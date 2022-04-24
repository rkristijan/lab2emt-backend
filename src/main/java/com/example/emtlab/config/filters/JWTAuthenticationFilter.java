package com.example.emtlab.config.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.emtlab.config.JWTAuthConstants;
import com.example.emtlab.model.LibraryUser;
import com.example.emtlab.model.dto.UserDetailsDto;
import com.example.emtlab.model.exceptions.PasswordsDoNotMatchException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@AllArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LibraryUser credentials = null;

        try {
            credentials = new ObjectMapper().readValue(request.getInputStream(), LibraryUser.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (credentials == null){
            throw new UsernameNotFoundException("Invalid credentials");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(credentials.getUsername());
        if (passwordEncoder.matches(credentials.getPassword(), userDetails.getPassword())){
            throw new PasswordsDoNotMatchException();
        }

        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDetails.getUsername(),"", userDetails.getAuthorities()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        LibraryUser userDetails = (LibraryUser) authResult.getPrincipal();
        String token  = JWT.create()
                .withSubject(new ObjectMapper().writeValueAsString(UserDetailsDto.of(userDetails)))
                .withExpiresAt(new Date(System.currentTimeMillis()+ JWTAuthConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(JWTAuthConstants.SECRET));

        response.addHeader(JWTAuthConstants.HEADER_STRING,JWTAuthConstants.TOKEN_PREFIX + token);
        response.getWriter().append(token);
    }
}

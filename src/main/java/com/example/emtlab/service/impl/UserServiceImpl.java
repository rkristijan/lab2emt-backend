package com.example.emtlab.service.impl;

import com.example.emtlab.model.LibraryUser;

import com.example.emtlab.model.enums.Role;
import com.example.emtlab.model.exceptions.InvalidUsernameOrPasswordException;
import com.example.emtlab.model.exceptions.PasswordsDoNotMatchException;
import com.example.emtlab.model.exceptions.UsernameAlreadyExistsException;
import com.example.emtlab.repository.UserRepository;
import com.example.emtlab.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }


    @Override
    public LibraryUser register(String username, String password, String repeatPassword, String name, String surname, Role userRole) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        LibraryUser user = new LibraryUser(username,passwordEncoder.encode(password),name,surname,userRole);
        return userRepository.save(user);
    }
}

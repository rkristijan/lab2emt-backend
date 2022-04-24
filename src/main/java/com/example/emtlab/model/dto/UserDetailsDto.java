package com.example.emtlab.model.dto;

import com.example.emtlab.model.LibraryUser;
import com.example.emtlab.model.enums.Role;
import lombok.Data;

@Data
public class UserDetailsDto {
    private String username;
    private Role role;

    public static UserDetailsDto of(LibraryUser user){
        UserDetailsDto details = new UserDetailsDto();
        details.username= user.getUsername();
        details.role = user.getRole();
        return details;
    }
}

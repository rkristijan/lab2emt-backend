package com.example.emtlab.service;

import com.example.emtlab.model.LibraryUser;

import com.example.emtlab.model.enums.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    LibraryUser register(String username, String password, String repeatPassword, String name, String surname, Role role);
}

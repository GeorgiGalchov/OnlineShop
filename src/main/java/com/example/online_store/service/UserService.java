package com.example.online_store.service;

import com.example.online_store.model.dto.UserRegistrationDTO;
import org.springframework.security.core.Authentication;

public interface UserService {

    void registerUser(UserRegistrationDTO userRegistrationDTO);

    void createUserIfNotExist(String email, String names);

    Authentication login(String email);
}
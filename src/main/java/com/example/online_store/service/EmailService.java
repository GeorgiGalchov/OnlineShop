package com.example.online_store.service;

public interface EmailService {
    void sendRegistrationEmail(
            String userEmail,
            String userName,
            String activationCode);
}
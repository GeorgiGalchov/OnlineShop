package com.example.online_store.service;

import com.example.online_store.model.dto.ReCaptchaResponseDTO;

import java.util.Optional;

public interface ReCaptchaService {
    Optional<ReCaptchaResponseDTO> verify(String token);
}
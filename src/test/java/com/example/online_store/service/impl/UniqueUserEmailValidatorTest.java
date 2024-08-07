package com.example.online_store.service.impl;


import com.example.online_store.model.entity.UserEntity;
import com.example.online_store.model.validation.UniqueUserEmailValidator;
import com.example.online_store.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class UniqueUserEmailValidatorTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UniqueUserEmailValidator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsValidEmailNotInRepository() {
        // Arrange
        String email = "unique@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act
        boolean isValid = validator.isValid(email, null);

        // Assert
        assertTrue(isValid, "Email should be valid as it is not in the repository");
    }

    @Test
    void testIsValidEmailInRepository() {
        // Arrange
        String email = "duplicate@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(new UserEntity())); // Assuming User is a class in your repo

        // Act
        boolean isValid = validator.isValid(email, null);

        // Assert
        assertFalse(isValid, "Email should be invalid as it is already in the repository");
    }
}

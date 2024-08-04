package com.example.online_store.service.impl;

import com.example.online_store.model.dto.UserRegistrationDTO;
import com.example.online_store.model.entity.UserEntity;
import com.example.online_store.model.events.UserRegisteredEvent;
import com.example.online_store.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ApplicationEventPublisher appEventPublisher;

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_shouldSaveUserAndPublishEvent() {
        // Arrange
        UserRegistrationDTO registrationDTO = new UserRegistrationDTO("Georgi", "Galchov", "galchov@example.com", "secretno","secretno");
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("encodedPassword");

        // Mock behavior
        when(modelMapper.map(any(UserRegistrationDTO.class), eq(UserEntity.class))).thenReturn(userEntity);
        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");

        // Act
        userService.registerUser(registrationDTO);

        // Assert
        ArgumentCaptor<UserEntity> userCaptor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepository, times(1)).save(userCaptor.capture());
        UserEntity capturedUser = userCaptor.getValue();

        assertEquals("encodedPassword", capturedUser.getPassword());

        ArgumentCaptor<UserRegisteredEvent> eventCaptor = ArgumentCaptor.forClass(UserRegisteredEvent.class);
        verify(appEventPublisher, times(1)).publishEvent(eventCaptor.capture());
        UserRegisteredEvent capturedEvent = eventCaptor.getValue();

        assertEquals("UserService", capturedEvent.getSource());
        assertEquals("galchov@example.com", capturedEvent.getUserEmail());
        assertEquals("Georgi Galchov", capturedEvent.getUserNames());
    }
}

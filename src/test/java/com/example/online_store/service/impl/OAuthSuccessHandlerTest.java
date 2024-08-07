package com.example.online_store.service.impl;

import com.example.online_store.service.UserService;
import com.example.online_store.service.oauth.OAuthSuccessHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import java.io.IOException;
import static org.mockito.Mockito.*;

public class OAuthSuccessHandlerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private OAuthSuccessHandler oAuthSuccessHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void onAuthenticationSuccess_shouldCallUserService_whenOAuth2AuthenticationTokenIsValid() throws ServletException, IOException {
        // Arrange
        OAuth2User oAuth2User = mock(OAuth2User.class);
        when(oAuth2User.getAttribute("email")).thenReturn("test@example.com");
        when(oAuth2User.getAttribute("name")).thenReturn("Test User");

        OAuth2AuthenticationToken authenticationToken = mock(OAuth2AuthenticationToken.class);
        when(authenticationToken.getPrincipal()).thenReturn(oAuth2User);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);


        oAuthSuccessHandler.onAuthenticationSuccess(request, response, authenticationToken);


        verify(userService).createUserIfNotExist("test@example.com", "Test User");

    }

    @Test
    void onAuthenticationSuccess_shouldNotFail_whenNotOAuth2AuthenticationToken() throws ServletException, IOException {

        Authentication authentication = mock(Authentication.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        oAuthSuccessHandler.onAuthenticationSuccess(request, response, authentication);


    }
}

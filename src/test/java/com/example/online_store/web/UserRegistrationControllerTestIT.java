package com.example.online_store.web;

import com.example.online_store.model.dto.ReCaptchaResponseDTO;
import com.example.online_store.model.dto.UserRegistrationDTO;
import com.example.online_store.service.ReCaptchaService;
import com.example.online_store.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegistrationControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private ReCaptchaService reCaptchaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrationSuccessful() throws Exception {

        ReCaptchaResponseDTO reCaptchaResponseDTO = new ReCaptchaResponseDTO();
        reCaptchaResponseDTO.setSuccess(true);
        when(reCaptchaService.verify("test-recaptcha-response")).thenReturn(Optional.of(reCaptchaResponseDTO));


        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("Gosho", "Ivanov", "gosho@abv.bg", "topsecret", "topsecret");

        mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
                        .param("firstName", userRegistrationDTO.getFirstName())
                        .param("lastName", userRegistrationDTO.getLastName())
                        .param("email", userRegistrationDTO.getEmail())
                        .param("password", userRegistrationDTO.getPassword())
                        .param("confirmPassword", userRegistrationDTO.getConfirmPassword())
                        .param("g-recaptcha-response", "test-recaptcha-response")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));


        verify(reCaptchaService).verify("test-recaptcha-response");
    }

    @Test
    void testRegistrationFailure() throws Exception {

        ReCaptchaResponseDTO reCaptchaResponseDTO = new ReCaptchaResponseDTO();
        reCaptchaResponseDTO.setSuccess(false);
        when(reCaptchaService.verify("test-recaptcha-response")).thenReturn(Optional.of(reCaptchaResponseDTO));

        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("Gosho", "Ivanov", "gosho@abv.bg", "topsecret", "topsecret");

        mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
                        .param("firstName", userRegistrationDTO.getFirstName())
                        .param("lastName", userRegistrationDTO.getLastName())
                        .param("email", userRegistrationDTO.getEmail())
                        .param("password", userRegistrationDTO.getPassword())
                        .param("confirmPassword", userRegistrationDTO.getConfirmPassword())
                        .param("g-recaptcha-response", "test-recaptcha-response")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }
}

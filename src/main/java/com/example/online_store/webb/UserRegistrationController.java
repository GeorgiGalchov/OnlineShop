package com.example.online_store.webb;

import com.example.online_store.model.dto.ReCaptchaResponseDTO;
import com.example.online_store.model.dto.UserRegistrationDTO;
import com.example.online_store.service.ReCaptchaService;
import com.example.online_store.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@Controller
public class UserRegistrationController {

    private final UserService userService;
    private final ReCaptchaService reCaptchaService;

    public UserRegistrationController(
            UserService userService,
            ReCaptchaService reCaptchaService) {
        this.userService = userService;
        this.reCaptchaService = reCaptchaService;
    }

    @GetMapping("/register")
    public String register() {
        return "auth-register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserRegistrationDTO userRegistrationDTO,
                           @RequestParam("g-recaptcha-response") String reCaptchaResponse) {

        System.out.println("ReCAPTCHA Response: " + reCaptchaResponse);

        boolean isBot = !reCaptchaService
                .verify(reCaptchaResponse)
                .map(ReCaptchaResponseDTO::isSuccess)
                .orElse(false);

        if (isBot) {
            System.out.println("reCAPTCHA verification failed.");
            return "redirect:/";
        }

        System.out.println("User Registration DTO: " + userRegistrationDTO);
        userService.registerUser(userRegistrationDTO);

        return "redirect:/";
    }



}
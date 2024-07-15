package com.example.online_store.model.dto;

import com.example.online_store.model.validation.UniqueUserEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import com.example.online_store.model.validation.FieldMatch;

@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "Passwords should match."
)
public record UserRegistrationDTO(@NotEmpty String firstName,
                                  @NotEmpty String lastName,
                                  @NotNull @Email @UniqueUserEmail String email,
                                  String password,
                                  String confirmPassword) {

    public String fullName() {
        return firstName + " " + lastName;
    }

}
package com.example.online_store.model.dto;

import com.example.online_store.model.validation.FieldMatch;
import com.example.online_store.model.validation.UniqueUserEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "Passwords should match."
)
public class UserRegistrationDTO {
    @NotEmpty private String firstName;
    @NotEmpty private String lastName;
    @NotNull @Email @UniqueUserEmail private String email;
    private String password;
   private String confirmPassword;

    public String fullName() {

        return firstName + " " + lastName;

    }

    public UserRegistrationDTO(String firstName, String lastName, String email, String password, String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public @NotEmpty String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotEmpty String firstName) {
        this.firstName = firstName;
    }

    public @NotEmpty String getLastName() {
        return lastName;
    }

    public void setLastName(@NotEmpty String lastName) {
        this.lastName = lastName;
    }

    public @NotNull @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotNull @Email String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
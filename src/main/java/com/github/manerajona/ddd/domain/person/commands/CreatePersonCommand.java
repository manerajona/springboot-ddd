package com.github.manerajona.ddd.domain.person.commands;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreatePersonCommand(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @Email String email,
        @Pattern(message = "must contain at least one letter, one digit and be at least 8 characters long",
                regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$") String password) {
}

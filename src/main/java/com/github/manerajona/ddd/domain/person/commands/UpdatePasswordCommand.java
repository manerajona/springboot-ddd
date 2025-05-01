package com.github.manerajona.ddd.domain.person.commands;

import com.github.manerajona.ddd.domain.person.vo.id.PersonId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UpdatePasswordCommand(
        PersonId personId,
        @NotBlank String oldPassword,
        @Pattern(message = "must contain at least one letter, one digit and be at least 8 characters long",
                regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$") String newPassword) {

    public UpdatePasswordCommand withPersonId(PersonId id) {
        return new UpdatePasswordCommand(id, oldPassword, newPassword);
    }
}

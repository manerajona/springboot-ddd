package com.github.manerajona.ddd.domain.person.vo.email;

import com.github.manerajona.ddd.domain.base.ValueObject;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public record EmailAddress(@Email String email) implements ValueObject {

    @Override
    public @NotNull String toString() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailAddress that = (EmailAddress) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}

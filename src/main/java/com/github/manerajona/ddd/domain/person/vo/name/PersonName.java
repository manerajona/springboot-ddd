package com.github.manerajona.ddd.domain.person.vo.name;

import com.github.manerajona.ddd.domain.base.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Embeddable
public class PersonName implements ValueObject {

    @Column(name = "first_name", nullable = false, updatable = false)
    private String firstname;

    @Column(name = "last_name", nullable = false, updatable = false)
    private String lastname;

    public PersonName(@NotNull String firstname, @NotNull String lastname) {
        this.firstname = Objects.requireNonNull(firstname);
        this.lastname = Objects.requireNonNull(lastname);
    }

    protected PersonName() {

    }

    public @NotNull String firstname() {
        return this.firstname;
    }

    public @NotNull String lastname() {
        return this.lastname;
    }

    @Override
    public @NotNull String toString() {
        return "%s %s".formatted(this.firstname, this.lastname);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonName that = (PersonName) o;
        return Objects.equals(firstname, that.firstname)
                && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname);
    }
}

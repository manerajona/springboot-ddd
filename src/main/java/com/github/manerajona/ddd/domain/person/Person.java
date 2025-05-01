package com.github.manerajona.ddd.domain.person;

import com.github.manerajona.ddd.domain.base.BaseAggregateRoot;
import com.github.manerajona.ddd.domain.person.errors.PasswordDoesNotMatchException;
import com.github.manerajona.ddd.domain.person.events.PersonPasswordChangedEvent;
import com.github.manerajona.ddd.domain.person.vo.email.EmailAddress;
import com.github.manerajona.ddd.domain.person.vo.email.EmailAddressAttributeConverter;
import com.github.manerajona.ddd.domain.person.vo.id.PersonId;
import com.github.manerajona.ddd.domain.person.vo.id.PersonIdClassJavaType;
import com.github.manerajona.ddd.domain.person.vo.id.PersonIdGeneratorType;
import com.github.manerajona.ddd.domain.person.vo.name.PersonName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.JavaType;

import java.time.Instant;

@Entity
public class Person extends BaseAggregateRoot<PersonId> {

    @Id
    @PersonIdGeneratorType
    @JavaType(PersonIdClassJavaType.class)
    private PersonId id;

    @Embedded
    private PersonName name;

    @Column(name = "email", nullable = false, updatable = false, unique = true)
    @Convert(converter = EmailAddressAttributeConverter.class)
    private EmailAddress emailAddress;

    @Column(nullable = false)
    private String password;

    public Person(@NotNull PersonName name, @NotNull EmailAddress emailAddress, @NotNull String password) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    protected Person() {

    }

    @Override
    public PersonId getId() {
        return this.id;
    }

    public @NotNull PersonName name() {
        return name;
    }

    public @NotNull EmailAddress emailAddress() {
        return emailAddress;
    }

    public void setPassword(@NotBlank String newPassword, @NotBlank String oldPassword) throws PasswordDoesNotMatchException {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
            registerEvent(new PersonPasswordChangedEvent(this, Instant.now()));
        } else {
            throw new PasswordDoesNotMatchException();
        }
    }
}
package com.github.manerajona.ddd.domain.person.errors;

import com.github.manerajona.ddd.domain.base.DomainException;

public class EmailAlreadyRegisteredException extends DomainException {

    public EmailAlreadyRegisteredException() {
        super("Email is already registered.");
    }
}
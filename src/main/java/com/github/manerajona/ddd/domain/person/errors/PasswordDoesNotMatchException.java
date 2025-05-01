package com.github.manerajona.ddd.domain.person.errors;

import com.github.manerajona.ddd.domain.base.DomainException;

public class PasswordDoesNotMatchException extends DomainException {

    public PasswordDoesNotMatchException() {
        super("Old password does not match.");
    }
}

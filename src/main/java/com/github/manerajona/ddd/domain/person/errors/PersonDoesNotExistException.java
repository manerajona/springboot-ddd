package com.github.manerajona.ddd.domain.person.errors;

import com.github.manerajona.ddd.domain.base.DomainException;
import com.github.manerajona.ddd.domain.person.vo.id.PersonId;

public class PersonDoesNotExistException extends DomainException {

    public PersonDoesNotExistException(PersonId personId) {
        super("Person does not exist: " + personId.guid());
    }
}
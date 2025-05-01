package com.github.manerajona.ddd.domain.base;

/**
 * Base class for domain errors.
 */
public abstract class DomainException extends RuntimeException {
    protected DomainException(String message) {
        super(message);
    }
}
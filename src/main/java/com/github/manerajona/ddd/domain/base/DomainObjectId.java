package com.github.manerajona.ddd.domain.base;

import java.io.Serializable;

/**
 * Marker interface for value objects that act as identifiers of {@link IdentifiableDomainObject}s.
 */
public interface DomainObjectId extends ValueObject, Serializable {
}

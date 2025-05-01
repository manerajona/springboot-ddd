package com.github.manerajona.ddd.domain.person.events;

import com.github.manerajona.ddd.domain.base.DomainEvent;
import com.github.manerajona.ddd.domain.person.Person;

import java.time.Instant;

public record PersonCreatedEvent(Person person, Instant occurredOn) implements DomainEvent {
}

package com.github.manerajona.ddd.port.input.consumer;

import com.github.manerajona.ddd.domain.person.events.PersonCreatedEvent;
import com.github.manerajona.ddd.domain.person.events.PersonPasswordChangedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
class PersonEventHandler {

    @TransactionalEventListener
    public void on(PersonCreatedEvent event) {
        System.out.println(event);
    }

    @EventListener
    public void on(PersonPasswordChangedEvent event) {
        System.out.println(event);
    }
}
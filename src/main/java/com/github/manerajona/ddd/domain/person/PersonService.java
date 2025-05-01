package com.github.manerajona.ddd.domain.person;

import com.github.manerajona.ddd.domain.person.commands.CreatePersonCommand;
import com.github.manerajona.ddd.domain.person.commands.UpdatePasswordCommand;
import com.github.manerajona.ddd.domain.person.errors.EmailAlreadyRegisteredException;
import com.github.manerajona.ddd.domain.person.errors.PersonDoesNotExistException;
import com.github.manerajona.ddd.domain.person.events.PersonCreatedEvent;
import com.github.manerajona.ddd.domain.person.queries.PersonByIdQuery;
import com.github.manerajona.ddd.domain.person.vo.email.EmailAddress;
import com.github.manerajona.ddd.domain.person.vo.id.PersonId;
import com.github.manerajona.ddd.domain.person.vo.name.PersonName;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final ApplicationEventPublisher eventPublisher;

    public PersonService(PersonRepository personRepository, ApplicationEventPublisher eventPublisher) {
        this.personRepository = personRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public PersonId handle(CreatePersonCommand command) {
        if (personRepository.existsByEmailAddress(new EmailAddress(command.email()))) {
            throw new EmailAlreadyRegisteredException();
        }
        Person person = personRepository.saveAndFlush(createPersonCommandToPerson(command));
        eventPublisher.publishEvent(new PersonCreatedEvent(person, Instant.now()));
        return person.getId();
    }

    @Transactional(readOnly = true)
    public PersonProjection handle(PersonByIdQuery query) {
        return personRepository.queryById(query.personId())
                .orElseThrow(() -> new PersonDoesNotExistException(query.personId()));
    }

    @Transactional
    public void handle(UpdatePasswordCommand command) {
        Person person = personRepository.findById(command.personId())
                .orElseThrow(() -> new PersonDoesNotExistException(command.personId()));
        person.setPassword(command.newPassword(), command.oldPassword());
        personRepository.saveAndFlush(person);
    }

    private static Person createPersonCommandToPerson(CreatePersonCommand command) {
        return new Person(
                new PersonName(command.firstName(), command.lastName()),
                new EmailAddress(command.email()),
                command.password()
        );
    }
}

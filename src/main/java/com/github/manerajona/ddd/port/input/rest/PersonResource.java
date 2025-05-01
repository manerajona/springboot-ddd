package com.github.manerajona.ddd.port.input.rest;

import com.github.manerajona.ddd.domain.person.PersonProjection;
import com.github.manerajona.ddd.domain.person.PersonService;
import com.github.manerajona.ddd.domain.person.commands.CreatePersonCommand;
import com.github.manerajona.ddd.domain.person.commands.UpdatePasswordCommand;
import com.github.manerajona.ddd.domain.person.errors.EmailAlreadyRegisteredException;
import com.github.manerajona.ddd.domain.person.errors.PasswordDoesNotMatchException;
import com.github.manerajona.ddd.domain.person.errors.PersonDoesNotExistException;
import com.github.manerajona.ddd.domain.person.queries.PersonByIdQuery;
import com.github.manerajona.ddd.domain.person.vo.id.PersonId;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people")
public class PersonResource {

    private final PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonId create(@Valid @RequestBody CreatePersonCommand command) {
        return personService.handle(command);
    }

    @GetMapping("/{guid}")
    public PersonProjection getByGuid(@PathVariable UUID guid) {
        return personService.handle(new PersonByIdQuery(new PersonId(guid)));
    }

    @PatchMapping("/{guid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@PathVariable UUID guid, @Valid @RequestBody UpdatePasswordCommand command) {
        personService.handle(command.withPersonId(new PersonId(guid)));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleValidationExceptions(MethodArgumentNotValidException exception) {
        return exception.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));
    }

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Object handleEmailAlreadyRegisteredException(EmailAlreadyRegisteredException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(PersonDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object handlePersonDoesNotExistException(PersonDoesNotExistException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(PasswordDoesNotMatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handlePasswordDoesNotMatchException(PasswordDoesNotMatchException exception) {
        return exception.getMessage();
    }
}

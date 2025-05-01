package com.github.manerajona.ddd.domain.person;

import com.github.manerajona.ddd.domain.base.BaseRepository;
import com.github.manerajona.ddd.domain.person.vo.email.EmailAddress;
import com.github.manerajona.ddd.domain.person.vo.id.PersonId;

import java.util.Optional;

public interface PersonRepository extends BaseRepository<Person, PersonId> {

    Optional<PersonProjection> queryById(PersonId personId);

    boolean existsByEmailAddress(EmailAddress emailAddress);
}

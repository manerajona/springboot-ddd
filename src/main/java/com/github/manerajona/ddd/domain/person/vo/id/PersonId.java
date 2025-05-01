package com.github.manerajona.ddd.domain.person.vo.id;

import com.github.manerajona.ddd.domain.base.DomainObjectId;

import java.util.UUID;

public record PersonId(UUID guid) implements DomainObjectId {
}

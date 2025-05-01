package com.github.manerajona.ddd.domain.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

@JsonPropertyOrder(value = {"guid", "name", "email"})
public interface PersonProjection {

    @JsonProperty("guid")
    @Value("#{target.id.guid}")
    UUID getGuid();

    @JsonProperty("fullName")
    @Value("#{target.name.firstname + ' ' + target.name.lastname}")
    String getName();

    @JsonProperty("email")
    @Value("#{target.emailAddress.email}")
    String getEmail();
}
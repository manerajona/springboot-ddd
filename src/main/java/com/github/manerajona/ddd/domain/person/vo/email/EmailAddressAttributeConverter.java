package com.github.manerajona.ddd.domain.person.vo.email;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.lang.Contract;

@Converter
public class EmailAddressAttributeConverter implements AttributeConverter<EmailAddress, String> {

    @Override
    @Contract("null -> null")
    public String convertToDatabaseColumn(EmailAddress attribute) {
        return attribute == null ? null : attribute.toString();
    }

    @Override
    @Contract("null -> null")
    public EmailAddress convertToEntityAttribute(String dbColumnValue) {
        return dbColumnValue == null ? null : new EmailAddress(dbColumnValue);
    }
}
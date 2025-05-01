package com.github.manerajona.ddd.domain.person.vo.id;

import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractClassJavaType;
import org.hibernate.type.descriptor.java.UUIDJavaType;

import java.util.UUID;

public class PersonIdClassJavaType extends AbstractClassJavaType<PersonId> {

    public static final PersonIdClassJavaType INSTANCE = new PersonIdClassJavaType();

    private PersonIdClassJavaType() {
        super(PersonId.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <X> X unwrap(PersonId value, Class<X> type, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (getJavaType().isAssignableFrom(type)) {
            return (X) value;
        }
        if (UUID.class.isAssignableFrom(type)) {
            return (X) UUIDJavaType.PassThroughTransformer.INSTANCE.transform(value.guid());
        }
        if (String.class.isAssignableFrom(type)) {
            return (X) UUIDJavaType.ToStringTransformer.INSTANCE.transform(value.guid());
        }
        if (byte[].class.isAssignableFrom(type)) {
            return (X) UUIDJavaType.ToBytesTransformer.INSTANCE.transform(value.guid());
        }
        throw unknownUnwrap(type);
    }

    @Override
    public <X> PersonId wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (getJavaType().isInstance(value)) {
            return getJavaType().cast(value);
        }
        if (value instanceof UUID) {
            return new PersonId(UUIDJavaType.PassThroughTransformer.INSTANCE.parse(value));
        }
        if (value instanceof String) {
            return new PersonId(UUIDJavaType.ToStringTransformer.INSTANCE.parse(value));
        }
        if (value instanceof byte[]) {
            return new PersonId(UUIDJavaType.ToBytesTransformer.INSTANCE.parse(value));
        }
        throw unknownWrap(value.getClass());
    }

    @Override
    public String toString(PersonId value) {
        return UUIDJavaType.ToStringTransformer.INSTANCE.transform(value.guid());
    }
}
package com.github.manerajona.ddd.domain.base;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Persistable;
import org.springframework.data.util.ProxyUtils;

import java.io.Serializable;
import java.util.Optional;

/**
 * Base class for entities.
 *
 * @param <ID> the ID type.
 */
@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> implements Persistable<ID>, IdentifiableDomainObject<ID> {

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    @Override
    @Transient
    public abstract @Nullable ID getId();

    protected BaseEntity() {
    }

    @Override
    @Transient
    public boolean isNew() {
        return getId() == null;
    }

    @Override
    @Transient
    public @NotNull ID getIdentifier() {
        var id = getId();
        if (id == null) {
            throw new IllegalStateException("No ID set");
        }
        return id;
    }

    @Override
    public boolean hasIdentifier() {
        return getId() != null;
    }

    /**
     * Returns the optimistic locking version.
     *
     * @return the version or an empty {@code Optional} if the entity has not been persisted yet.
     */
    @Transient
    public @NotNull Optional<Long> getVersion() {
        return Optional.ofNullable(version);
    }

    /**
     * Sets the optimistic locking version. You should almost never need to do this.
     *
     * @param version the version to set.
     */
    protected void setVersion(@Nullable Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return String.format("%s{id=%s, version=%d}", getClass().getSimpleName(), getId(), version);
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object obj) {
        if (null == obj) return false;
        if (this == obj) return true;
        if (!getClass().equals(ProxyUtils.getUserClass(obj))) return false;
        var that = (BaseEntity<?>) obj;
        var id = getId();
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        var id = getId();
        return id == null ? super.hashCode() : id.hashCode();
    }
}

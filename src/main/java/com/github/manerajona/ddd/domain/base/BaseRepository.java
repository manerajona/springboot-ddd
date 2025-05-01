package com.github.manerajona.ddd.domain.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Base interface for repositories of {@linkplain BaseAggregateRoot aggregate roots}.
 *
 * @param <AGGREGATE> the aggregate root type.
 * @param <ID>        the ID type.
 */
@NoRepositoryBean
public interface BaseRepository<AGGREGATE extends BaseAggregateRoot<ID>, ID extends Serializable>
        extends JpaRepository<AGGREGATE, ID>, JpaSpecificationExecutor<AGGREGATE> {
}
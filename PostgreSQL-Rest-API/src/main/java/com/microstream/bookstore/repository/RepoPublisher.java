package com.microstream.bookstore.repository;

import java.util.Optional;

import com.microstream.bookstore.domain.Publisher;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;
import io.micronaut.data.repository.jpa.JpaSpecificationExecutor;
import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;


@JdbcRepository(dialect = Dialect.POSTGRES)
public interface RepoPublisher extends PageableRepository<Publisher, Integer>, JpaSpecificationExecutor<Publisher>
{
	@Join(value = "addresses", type = Join.Type.LEFT_FETCH)
	Optional<Publisher> findById(@NonNull Integer id);
	
	@Join(value = "addresses", type = Join.Type.LEFT_FETCH)
	Optional<Publisher> findOne(@Nullable PredicateSpecification<Publisher> spec);
}

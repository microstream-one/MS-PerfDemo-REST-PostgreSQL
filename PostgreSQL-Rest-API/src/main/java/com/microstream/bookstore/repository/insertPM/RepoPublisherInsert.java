package com.microstream.bookstore.repository.insertPM;

import java.util.Optional;

import com.microstream.bookstore.domain.Publisher;
import com.microstream.bookstore.domain.insert.PublisherInsert;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;
import io.micronaut.data.repository.jpa.JpaSpecificationExecutor;
import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;


@JdbcRepository(dialect = Dialect.POSTGRES)
public interface RepoPublisherInsert extends PageableRepository<PublisherInsert, Integer>, JpaSpecificationExecutor<PublisherInsert>
{
	@Join(value = "addresses", type = Join.Type.LEFT_FETCH)
	Optional<PublisherInsert> findById(@NonNull Integer id);
	
	@Join(value = "addresses", type = Join.Type.LEFT_FETCH)
	Optional<PublisherInsert> findOne(@Nullable PredicateSpecification<PublisherInsert> spec);
}

package com.microstream.bookstore.repository.insertPM;

import java.util.Optional;

import com.microstream.bookstore.domain.insert.AuthorInsert;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.repository.jpa.JpaSpecificationExecutor;
import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;


@JdbcRepository(dialect = Dialect.POSTGRES)
public interface RepoAuthorsInsert extends CrudRepository<AuthorInsert, Integer>, JpaSpecificationExecutor<AuthorInsert>
{	
	@Join(value = "addresses", type = Join.Type.LEFT_FETCH)
	Optional<AuthorInsert> findById(@NonNull Integer id);
	
	@Join(value = "addresses", type = Join.Type.LEFT_FETCH)
	Optional<AuthorInsert> findOne(@Nullable PredicateSpecification<AuthorInsert> spec);
}

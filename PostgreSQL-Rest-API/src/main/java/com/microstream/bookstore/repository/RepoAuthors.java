package com.microstream.bookstore.repository;

import java.util.Optional;

import com.microstream.bookstore.domain.Author;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.repository.jpa.JpaSpecificationExecutor;


@JdbcRepository(dialect = Dialect.POSTGRES)
public interface RepoAuthors extends CrudRepository<Author, Integer>, JpaSpecificationExecutor<Author>
{
//	@Join(value = "addresses", type = Join.Type.LEFT_FETCH)
//	Optional<Author> getById(@NonNull Integer id);
	
	@Join(value = "addresses", type = Join.Type.LEFT_FETCH)
	Optional<Author> findById(@NonNull Integer id);
}

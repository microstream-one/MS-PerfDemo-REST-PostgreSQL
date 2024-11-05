package com.microstream.bookstore.repository;

import com.microstream.bookstore.domain.Author;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;
import io.micronaut.data.repository.jpa.JpaSpecificationExecutor;

@JdbcRepository(dialect = Dialect.POSTGRES) 
public interface RepoAuthors extends PageableRepository<Author, Integer>, JpaSpecificationExecutor<Author>
{
	
}

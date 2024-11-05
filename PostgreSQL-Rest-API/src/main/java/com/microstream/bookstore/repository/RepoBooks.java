package com.microstream.bookstore.repository;

import com.microstream.bookstore.domain.Book;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;
import io.micronaut.data.repository.jpa.JpaSpecificationExecutor;

@JdbcRepository(dialect = Dialect.POSTGRES) 
public interface RepoBooks extends PageableRepository<Book, Integer>, JpaSpecificationExecutor<Book>
{
	
}

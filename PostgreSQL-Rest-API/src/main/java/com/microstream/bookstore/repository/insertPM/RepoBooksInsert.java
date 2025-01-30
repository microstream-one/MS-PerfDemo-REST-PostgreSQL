package com.microstream.bookstore.repository.insertPM;

import com.microstream.bookstore.domain.insert.BookInsert;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;
import io.micronaut.data.repository.jpa.JpaSpecificationExecutor;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface RepoBooksInsert extends PageableRepository<BookInsert, Integer>, JpaSpecificationExecutor<BookInsert>
{

}

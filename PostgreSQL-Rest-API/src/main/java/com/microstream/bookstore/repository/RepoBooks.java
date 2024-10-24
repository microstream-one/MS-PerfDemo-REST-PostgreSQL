package com.microstream.bookstore.repository;

import com.microstream.bookstore.domain.Book;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

@JdbcRepository(dialect = Dialect.POSTGRES) 
public interface RepoBooks extends PageableRepository<Book, String>
{
	
//	Book save(@NonNull @NotBlank String name);

//    @Transactional
//    default Book saveWithException(@NonNull @NotBlank String name) {
//        save(name);
//        throw new DataAccessException("test exception");
//    }

//    long update(@NonNull @NotNull String isbn, @NonNull @NotBlank String name);
}

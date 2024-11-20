package com.microstream.bookstore.repository;

import java.util.List;
import java.util.Optional;

import com.microstream.bookstore.domain.Book;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;
import io.micronaut.data.repository.jpa.JpaSpecificationExecutor;
import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface RepoBooks extends PageableRepository<Book, Integer>, JpaSpecificationExecutor<Book>
{
	@Join(value = "author", type = Join.Type.FETCH)
	@Join(value = "publisher", type = Join.Type.FETCH)
	List<Book> list();

	@Join(value = "author", type = Join.Type.FETCH)
	@Join(value = "publisher", type = Join.Type.FETCH)
	Optional<Book> findById(@NonNull Integer id);
	
	@Query(value = """
		SELECT books.id, books.isbn as isbn, books.title as title, books.author_id as author_id,
		books.available_quantity as available_quantity, books.edition as edition, books.price as price,
		books.publication_date as publication_date, books.publisher_id as publisher_id,
		author.mail as author_mail, author.firstname as author_firstname, author.lastname as author_lastname,
		publisher.company as publisher_company, publisher.mail as publisher_mail
		FROM books
		INNER JOIN author ON books.author_id = author.id
		INNER JOIN publisher ON books.publisher_id = publisher.id
		LEFT JOIN addresses ON publisher.id = addresses.publisher_id
		WHERE books.title LIKE :title
		""", nativeQuery = true)
	@Join(value = "author")
	@Join(value = "publisher")
	List<Book> searchBooksByTitle(String title);

	@Query(value = """
		SELECT books.id, books.isbn as isbn, books.title as title, books.author_id as author_id,
		books.available_quantity as available_quantity, books.edition as edition, books.price as price,
		books.publication_date as publication_date, books.publisher_id as publisher_id,
		author.mail as author_mail, author.firstname as author_firstname, author.lastname as author_lastname,
		publisher.company as publisher_company, publisher.mail as publisher_mail
		FROM books
		INNER JOIN author ON books.author_id = author.id
		INNER JOIN publisher ON books.publisher_id = publisher.id
		WHERE books.isbn = :isbn
		FETCH FIRST 1 ROWS ONLY
		""", nativeQuery = true)
	@Join(value = "author")
	@Join(value = "publisher")
	Optional<Book> searchBooksByIsbn(String isbn);
	
	@Join(value = "author")
	@Join(value = "publisher")
	Optional<Book> findOne(@Nullable PredicateSpecification<Book> spec);
}

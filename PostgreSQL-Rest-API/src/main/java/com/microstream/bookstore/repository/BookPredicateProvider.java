package com.microstream.bookstore.repository;

import com.microstream.bookstore.domain.Author;
import com.microstream.bookstore.domain.Book;

import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;

public interface BookPredicateProvider
{
	static PredicateSpecification<Book> titleContains(String title)
	{
		return (root, criteriaBuilder) -> criteriaBuilder.equal(root.get("title"), "%" + title + "%");
	}

	static PredicateSpecification<Book> authorIdEquals(Author author)
	{
		return (root, criteriaBuilder) -> criteriaBuilder.equal(root.get("author"), author.getId());
	}

	static PredicateSpecification<Book> isbnEquals(String isbn)
	{
		return (root, criteriaBuilder) -> criteriaBuilder.equal(root.get("isbn"), isbn);
	}
}

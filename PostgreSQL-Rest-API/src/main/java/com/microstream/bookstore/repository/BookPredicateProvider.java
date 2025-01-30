package com.microstream.bookstore.repository;

import com.microstream.bookstore.domain.Book;

import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;

public interface BookPredicateProvider
{
	static PredicateSpecification<Book> ISBNquals(String ISBN)
	{
		return (root, criteriaBuilder) -> criteriaBuilder.equal(root.get("isbn"), ISBN);
	}
}

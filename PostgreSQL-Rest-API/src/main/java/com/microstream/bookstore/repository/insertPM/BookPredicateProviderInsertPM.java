package com.microstream.bookstore.repository.insertPM;

import com.microstream.bookstore.domain.insert.BookInsert;

import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;

public interface BookPredicateProviderInsertPM
{
	static PredicateSpecification<BookInsert> ISBNquals(String ISBN)
	{
		return (root, criteriaBuilder) -> criteriaBuilder.equal(root.get("isbn"), ISBN);
	}
}

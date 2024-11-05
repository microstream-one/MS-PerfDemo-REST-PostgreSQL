package com.microstream.bookstore.repository;

import com.microstream.bookstore.domain.Author;

import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;

public class BookPredicateProvider
{
	public static PredicateSpecification<Author> nameEquals(String title) {
        return (root, criteriaBuilder) -> criteriaBuilder.equal(root.get("title"), "%" + title + "%");
    }
}

package com.microstream.bookstore.repository;

import com.microstream.bookstore.domain.Author;

import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;

public class AuthorPredicateProvider
{
	public static PredicateSpecification<Author> nameEquals(String mail) {
        return (root, criteriaBuilder) -> criteriaBuilder.equal(root.get("mail"), mail);
    }
}

package com.microstream.bookstore.repository;

import com.microstream.bookstore.domain.Author;

import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;

public interface AuthorPredicateProvider
{
	static PredicateSpecification<Author> emailEquals(String mail)
	{
		return (root, criteriaBuilder) -> criteriaBuilder.equal(root.get("mail"), mail);
	}
}

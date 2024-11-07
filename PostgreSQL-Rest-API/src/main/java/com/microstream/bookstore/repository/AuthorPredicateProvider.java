package com.microstream.bookstore.repository;

import com.microstream.bookstore.domain.Author;

import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;

public interface AuthorPredicateProvider
{
	static PredicateSpecification<Author> nameContains(String name)
	{
		return (root, criteriaBuilder) ->
		{
			final var likeName = "%" + name + "%";
			return criteriaBuilder.or(
				criteriaBuilder.equal(root.get("firstname"), likeName),
				criteriaBuilder.equal(root.get("lastname"), likeName)
			);
		};
	}

	static PredicateSpecification<Author> mailEquals(String mail)
	{
		return (root, criteriaBuilder) -> criteriaBuilder.equal(root.get("mail"), mail);
	}
}

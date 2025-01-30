package com.microstream.bookstore.repository.insertPM;

import com.microstream.bookstore.domain.insert.AuthorInsert;

import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;

public interface AuthorPredicateProviderInsertPM
{
	static PredicateSpecification<AuthorInsert> emailEquals(String mail)
	{
		return (root, criteriaBuilder) -> criteriaBuilder.equal(root.get("mail"), mail);
	}
}

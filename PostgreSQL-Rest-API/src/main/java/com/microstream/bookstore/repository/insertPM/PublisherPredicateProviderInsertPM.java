package com.microstream.bookstore.repository.insertPM;

import com.microstream.bookstore.domain.insert.PublisherInsert;

import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;

public class PublisherPredicateProviderInsertPM
{
	public static PredicateSpecification<PublisherInsert> emailEquals(String mail)
	{
		return (root, criteriaBuilder) -> criteriaBuilder.equal(root.get("mail"), mail);
	}
}

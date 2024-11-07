package com.microstream.bookstore.repository;

import com.microstream.bookstore.domain.Publisher;

import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;

public class PublisherPredicateProvider
{
	public static PredicateSpecification<Publisher> mailEquals(String mail)
	{
		return (root, criteriaBuilder) -> criteriaBuilder.equal(root.get("mail"), mail);
	}
}

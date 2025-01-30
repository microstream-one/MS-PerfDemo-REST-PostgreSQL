package com.microstream.bookstore.repository;

import com.microstream.bookstore.domain.Address;

import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;

public interface AddressPredicateProvider
{
	static PredicateSpecification<Address> addressEquals(String address)
	{
		return (root, criteriaBuilder) -> criteriaBuilder.equal(root.get("address"), "%" + address + "%");
	}
}

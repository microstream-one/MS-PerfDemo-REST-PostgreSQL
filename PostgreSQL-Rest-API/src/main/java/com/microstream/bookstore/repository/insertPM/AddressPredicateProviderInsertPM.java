package com.microstream.bookstore.repository.insertPM;

import com.microstream.bookstore.domain.insert.AddressInsert;

import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;

public interface AddressPredicateProviderInsertPM
{
	static PredicateSpecification<AddressInsert> addressEquals(String address)
	{
		return (root, criteriaBuilder) -> criteriaBuilder.equal(root.get("address"), "%" + address + "%");
	}
}

package com.microstream.bookstore.repository;

import com.microstream.bookstore.domain.Address;

import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;

public class AddressPredicateProvider
{
	public static PredicateSpecification<Address> nameEquals(String address) {
        return (root, criteriaBuilder) -> criteriaBuilder.equal(root.get("address"), "%" + address + "%");
    }
}

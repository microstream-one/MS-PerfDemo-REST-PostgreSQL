package com.microstream.bookstore.repository;

import java.util.List;

import com.microstream.bookstore.domain.Address;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;
import io.micronaut.data.repository.jpa.JpaSpecificationExecutor;


@JdbcRepository(dialect = Dialect.POSTGRES)
public interface RepoAddress extends PageableRepository<Address, Integer>, JpaSpecificationExecutor<Address>
{
	@Query("SELECT * FROM addresses where author_id = :authorId")
	List<Address> adressesByAuthorID(Integer authorId);
	
	@Query("SELECT * FROM addresses where publisher_id = :publisherId")
	List<Address> adressesByPublisherID(Integer publisherId);
}

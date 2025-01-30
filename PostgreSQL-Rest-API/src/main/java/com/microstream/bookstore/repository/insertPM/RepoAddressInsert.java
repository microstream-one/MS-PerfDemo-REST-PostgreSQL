package com.microstream.bookstore.repository.insertPM;

import java.util.List;

import com.microstream.bookstore.domain.insert.AddressInsert;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;
import io.micronaut.data.repository.jpa.JpaSpecificationExecutor;


@JdbcRepository(dialect = Dialect.POSTGRES)
public interface RepoAddressInsert extends PageableRepository<AddressInsert, Integer>, JpaSpecificationExecutor<AddressInsert>
{
	@Query("SELECT * FROM addressesinsert where author_id = :authorId")
	List<AddressInsert> adressesByAuthorID(Integer authorId);
	
	@Query("SELECT * FROM addressesinsert where publisher_id = :publisherId")
	List<AddressInsert> adressesByPublisherID(Integer publisherId);
}

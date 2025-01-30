package com.microstream.controller;

import java.util.List;

import com.microstream.bookstore.domain.Address;
import com.microstream.bookstore.repository.RepoAddress;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;

@Controller("/address")
public class ControllerAddress
{
	private final RepoAddress repo;
	
	public ControllerAddress(RepoAddress repo)
	{
		this.repo = repo;
	}
	
	@Get("/byAuthorID/{author_id}")
	List<Address> findByAuthorID(@PathVariable final Integer author_id)
	{
		List<Address> all = repo.adressesByAuthorID(author_id);
		
		return all;
	}
}

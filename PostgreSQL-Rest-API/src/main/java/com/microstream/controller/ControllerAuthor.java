package com.microstream.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.microstream.bookstore.domain.Address;
import com.microstream.bookstore.domain.Author;
import com.microstream.bookstore.dto.DTOAddress;
import com.microstream.bookstore.dto.DTOAuthor;
import com.microstream.bookstore.repository.RepoAuthors;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;


@Controller("/authors")
public class ControllerAuthor
{
	private final RepoAuthors authors;
	
	public ControllerAuthor(RepoAuthors authors)
	{
		this.authors = authors;
	}
	
	@Get("/byID/{id}")
	DTOAuthor findByID(@PathVariable final String id)
	{
		Author author = authors.findById(Integer.valueOf(id)).get();
		
		List<Address> addresses = author.getAddresses();
		
		List<DTOAddress> collect = author.getAddresses().stream().map(
			a -> new DTOAddress(
				a.getAddress(),
				a.getAddress2(),
				a.getZip(),
				a.getCity(),
				a.getCountry())).collect(Collectors.toList());
		
		return new DTOAuthor(author.getMail(), author.getFirstname(), author.getLastname(), collect);
	}
	
	
	
}

package com.microstream.controler;

import com.microstream.bookstore.domain.Author;
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
	Author findByID(@PathVariable final String id)
	{
		Author author = authors.findById(Integer.valueOf(id)).get();
		
		return author;
	}
}

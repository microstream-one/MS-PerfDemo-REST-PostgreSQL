package com.microstream.controler;

import java.util.List;
import java.util.Optional;

import com.microstream.bookstore.domain.Author;
import com.microstream.bookstore.repository.RepoAuthors;

import io.micronaut.data.model.Pageable;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;

@Controller("/authors")
public class ControllerAuthor
{
	private final RepoAuthors authors;

	public ControllerAuthor(RepoAuthors authors)
	{
		this.authors = authors;
	}

	@Get
	public List<Author> getAuthors(@QueryValue Optional<Integer> limit)
	{
		return authors.findAll(Pageable.from(0, limit.orElse(-1))).getContent();
	}
}

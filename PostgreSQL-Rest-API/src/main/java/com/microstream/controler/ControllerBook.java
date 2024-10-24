package com.microstream.controler;

import java.util.List;

import com.microstream.bookstore.domain.Book;
import com.microstream.bookstore.dto.DTOBook;
import com.microstream.bookstore.repository.RepoBooks;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.validation.Valid;


@Controller("/books")
public class ControllerBook
{
	
	protected RepoBooks repo;
	
	public ControllerBook(RepoBooks repo)
	{
		this.repo = repo;
	}
	
	@Post
	HttpResponse<String> create(@NonNull @Valid @Body DTOBook dto)
	{
		repo.save(new Book(dto));
		return HttpResponse.ok("Successfully created");
	}
	
	@Get()
	List<Book> findAll()
	{
		return repo.findAll();
	}
		
}

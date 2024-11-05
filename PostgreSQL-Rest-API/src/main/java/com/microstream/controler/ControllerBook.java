package com.microstream.controler;

import java.util.List;
import java.util.Optional;

import com.microstream.bookstore.domain.Author;
import com.microstream.bookstore.domain.Book;
import com.microstream.bookstore.domain.Publisher;
import com.microstream.bookstore.dto.DTOBook;
import com.microstream.bookstore.repository.AuthorPredicateProvider;
import com.microstream.bookstore.repository.PublisherPredicateProvider;
import com.microstream.bookstore.repository.RepoAuthors;
import com.microstream.bookstore.repository.RepoBooks;
import com.microstream.bookstore.repository.RepoPublisher;

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
	
	protected RepoBooks bookRepo;
	protected RepoAuthors authorRepo;
	protected RepoPublisher pubRepo;
	
	public ControllerBook(RepoBooks bookRepo, RepoAuthors authorRepo, RepoPublisher pubRepo)
	{
		this.bookRepo = bookRepo;
		this.authorRepo = authorRepo;
		this.pubRepo = pubRepo;
	}
	
	@Post
	HttpResponse<String> create(@NonNull @Valid @Body DTOBook dto)
	{
		Optional<Author> optAuthor = authorRepo.findOne(AuthorPredicateProvider.nameEquals(dto.author().mail()));
		Optional<Publisher> optPublisher = pubRepo.findOne(PublisherPredicateProvider.nameEquals(dto.publisher().mail()));
		
		Author author;
		Publisher publisher;
		
		if(optAuthor.isPresent())
		{
			author = optAuthor.get();
		}
		else
		{
			author = new Author(dto.author().mail(), dto.author().firstname(), dto.author().lastname());
			author = authorRepo.save(author);
		}
		
		if(optPublisher.isPresent())
		{
			publisher = optPublisher.get();
		}
		else
		{
			publisher = new Publisher(dto.publisher().mail(), dto.publisher().company());
			publisher = pubRepo.save(publisher);
		}
		
		Book book = new Book(dto);
		book.setAuthor(author);
		book.setPublisher(publisher);
		
		bookRepo.save(book);
		return HttpResponse.ok("Successfully created");
	}
	
	@Get()
	List<Book> findAll()
	{
		return bookRepo.findAll();
	}
		
}

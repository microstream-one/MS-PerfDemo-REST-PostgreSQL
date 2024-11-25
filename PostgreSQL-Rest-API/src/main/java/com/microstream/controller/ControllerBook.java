package com.microstream.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.microstream.bookstore.domain.Address;
import com.microstream.bookstore.domain.Author;
import com.microstream.bookstore.domain.Book;
import com.microstream.bookstore.domain.Publisher;
import com.microstream.bookstore.dto.DTOBook;
import com.microstream.bookstore.repository.AuthorPredicateProvider;
import com.microstream.bookstore.repository.BookPredicateProvider;
import com.microstream.bookstore.repository.PublisherPredicateProvider;
import com.microstream.bookstore.repository.RepoAddress;
import com.microstream.bookstore.repository.RepoAuthors;
import com.microstream.bookstore.repository.RepoBooks;
import com.microstream.bookstore.repository.RepoPublisher;

import io.micrometer.observation.annotation.Observed;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Put;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;


@Controller("/books")
@Observed
public class ControllerBook
{
	private static final int	LIST_LIMIT	= 100;
	
	protected RepoBooks			bookRepo;
	protected RepoAuthors		authorRepo;
	protected RepoPublisher		pubRepo;
	protected RepoAddress		addressRepo;
	
	public ControllerBook(RepoBooks bookRepo, RepoAuthors authorRepo, RepoPublisher pubRepo, RepoAddress addressRepo)
	{
		this.bookRepo = bookRepo;
		this.authorRepo = authorRepo;
		this.pubRepo = pubRepo;
		this.addressRepo = addressRepo;
	}
	
	@Put
	HttpResponse<String> create(@NonNull @Valid @Body DTOBook dto)
	{
		Book book = internalGetBook(dto);
		book.getAuthor().getAddresses().forEach(a -> addressRepo.save(a));
		book.getPublisher().getAddresses().forEach(a -> addressRepo.save(a));
		
		bookRepo.save(book);
		return HttpResponse.ok("Successfully created");
	}
	
	@Put("/batch")
	HttpResponse<String> createBatch(@NotEmpty @Body List<@Valid @NonNull DTOBook> dto)
	{
		bookRepo.saveAll(dto.stream().map(this::internalGetBook).toList());
		return HttpResponse.ok("Successfully created");
	}
	
	@Get
	List<Book> findAll()
	{
		// TODO: Find out how to use Pageable here
		return bookRepo.list().stream().limit(LIST_LIMIT).toList();
	}
	
	@Get("/byID/{id}")
	Book findByID(@PathVariable final String id)
	{
		Book book = bookRepo.findById(Integer.valueOf(id)).get();
		return book;
	}
	
	@Get("/search/{title}")
	List<Book> findByTitle(@PathVariable final String title)
	{
		List<Book> searchBooksByTitle = bookRepo.searchBooksByTitle("%" + title + "%");
		
		return searchBooksByTitle;
	}
	
	@Get("/{isbn}")
	Book findByIsbn(@PathVariable final String isbn)
	{
		@NonNull
		Optional<Book> optional = bookRepo.findOne(BookPredicateProvider.ISBNquals(isbn));
		
		if(optional.isPresent())
		{
			Book b = optional.get();
			List<Address> adressesByAuthorID = addressRepo.adressesByAuthorID(b.getAuthor().getId());
			b.getAuthor().setAddresses(adressesByAuthorID);
			List<Address> adressesByPublisherID = addressRepo.adressesByPublisherID(b.getPublisher().getId());
			b.getPublisher().setAddresses(adressesByPublisherID);
			
			return optional.get();
		}
		else
		{
			return null;
		}
	}
	
	private Book internalGetBook(final DTOBook dto)
	{
		final var author = authorRepo.findOne(AuthorPredicateProvider.emailEquals(dto.author().mail())).orElseGet(() ->
		{
			Author newAuthor = new Author(
				dto.author().mail(),
				dto.author().firstname(),
				dto.author().lastname(),
				new ArrayList<Address>());
			
			authorRepo.save(newAuthor);
			
			dto.author().addresses().stream().forEach(ad ->
			{
				Address address = new Address(ad);
				address.setAuthor(newAuthor);
				
				addressRepo.save(address);
			});
			
			return newAuthor;
		});
		
		final var publisher =
			pubRepo.findOne(PublisherPredicateProvider.emailEquals(dto.publisher().mail())).orElseGet(() ->
			{
				
				Publisher newPublisher = new Publisher(
					dto.publisher().mail(),
					dto.publisher().company(),
					new ArrayList<Address>());
				
				pubRepo.save(newPublisher);
				
				dto.publisher().addresses().stream().forEach(ad ->
				{
					Address address = new Address(ad);
					address.setPublisher(newPublisher);
					
					addressRepo.save(address);
				});
				
				return newPublisher;
			});
		
		Book book = new Book(dto);
		book.setAuthor(author);
		book.setPublisher(publisher);
		return book;
	}
}

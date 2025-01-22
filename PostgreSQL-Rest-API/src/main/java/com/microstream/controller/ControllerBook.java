package com.microstream.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.microstream.bookstore.domain.Address;
import com.microstream.bookstore.domain.Author;
import com.microstream.bookstore.domain.Book;
import com.microstream.bookstore.domain.Publisher;
import com.microstream.bookstore.domain.insert.AddressInsert;
import com.microstream.bookstore.domain.insert.AuthorInsert;
import com.microstream.bookstore.domain.insert.BookInsert;
import com.microstream.bookstore.domain.insert.PublisherInsert;
import com.microstream.bookstore.dto.DTOBook;
import com.microstream.bookstore.repository.AuthorPredicateProvider;
import com.microstream.bookstore.repository.BookPredicateProvider;
import com.microstream.bookstore.repository.PublisherPredicateProvider;
import com.microstream.bookstore.repository.RepoAddress;
import com.microstream.bookstore.repository.RepoAuthors;
import com.microstream.bookstore.repository.RepoBooks;
import com.microstream.bookstore.repository.RepoPublisher;
import com.microstream.bookstore.repository.insertPM.AuthorPredicateProviderInsertPM;
import com.microstream.bookstore.repository.insertPM.PublisherPredicateProviderInsertPM;
import com.microstream.bookstore.repository.insertPM.RepoAddressInsert;
import com.microstream.bookstore.repository.insertPM.RepoAuthorsInsert;
import com.microstream.bookstore.repository.insertPM.RepoBooksInsert;
import com.microstream.bookstore.repository.insertPM.RepoPublisherInsert;

import io.micrometer.observation.annotation.Observed;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Put;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;


@Controller("/books")
@Observed
public class ControllerBook
{
	private static final int		LIST_LIMIT	= 100;
	
	protected RepoBooks				bookRepo;
	protected RepoAuthors			authorRepo;
	protected RepoPublisher			pubRepo;
	protected RepoAddress			addressRepo;
	
	protected RepoBooksInsert		bookRepoInsert;
	protected RepoAuthorsInsert		authorRepoInsert;
	protected RepoPublisherInsert	pubRepoInsert;
	protected RepoAddressInsert		addressRepoInsert;
	
	public ControllerBook(
		RepoBooks bookRepo,
		RepoAuthors authorRepo,
		RepoPublisher pubRepo,
		RepoAddress addressRepo,
		RepoBooksInsert bookRepoInsert,
		RepoAuthorsInsert authorRepoInsert,
		RepoPublisherInsert pubRepoInsert,
		RepoAddressInsert addressRepoInsert)
	{
		this.bookRepo = bookRepo;
		this.authorRepo = authorRepo;
		this.pubRepo = pubRepo;
		this.addressRepo = addressRepo;
		
		this.bookRepoInsert = bookRepoInsert;
		this.authorRepoInsert = authorRepoInsert;
		this.pubRepoInsert = pubRepoInsert;
		this.addressRepoInsert = addressRepoInsert;
	}
	
	@Put
	HttpResponse<String> create(@NonNull @Valid @Body DTOBook dto)
	{
		Book book = createBook(dto);
		book.getAuthor().getAddresses().forEach(a -> addressRepo.save(a));
		book.getPublisher().getAddresses().forEach(a -> addressRepo.save(a));
		
		bookRepo.save(book);
		return HttpResponse.ok("Successfully created");
	}
	
	@Put("/insertPM")
	HttpResponse<String> createInsertPM(@NonNull @Valid @Body DTOBook dto)
	{
		BookInsert book = createBookInsertPM(dto);
		book.getAuthor().getAddresses().forEach(a -> addressRepoInsert.save(a));
		book.getPublisher().getAddresses().forEach(a -> addressRepoInsert.save(a));
		
		bookRepoInsert.save(book);
		return HttpResponse.ok("Successfully created");
	}
	
	@Put("/batch")
	HttpResponse<String> createBatch(@NotEmpty @Body List<@Valid @NonNull DTOBook> dto)
	{
		bookRepo.saveAll(dto.stream().map(this::createBook).toList());
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
	
	@Get("/count")
	Long countBooks()
	{
		long count = bookRepo.count();
		return count;
	}
	
	@Get("/countInsertPM")
	Long countBooksInsertPM()
	{
		long count = bookRepoInsert.count();
		return count;
	}
	
	@Get("/{isbn}")
	DTOBook findByIsbn(@PathVariable final String isbn)
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
			
			return DTOBook.map(b);
		}
		else
		{
			return null;
		}
	}
	
	@Delete("/flushdatabase")
	HttpResponse<String> flushDatabase()
	{
		bookRepo.deleteAll();
		addressRepo.deleteAll();
		authorRepo.deleteAll();
		pubRepo.deleteAll();
		
		
		return HttpResponse.ok("Data successfully deleted");
	}
	
	@Delete("/flushinsertPM")
	HttpResponse<String> flushInsertPM()
	{
		addressRepoInsert.deleteAll();
		bookRepoInsert.deleteAll();
		authorRepoInsert.deleteAll();
		pubRepoInsert.deleteAll();
		
		return HttpResponse.ok("Data successfully deleted");
	}
	
	private Book createBook(final DTOBook dto)
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
	
	private BookInsert createBookInsertPM(final DTOBook dto)
	{
		final var author = authorRepoInsert.findOne(AuthorPredicateProviderInsertPM.emailEquals(dto.author().mail())).orElseGet(() ->
		{
			AuthorInsert newAuthor = new AuthorInsert(
				dto.author().mail(),
				dto.author().firstname(),
				dto.author().lastname(),
				new ArrayList<AddressInsert>());
			
			authorRepoInsert.save(newAuthor);
			
			dto.author().addresses().stream().forEach(ad ->
			{
				AddressInsert address = new AddressInsert(ad);
				address.setAuthor(newAuthor);
				
				addressRepoInsert.save(address);
			});
			
			return newAuthor;
		});
		
		final var publisher =
			pubRepoInsert.findOne(PublisherPredicateProviderInsertPM.emailEquals(dto.publisher().mail())).orElseGet(() ->
			{
				
				PublisherInsert newPublisher = new PublisherInsert(
					dto.publisher().mail(),
					dto.publisher().company(),
					new ArrayList<AddressInsert>());
				
				pubRepoInsert.save(newPublisher);
				
				dto.publisher().addresses().stream().forEach(ad ->
				{
					AddressInsert address = new AddressInsert(ad);
					address.setPublisher(newPublisher);
					
					addressRepoInsert.save(address);
				});
				
				return newPublisher;
			});
		
		BookInsert book = new BookInsert(dto);
		book.setAuthor(author);
		book.setPublisher(publisher);
		return book;
	}
}

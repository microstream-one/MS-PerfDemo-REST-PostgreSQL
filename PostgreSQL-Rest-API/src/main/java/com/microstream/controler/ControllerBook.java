package com.microstream.controler;

import java.util.List;
import java.util.stream.Collectors;

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
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.server.exceptions.NotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

@Controller("/books")
@Observed
public class ControllerBook
{
	private static final int LIST_LIMIT = 100;

	protected RepoBooks bookRepo;
	protected RepoAuthors authorRepo;
	protected RepoPublisher pubRepo;
	protected RepoAddress addressRepo;

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
		bookRepo.save(internalGetBook(dto));
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
		return bookRepo.findAll(Pageable.from(0, LIST_LIMIT)).getContent();
	}

	@Get("/search/{title}")
	List<Book> findByTitle(@PathVariable final String title)
	{
		return bookRepo.findAll(BookPredicateProvider.titleContains(title));
	}

	@Get("/search/author/{name}")
	List<Book> findByAuthorName(@PathVariable final String name)
	{
		final var author = authorRepo.findOne(AuthorPredicateProvider.nameContains(name))
			.orElseThrow(NotFoundException::new);
		return bookRepo.findAll(BookPredicateProvider.authorIdEquals(author));
	}

	@Get("/{isbn}")
	Book findByIsbn(@PathVariable final String isbn)
	{
		return bookRepo.findOne(BookPredicateProvider.isbnEquals(isbn)).orElse(null);
	}

	private Book internalGetBook(final DTOBook dto)
	{
		final var author = authorRepo.findOne(AuthorPredicateProvider.mailEquals(dto.author().mail()))
			.orElseGet(
				() -> authorRepo.save(
					new Author(
						dto.author().mail(),
						dto.author().firstname(),
						dto.author().lastname(),
						dto.author()
							.addresses()
							.stream()
							.map(a -> addressRepo.save(new Address(a)))
							.collect(Collectors.toList())
					)
				)
			);
		final var publisher = pubRepo.findOne(PublisherPredicateProvider.mailEquals(dto.publisher().mail()))
			.orElseGet(
				() -> pubRepo.save(
					new Publisher(
						dto.publisher().mail(),
						dto.publisher().company(),
						dto.publisher()
							.addresses()
							.stream()
							.map(a -> addressRepo.save(new Address(a)))
							.collect(Collectors.toList())
					)
				)
			);

		Book book = new Book(dto);
		book.setAuthor(author);
		book.setPublisher(publisher);
		return book;
	}
}

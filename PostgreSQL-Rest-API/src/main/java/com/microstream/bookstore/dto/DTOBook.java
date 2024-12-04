package com.microstream.bookstore.dto;

import java.time.LocalDate;

import com.microstream.bookstore.domain.Book;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;


@Serdeable
@Introspected
public record DTOBook(
	@NotBlank String isbn,
	@NotBlank String title,
	@NonNull LocalDate publicationDate,
	int edition,
	int availableQuantity,
	double price,
	@NonNull @Valid DTOAuthor author,
	@NonNull @Valid DTOPublisher publisher)
{
	public static DTOBook map(Book b)
	{
		DTOBook dtoBook = new DTOBook(b.getIsbn(), b.getTitle(), b.getPublicationDate(), b.getEdition(),
			b.getAvailableQuantity(),
			b.getPrice().doubleValue(),
			DTOAuthor.map(b.getAuthor()),
			DTOPublisher.map(b.getPublisher()));
		
		return dtoBook;
	}
}

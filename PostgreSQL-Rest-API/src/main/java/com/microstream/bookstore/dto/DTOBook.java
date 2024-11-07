package com.microstream.bookstore.dto;

import java.time.LocalDate;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Serdeable
@Introspected
public record DTOBook(
	@NotBlank String ISBN,
	@NotBlank String title,
	@NonNull LocalDate publicationDate,
	int edition,
	int availableQuantity,
	double price,
	@NonNull @Valid DTOAuthor author,
	@NonNull @Valid DTOPublisher publisher
)
{
}

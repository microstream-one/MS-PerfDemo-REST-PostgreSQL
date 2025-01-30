package com.microstream.bookstore.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.microstream.bookstore.domain.Address;
import com.microstream.bookstore.domain.Author;
import com.microstream.bookstore.domain.Book;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Serdeable
@Introspected
public record DTOAuthor(
	@NotBlank String mail,
	@NotBlank String firstname,
	@NotBlank String lastname,
	@NotEmpty List<@Valid @NonNull DTOAddress> addresses
)
{
	public static DTOAuthor map(Author a)
	{
		List<DTOAddress> addresses =
			a.getAddresses().stream().map(address -> DTOAddress.map(address)).collect(Collectors.toList());
		return new DTOAuthor(a.getMail(), a.getFirstname(), a.getLastname(), addresses);
	}
}

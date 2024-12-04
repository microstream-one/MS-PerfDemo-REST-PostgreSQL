package com.microstream.bookstore.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.microstream.bookstore.domain.Author;
import com.microstream.bookstore.domain.Publisher;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


@Serdeable
@Introspected
public record DTOPublisher(
	@NotBlank String mail,
	@NotBlank String company,
	@NotEmpty List<@Valid @NonNull DTOAddress> addresses)
{
	public static DTOPublisher map(Publisher p)
	{
		List<DTOAddress> addresses =
			p.getAddresses().stream().map(address -> DTOAddress.map(address)).collect(Collectors.toList());
		return new DTOPublisher(p.getMail(), p.getCompany(), addresses);
	}
}

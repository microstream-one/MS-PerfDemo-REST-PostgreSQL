package com.microstream.bookstore.dto;

import java.util.List;

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
	@NotEmpty List<@Valid @NonNull DTOAddress> addresses
)
{

}

package com.microstream.bookstore.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;

@Serdeable
@Introspected
public record DTOAddress(@NotBlank String address, @Nullable String address2, @NotBlank String zip, @NotBlank String city, @NotBlank String country)
{

}

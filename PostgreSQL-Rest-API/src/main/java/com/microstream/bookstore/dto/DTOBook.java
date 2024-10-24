package com.microstream.bookstore.dto;

import java.time.LocalDate;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record DTOBook(String ISBN, String title, LocalDate publicationDate, 
	int	edition,int availableQuantity, double price, DTOAuthor author, DTOPublisher publisher)
{
	
}

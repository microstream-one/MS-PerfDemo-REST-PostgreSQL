package com.microstream.bookstore.domain.insert;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.microstream.bookstore.dto.DTOBook;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


/**
 * Book entity for insert performance measuring
 */
@Serdeable
@Entity
@Cacheable(true)
@Table(name = "booksinsert", schema = "public")
public class BookInsert
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) private int		id;
	@NotNull private String												isbn;
	@NotNull private String												title;
	@NotNull private LocalDate											publicationDate;
	@NotNull private int												edition;
	@NotNull private int												availableQuantity;
	@NotNull private BigDecimal											price;
	
	@ManyToOne private AuthorInsert										author;
	@ManyToOne private PublisherInsert									publisher;
	
	public BookInsert()
	{
	}
	
	public BookInsert(DTOBook dto)
	{
		this.isbn = dto.isbn();
		this.title = dto.title();
		this.publicationDate = dto.publicationDate();
		this.edition = dto.edition();
		this.availableQuantity = dto.availableQuantity();
		this.price = BigDecimal.valueOf(dto.price());
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getIsbn()
	{
		return isbn;
	}
	
	public void setIsbn(String isbn)
	{
		this.isbn = isbn;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public LocalDate getPublicationDate()
	{
		return publicationDate;
	}
	
	public void setPublicationDate(LocalDate publicationDate)
	{
		this.publicationDate = publicationDate;
	}
	
	public int getEdition()
	{
		return edition;
	}
	
	public void setEdition(int edition)
	{
		this.edition = edition;
	}
	
	public int getAvailableQuantity()
	{
		return availableQuantity;
	}
	
	public void setAvailableQuantity(int availableQuantity)
	{
		this.availableQuantity = availableQuantity;
	}
	
	public BigDecimal getPrice()
	{
		return price;
	}
	
	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}
	
	public AuthorInsert getAuthor()
	{
		return author;
	}
	
	public void setAuthor(AuthorInsert author)
	{
		this.author = author;
	}
	
	public PublisherInsert getPublisher()
	{
		return publisher;
	}
	
	public void setPublisher(PublisherInsert publisher)
	{
		this.publisher = publisher;
	}
}

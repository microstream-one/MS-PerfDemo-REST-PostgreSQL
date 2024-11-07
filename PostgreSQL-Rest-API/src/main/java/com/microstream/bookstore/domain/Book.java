
package com.microstream.bookstore.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.microstream.bookstore.dto.DTOBook;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.GeneratedValue.Type;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * Book
 */
@Serdeable
@Entity
@Cacheable(true)
@Table(name = "books", schema = "public")
public class Book implements java.io.Serializable
{
	@Id
	@GeneratedValue(Type.IDENTITY)
	private int id;
	@NotNull
	private String isbn;
	@NotNull
	private String title;
	@NotNull
	private LocalDate publicationDate;
	@NotNull
	private int edition;
	@NotNull
	private int availableQuantity;
	@NotNull
	private BigDecimal price;

	@ManyToOne
	private Author author;
	@ManyToOne
	private Publisher publisher;

	public Book()
	{
	}

	public Book(DTOBook dto)
	{
		this.isbn = dto.ISBN();
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

	public Author getAuthor()
	{
		return author;
	}

	public void setAuthor(Author author)
	{
		this.author = author;
	}

	public Publisher getPublisher()
	{
		return publisher;
	}

	public void setPublisher(Publisher publisher)
	{
		this.publisher = publisher;
	}
}

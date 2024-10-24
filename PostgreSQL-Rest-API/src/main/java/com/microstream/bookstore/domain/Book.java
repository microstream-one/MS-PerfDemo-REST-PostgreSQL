
package com.microstream.bookstore.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.microstream.bookstore.dto.DTOBook;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
	
	@Id private String			ISBN;
	@NotNull private String		title;
	@NotNull private LocalDate	publicationDate;
	@NotNull private int		edition;
	@NotNull private int		availableQuantity;
	@NotNull private BigDecimal	price;

	// private Author author;
	// private Publisher publisher;
	
	public Book()
	{
		// TODO Auto-generated constructor stub
	}
	
	public Book(DTOBook dto)
	{	
		this.ISBN = dto.ISBN();
		this.title = dto.title();
		this.publicationDate = dto.publicationDate();
		this.edition = dto.edition();
		this.availableQuantity = dto.availableQuantity();
		this.price = new BigDecimal(dto.price());
		
//		 this.author = new Author(dto.author().mail(), dto.author().firstname(), dto.author().lastname());
//		 this.publisher = new Publisher(dto.publisher().mail(), dto.publisher().company());
	}
		
	
	public void setISBN(String iSBN)
	{
		ISBN = iSBN;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setPublicationDate(LocalDate publicationDate)
	{
		this.publicationDate = publicationDate;
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
	
	public String getISBN()
	{
		return ISBN;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public LocalDate getPublicationDate()
	{
		return publicationDate;
	}
	
	public int getEdition()
	{
		return edition;
	}
	
	// public Author getAuthor()
	// {
	// return author;
	// }
	//
	// public Publisher getPublisher()
	// {
	// return publisher;
	// }
}

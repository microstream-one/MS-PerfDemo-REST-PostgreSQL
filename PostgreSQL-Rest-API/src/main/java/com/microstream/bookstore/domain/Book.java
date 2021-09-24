
package com.microstream.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.microstream.bookstore.dal.BookDAO;
import com.rapidclipse.framework.server.data.DAO;
import com.rapidclipse.framework.server.resources.Caption;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Book
 */
@DAO(BookDAO.class)
@Caption("{%isbn13}")
@Entity
@Cacheable(true)
@Table(name = "book", schema = "public")
public class Book implements java.io.Serializable
{
	@JsonIgnore
	private long				id;
	private Author				author;
	@JsonIgnore
	private Genre				genre;
	@JsonIgnore
	private Language			language;
	@JsonIgnore
	private Publisher			publisher;
	private String				isbn13;
	@JsonIgnore
	private Double				purchasePrice;
	@JsonIgnore
	private Double				retailPrice;
	private String				title;
	@JsonIgnore
	private Set<Purchaseitem>	purchaseitems	= new HashSet<>(0);
	@JsonIgnore
	private Set<Inventoryitem>	inventoryitems	= new HashSet<>(0);
	
	public Book()
	{
	}
	
	@Caption("Id")
	@Id
	
	@Column(name = "id", unique = true, nullable = false, columnDefinition = "bigserial")
	public long getId()
	{
		return this.id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	@Caption("Author")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "author_id", columnDefinition = "int8")
	@JsonSerialize
	public Author getAuthor()
	{
		return this.author;
	}
	
	public void setAuthor(Author author)
	{
		this.author = author;
	}
	
	@Caption("Genre")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "genre_id", columnDefinition = "int8")
	@JsonIgnore
	public Genre getGenre()
	{
		return this.genre;
	}
	
	public void setGenre(Genre genre)
	{
		this.genre = genre;
	}
	
	@Caption("Language")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "language_id", columnDefinition = "int8")
	@JsonIgnore
	public Language getLanguage()
	{
		return this.language;
	}
	
	public void setLanguage(Language language)
	{
		this.language = language;
	}
	
	@Caption("Publisher")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "publisher_id", columnDefinition = "int8")
	@JsonIgnore
	public Publisher getPublisher()
	{
		return this.publisher;
	}
	
	public void setPublisher(Publisher publisher)
	{
		this.publisher = publisher;
	}
	
	@Caption("Isbn13")
	@Column(name = "isbn13", columnDefinition = "varchar")
	@JsonSerialize
	public String getIsbn13()
	{
		return this.isbn13;
	}
	
	public void setIsbn13(String isbn13)
	{
		this.isbn13 = isbn13;
	}
	
	@Caption("PurchasePrice")
	@Column(name = "purchase_price", columnDefinition = "float8", precision = 17, scale = 17)
	@JsonIgnore
	public Double getPurchasePrice()
	{
		return this.purchasePrice;
	}
	
	public void setPurchasePrice(Double purchasePrice)
	{
		this.purchasePrice = purchasePrice;
	}
	
	@Caption("RetailPrice")
	@Column(name = "retail_price", columnDefinition = "float8", precision = 17, scale = 17)
	@JsonIgnore
	public Double getRetailPrice()
	{
		return this.retailPrice;
	}
	
	public void setRetailPrice(Double retailPrice)
	{
		this.retailPrice = retailPrice;
	}
	
	@Caption("Title")
	@Column(name = "title", columnDefinition = "varchar")
	@JsonSerialize
	public String getTitle()
	{
		return this.title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	@Caption("Purchaseitems")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	@JsonIgnore
	public Set<Purchaseitem> getPurchaseitems()
	{
		return this.purchaseitems;
	}
	
	public void setPurchaseitems(Set<Purchaseitem> purchaseitems)
	{
		this.purchaseitems = purchaseitems;
	}
	
	@Caption("Inventoryitems")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	@JsonIgnore
	public Set<Inventoryitem> getInventoryitems()
	{
		return this.inventoryitems;
	}
	
	public void setInventoryitems(Set<Inventoryitem> inventoryitems)
	{
		this.inventoryitems = inventoryitems;
	}
	
}

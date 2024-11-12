
package com.microstream.bookstore.domain;

import com.microstream.bookstore.dto.DTOAddress;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


/**
 * Address
 */
@Serdeable
@Entity
@Cacheable(true)
@Table(name = "addresses", schema = "public")
public class Address
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) private int							id;
	@NotNull private String																	address;
	private String																			address2;
	@NotNull private String																	zip;
	@NotNull private String																	city;
	@NotNull private String																	country;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publisher_id") private Publisher									publisher;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id") private Author											author;
	
	public Address()
	{
	}
	
	public Address(String address, String address2, String zip, String city, String country)
	{
		super();
		this.address = address;
		this.address2 = address2;
		this.zip = zip;
		this.city = city;
		this.country = country;
	}
	
	public Address(DTOAddress dto)
	{
		this.address = dto.address();
		this.address2 = dto.address2();
		this.zip = dto.zip();
		this.city = dto.city();
		this.country = dto.country();
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public String getAddress2()
	{
		return address2;
	}
	
	public void setAddress2(String address2)
	{
		this.address2 = address2;
	}
	
	public String getZip()
	{
		return zip;
	}
	
	public void setZip(String zip)
	{
		this.zip = zip;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public String getCountry()
	{
		return country;
	}
	
	public void setCountry(String country)
	{
		this.country = country;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public Publisher getPublisher()
	{
		return publisher;
	}
	
	public void setPublisher(Publisher publisher)
	{
		this.publisher = publisher;
	}
	
	public Author getAuthor()
	{
		return author;
	}
	
	public void setAuthor(Author author)
	{
		this.author = author;
	}
	
}

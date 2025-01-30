package com.microstream.bookstore.domain.insert;

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
 * Address entity for insert performance measuring
 */
@Serdeable
@Entity
@Cacheable(true)
@Table(name = "addressesinsert", schema = "public")
public class AddressInsert
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) private int									id;
	@NotNull private String																			address;
	private String																					address2;
	@NotNull private String																			zip;
	@NotNull private String																			city;
	@NotNull private String																			country;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publisher_id") private PublisherInsert										publisher;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id") private AuthorInsert											author;
	
	public AddressInsert()
	{
	}
	
	public AddressInsert(String address, String address2, String zip, String city, String country)
	{
		super();
		this.address = address;
		this.address2 = address2;
		this.zip = zip;
		this.city = city;
		this.country = country;
	}
	
	public AddressInsert(DTOAddress dto)
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
	
	public PublisherInsert getPublisher()
	{
		return publisher;
	}
	
	public void setPublisher(PublisherInsert publisher)
	{
		this.publisher = publisher;
	}
	
	public AuthorInsert getAuthor()
	{
		return author;
	}
	
	public void setAuthor(AuthorInsert author)
	{
		this.author = author;
	}
	
}

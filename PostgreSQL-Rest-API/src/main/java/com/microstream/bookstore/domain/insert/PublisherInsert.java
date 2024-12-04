package com.microstream.bookstore.domain.insert;

import java.util.ArrayList;
import java.util.List;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * Publisher entity for insert performance measuring
 */
@Serdeable
@Entity
@Cacheable(true)
@Table(name = "publisherinsert", schema = "public")
public class PublisherInsert
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String mail;
	@NotNull
	private String company;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publisher")
	private List<AddressInsert> addresses = new ArrayList<>();
	
	public PublisherInsert()
	{
		
	}

	public PublisherInsert(String mail, String company, List<AddressInsert> addresses)
	{
		this.mail = mail;
		this.company = company;
		this.addresses = addresses;
	}

	public String getMail()
	{
		return mail;
	}

	public void setMail(String mail)
	{
		this.mail = mail;
	}

	public String getCompany()
	{
		return company;
	}

	public void setCompany(String company)
	{
		this.company = company;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public List<AddressInsert> getAddresses()
	{
		return addresses;
	}

	public void setAddresses(List<AddressInsert> addresses)
	{
		this.addresses = addresses;
	}
}

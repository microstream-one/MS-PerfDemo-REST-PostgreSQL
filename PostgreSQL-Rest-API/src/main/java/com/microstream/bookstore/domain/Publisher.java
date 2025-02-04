
package com.microstream.bookstore.domain;

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

@Serdeable
@Entity
@Cacheable(true)
@Table(name = "publisher", schema = "public")
public class Publisher
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String mail;
	@NotNull
	private String company;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publisher")
	private List<Address> addresses = new ArrayList<>();
	
	public Publisher()
	{
		
	}

	public Publisher(String mail, String company, List<Address> addresses)
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

	public List<Address> getAddresses()
	{
		return addresses;
	}

	public void setAddresses(List<Address> addresses)
	{
		this.addresses = addresses;
	}
}

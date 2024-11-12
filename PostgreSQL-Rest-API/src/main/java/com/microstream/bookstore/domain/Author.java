
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

/**
 * Author
 */
@Serdeable
@Entity
@Cacheable(true)
@Table(name = "author", schema = "public")
public class Author implements java.io.Serializable
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String mail;
	@NotNull
	private String firstname;
	@NotNull
	private String lastname;
	@NotNull
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
	private List<Address> addresses = new ArrayList<>();

	public Author()
	{
		this.addresses = new ArrayList<>();
	}

	public Author(String mail, String firstname, String lastname, List<Address> addresses)
	{
		this.mail = mail;
		this.firstname = firstname;
		this.lastname = lastname;
		this.addresses = addresses;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getMail()
	{
		return mail;
	}

	public void setMail(String mail)
	{
		this.mail = mail;
	}

	public String getFirstname()
	{
		return firstname;
	}

	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}

	public String getLastname()
	{
		return lastname;
	}

	public void setLastname(String lastname)
	{
		this.lastname = lastname;
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

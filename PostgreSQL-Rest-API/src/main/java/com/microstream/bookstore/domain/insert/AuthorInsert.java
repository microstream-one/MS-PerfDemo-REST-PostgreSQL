package com.microstream.bookstore.domain.insert;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
 * Author entity for insert performance measuring
 */
@Serdeable
@Entity
@Cacheable(true)
@Table(name = "authorinsert", schema = "public")
public class AuthorInsert
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) private int		id;
	@NotNull private String												mail;
	@NotNull private String												firstname;
	@NotNull private String												lastname;
	
	@NotNull
	@OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	private List<AddressInsert>				addresses	=
		new ArrayList<>();
	
	public AuthorInsert()
	{
		this.addresses = new ArrayList<>();
	}
	
	public AuthorInsert(String mail, String firstname, String lastname, List<AddressInsert> addresses)
	{
		this.mail = mail;
		this.firstname = firstname;
		this.lastname = lastname;
		this.addresses = addresses;
		
		for(AddressInsert address : addresses)
		{
			address.setAuthor(this);
		}
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
	
	public List<AddressInsert> getAddresses()
	{
		return addresses;
	}
	
	public void setAddresses(List<AddressInsert> addresses)
	{
		this.addresses = addresses;
	}
}

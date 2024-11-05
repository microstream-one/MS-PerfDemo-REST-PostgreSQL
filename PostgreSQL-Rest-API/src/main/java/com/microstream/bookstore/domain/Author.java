
package com.microstream.bookstore.domain;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
	@GeneratedValue private int		id;
	@NotNull private String			mail;
	@NotNull private String			firstname;
	@NotNull private String			lastname;
	
	public Author()
	{
		super();
	}
	
	public Author(String mail, String firstname, String lastname)
	{
		super();
		this.mail = mail;
		this.firstname = firstname;
		this.lastname = lastname;
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
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
}

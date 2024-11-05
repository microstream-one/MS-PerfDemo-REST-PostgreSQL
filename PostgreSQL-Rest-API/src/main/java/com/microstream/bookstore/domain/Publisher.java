
package com.microstream.bookstore.domain;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Serdeable
@Entity
@Cacheable(true)
@Table(name = "publisher", schema = "public")
public class Publisher
{
	
	@Id
	@GeneratedValue private int		id;
	@NotNull private String			mail;
	@NotNull private String			company;
	
	public Publisher()
	{
		super();
	}
	
	public Publisher(String mail, String company)
	{
		this.mail = mail;
		this.company = company;
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
	
}

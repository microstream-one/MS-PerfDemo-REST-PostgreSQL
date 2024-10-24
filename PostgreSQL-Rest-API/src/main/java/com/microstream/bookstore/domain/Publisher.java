
package com.microstream.bookstore.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



//@Entity
//@Cacheable(true)
//@Table(name = "publisher", schema = "public")
public class Publisher implements java.io.Serializable
{
	
	private final String	UUID	= java.util.UUID.randomUUID().toString();
	private String			mail;
	private String			company;
	
	public Publisher(String mail, String company)
	{
		super();
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
	
	public String getUUID()
	{
		return UUID;
	}
}

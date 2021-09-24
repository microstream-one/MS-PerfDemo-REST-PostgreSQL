
package com.microstream.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.microstream.bookstore.dal.AuthorDAO;
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
 * Author
 */
@DAO(AuthorDAO.class)
@Caption("{%name}")
@Entity
@Cacheable(true)
@Table(name = "author", schema = "public")
public class Author implements java.io.Serializable
{
	@JsonIgnore
	private long		id;
	@JsonIgnore
	private Address		address;
	private String		name;
	@JsonIgnore
	private Set<Book>	books	= new HashSet<>(0);
	
	public Author()
	{
	}
	
	@Caption("Id")
	@Id
	
	@Column(name = "id", unique = true, nullable = false, columnDefinition = "bigserial")
	@JsonIgnore
	public long getId()
	{
		return this.id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	@Caption("Address")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id", columnDefinition = "int8")
	@JsonIgnore
	public Address getAddress()
	{
		return this.address;
	}
	
	public void setAddress(Address address)
	{
		this.address = address;
	}
	
	@Caption("Name")
	@Column(name = "name", columnDefinition = "varchar")
	@JsonSerialize
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	@Caption("Books")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
	@JsonIgnore
	public Set<Book> getBooks()
	{
		return this.books;
	}
	
	public void setBooks(Set<Book> books)
	{
		this.books = books;
	}
	
}

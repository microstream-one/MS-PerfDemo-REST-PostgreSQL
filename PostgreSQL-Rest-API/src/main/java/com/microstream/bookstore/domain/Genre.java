
package com.microstream.bookstore.domain;

import com.microstream.bookstore.dal.GenreDAO;
import com.rapidclipse.framework.server.data.DAO;
import com.rapidclipse.framework.server.resources.Caption;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Genre
 */
@DAO(GenreDAO.class)
@Caption("{%name}")
@Entity
@Cacheable(true)
@Table(name = "genre", schema = "public")
public class Genre implements java.io.Serializable
{
	
	private long		id;
	private String		name;
	private Set<Book>	books	= new HashSet<>(0);
	
	public Genre()
	{
	}
	
	@Caption("Id")
	@Id
	
	@Column(name = "id", unique = true, nullable = false, columnDefinition = "bigserial")
	public long getId()
	{
		return this.id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	@Caption("Name")
	@Column(name = "name", columnDefinition = "varchar")
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	@Caption("Books")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "genre")
	public Set<Book> getBooks()
	{
		return this.books;
	}
	
	public void setBooks(Set<Book> books)
	{
		this.books = books;
	}
	
}

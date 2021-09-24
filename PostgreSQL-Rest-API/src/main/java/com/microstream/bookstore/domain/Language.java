
package com.microstream.bookstore.domain;

import com.microstream.bookstore.dal.LanguageDAO;
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
 * Language
 */
@DAO(LanguageDAO.class)
@Caption("{%languagetag}")
@Entity
@Cacheable(true)
@Table(name = "language", schema = "public")
public class Language implements java.io.Serializable
{
	
	private long		id;
	private String		languagetag;
	private Set<Book>	books	= new HashSet<>(0);
	
	public Language()
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
	
	@Caption("Languagetag")
	@Column(name = "languagetag", columnDefinition = "varchar")
	public String getLanguagetag()
	{
		return this.languagetag;
	}
	
	public void setLanguagetag(String languagetag)
	{
		this.languagetag = languagetag;
	}
	
	@Caption("Books")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "language")
	public Set<Book> getBooks()
	{
		return this.books;
	}
	
	public void setBooks(Set<Book> books)
	{
		this.books = books;
	}
	
}

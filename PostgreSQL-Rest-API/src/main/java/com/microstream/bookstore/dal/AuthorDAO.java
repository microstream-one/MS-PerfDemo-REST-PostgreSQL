
package com.microstream.bookstore.dal;

import com.microstream.bookstore.domain.Author;
import com.rapidclipse.framework.server.jpa.dal.JpaDataAccessObject;


/**
 * Home object for domain model class Author.
 * 
 * @see Author
 */
public class AuthorDAO extends JpaDataAccessObject.Default<Author, Long>
{
	public final static AuthorDAO INSTANCE = new AuthorDAO();
	
	public AuthorDAO()
	{
		super(Author.class);
	}
}

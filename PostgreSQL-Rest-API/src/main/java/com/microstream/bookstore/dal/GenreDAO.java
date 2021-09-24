
package com.microstream.bookstore.dal;

import com.microstream.bookstore.domain.Genre;
import com.rapidclipse.framework.server.jpa.dal.JpaDataAccessObject;


/**
 * Home object for domain model class Genre.
 * 
 * @see Genre
 */
public class GenreDAO extends JpaDataAccessObject.Default<Genre, Long>
{
	public final static GenreDAO INSTANCE = new GenreDAO();
	
	public GenreDAO()
	{
		super(Genre.class);
	}
}

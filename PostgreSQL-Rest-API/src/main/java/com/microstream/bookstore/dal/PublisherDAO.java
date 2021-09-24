
package com.microstream.bookstore.dal;

import com.microstream.bookstore.domain.Publisher;
import com.rapidclipse.framework.server.jpa.dal.JpaDataAccessObject;


/**
 * Home object for domain model class Publisher.
 * 
 * @see Publisher
 */
public class PublisherDAO extends JpaDataAccessObject.Default<Publisher, Long>
{
	public final static PublisherDAO INSTANCE = new PublisherDAO();
	
	public PublisherDAO()
	{
		super(Publisher.class);
	}
}

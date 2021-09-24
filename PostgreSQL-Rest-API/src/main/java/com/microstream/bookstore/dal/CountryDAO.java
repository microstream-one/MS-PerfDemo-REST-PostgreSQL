
package com.microstream.bookstore.dal;

import com.microstream.bookstore.domain.Country;
import com.rapidclipse.framework.server.jpa.dal.JpaDataAccessObject;


/**
 * Home object for domain model class Country.
 * 
 * @see Country
 */
public class CountryDAO extends JpaDataAccessObject.Default<Country, Long>
{
	public final static CountryDAO INSTANCE = new CountryDAO();
	
	public CountryDAO()
	{
		super(Country.class);
	}
}

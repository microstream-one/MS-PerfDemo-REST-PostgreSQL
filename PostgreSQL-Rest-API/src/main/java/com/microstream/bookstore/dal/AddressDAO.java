
package com.microstream.bookstore.dal;

import com.rapidclipse.framework.server.jpa.dal.JpaDataAccessObject;
import com.microstream.bookstore.domain.Address;


/**
 * Home object for domain model class Address.
 * 
 * @see Address
 */
public class AddressDAO extends JpaDataAccessObject.Default<Address, Long>
{
	public final static AddressDAO INSTANCE = new AddressDAO();
	
	public AddressDAO()
	{
		super(Address.class);
	}
}

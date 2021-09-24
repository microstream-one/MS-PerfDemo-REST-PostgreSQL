
package com.microstream.bookstore.dal;

import com.microstream.bookstore.domain.Purchase;
import com.rapidclipse.framework.server.jpa.dal.JpaDataAccessObject;


/**
 * Home object for domain model class Purchase.
 * 
 * @see Purchase
 */
public class PurchaseDAO extends JpaDataAccessObject.Default<Purchase, Long>
{
	public final static PurchaseDAO INSTANCE = new PurchaseDAO();
	
	public PurchaseDAO()
	{
		super(Purchase.class);
	}
}

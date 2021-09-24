package com.microstream.bookstore.dal;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class PostgreDAO
{
	private EntityManagerFactory emf;
	private static PostgreDAO INSTANCE;

	public PostgreDAO()
	{
		this.emf = Persistence.createEntityManagerFactory("PostgreSQL-Rest-API");
	}

	public EntityManagerFactory getFactory()
	{
		return this.emf;
	}
	
	public static PostgreDAO getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new PostgreDAO();
		}
		return INSTANCE;
	}
}

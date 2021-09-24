
package com.microstream.bookstore.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.microstream.bookstore.domain.Inventoryitem;
import com.rapidclipse.framework.server.jpa.dal.JpaDataAccessObject;


/**
 * Home object for domain model class Inventoryitem.
 *
 * @see Inventoryitem
 */
public class InventoryitemDAO extends JpaDataAccessObject.Default<Inventoryitem, Long> implements AutoCloseable
{
	private static InventoryitemDAO INSTANCE = new InventoryitemDAO();
	private EntityManager entityManager;
	
	public InventoryitemDAO()
	{
		super(Inventoryitem.class);
		this.entityManager = PostgreDAO.getInstance().getFactory().createEntityManager();
	}

	public static InventoryitemDAO getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new InventoryitemDAO();
		}
		return INSTANCE;
	}
	
	public List<Inventoryitem> booksInShop()
	{
		final CriteriaQuery<Inventoryitem> criteriaQuery = this.booksInShopQuery();

		final TypedQuery<Inventoryitem> query = this.entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}
	
	public CriteriaQuery<Inventoryitem> booksInShopQuery()
	{

		final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		final CriteriaQuery<Inventoryitem> criteriaQuery = criteriaBuilder.createQuery(Inventoryitem.class);
		
		criteriaQuery.from(Inventoryitem.class);
		
		return criteriaQuery;
	}
	
	public List<Inventoryitem> bookinStore(final String abc)
	{
		final CriteriaQuery<Inventoryitem> criteriaQuery = this.bookinStoreQuery();
		
		final TypedQuery<Inventoryitem> query = this.entityManager.createQuery(criteriaQuery);
		query.setParameter("abc", abc);
		return query.getResultList();
	}
	
	public CriteriaQuery<Inventoryitem> bookinStoreQuery()
	{
		
		final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		final ParameterExpression<String> abcParameter = criteriaBuilder.parameter(String.class, "abc");
		final CriteriaQuery<Inventoryitem> criteriaQuery = criteriaBuilder.createQuery(Inventoryitem.class);

		final Root<Inventoryitem> root = criteriaQuery.from(Inventoryitem.class);

		criteriaQuery.where(criteriaBuilder.equal(root.get("book").get("isbn13"), abcParameter));

		return criteriaQuery;
	}
	
	@Override
	public void close() throws Exception
	{
		this.entityManager.close();
	}
}

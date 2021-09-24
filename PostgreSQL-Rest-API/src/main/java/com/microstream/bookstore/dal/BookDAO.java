
package com.microstream.bookstore.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.microstream.bookstore.domain.Book;
import com.rapidclipse.framework.server.jpa.dal.JpaDataAccessObject;

import io.micronaut.cache.annotation.Cacheable;


/**
 * Home object for domain model class Book.
 *
 * @see Book
 */
public class BookDAO extends JpaDataAccessObject.Default<Book, Long> implements AutoCloseable
{
	private static BookDAO INSTANCE;
	private EntityManager entityManager;

	public BookDAO()
	{
		super(Book.class);
		this.entityManager = PostgreDAO.getInstance().getFactory().createEntityManager();
	}

	public static BookDAO getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new BookDAO();
		}
		return INSTANCE;
	}
	
	@Cacheable
	public List<Double> avgprice()
	{
		final CriteriaQuery<Double> criteriaQuery = this.avgpriceQuery();
		// EntityManagerFactory emf = Persistence.createEntityManagerFactory("PostgreSQL-Rest-API");
		// this.entityManager = emf.createEntityManager();
		
		final TypedQuery<Double> query = this.entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}
	
	@Cacheable
	public CriteriaQuery<Double> avgpriceQuery()
	{
		final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		final CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
		
		final Root<Book> root = criteriaQuery.from(Book.class);
		
		criteriaQuery.select(criteriaBuilder.avg(root.get("retailPrice")));
		
		criteriaQuery.groupBy(root.get("title"));
		
		return criteriaQuery;
	}
	
	@Cacheable
	public List<Book> findAllBooks()
	{
		final CriteriaQuery<Book> criteriaQuery = this.findAllBooksQuery();
		// EntityManagerFactory emf = Persistence.createEntityManagerFactory("PostgreSQL-Rest-API");
		// this.entityManager = emf.createEntityManager();
		
		final TypedQuery<Book> query = this.entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

	@Cacheable
	public List<Book> findAllLazyBooks()
	{
		System.gc();
		final CriteriaQuery<Book> criteriaQuery = this.findAllBooksQuery();
		// EntityManagerFactory emf = Persistence.createEntityManagerFactory("PostgreSQL-Rest-API");
		// this.entityManager = emf.createEntityManager();
		
		final TypedQuery<Book> query = this.entityManager.createQuery(criteriaQuery);
		List<Book> resultList = query.getResultList();
		this.entityManager.clear();
		
		return resultList;
	}
	
	// final CriteriaQuery<Book> criteriaQuery = this.findAllBooksQuery();
	//// EntityManagerFactory emf = Persistence.createEntityManagerFactory("PostgreSQL-Rest-API");
	//// this.entityManager = emf.createEntityManager();
	//
	//
	// final TypedQuery<Book> query = entityManager.createQuery(criteriaQuery);
	// return query.getResultList();
	
	@Cacheable
	public CriteriaQuery<Book> findAllBooksQuery()
	{
		// EntityManagerFactory emf = Persistence.createEntityManagerFactory("PostgreSQL-Rest-API");
		// final EntityManager entityManager = emf.createEntityManager();
		final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		final CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);

		criteriaQuery.from(Book.class);
		
		return criteriaQuery;
	}
	
	@Override
	public void close() throws Exception
	{
		this.entityManager.close();
	}
	
	//// EntityManagerFactory emf = Persistence.createEntityManagerFactory("PostgreSQL-Rest-API");
	//// final EntityManager entityManager = emf.createEntityManager();
	// final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	// final CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
	//
	// criteriaQuery.from(Book.class);
	//
	//
	// return criteriaQuery;
}

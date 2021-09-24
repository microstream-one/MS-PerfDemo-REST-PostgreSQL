
package com.microstream.bookstore.domain;

import com.microstream.bookstore.dal.PurchaseitemDAO;
import com.rapidclipse.framework.server.data.DAO;
import com.rapidclipse.framework.server.resources.Caption;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Purchaseitem
 */
@DAO(PurchaseitemDAO.class)
@Caption("{%id}")
@Entity
@Cacheable(true)
@Table(name = "purchaseitem", schema = "public")
public class Purchaseitem implements java.io.Serializable
{
	
	private long		id;
	private Book		book;
	private Purchase	purchase;
	private Integer		amount;
	private Double		price;
	
	public Purchaseitem()
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
	
	@Caption("Book")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "book_id", columnDefinition = "int8")
	public Book getBook()
	{
		return this.book;
	}
	
	public void setBook(Book book)
	{
		this.book = book;
	}
	
	@Caption("Purchase")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "purchase_id", columnDefinition = "int8")
	public Purchase getPurchase()
	{
		return this.purchase;
	}
	
	public void setPurchase(Purchase purchase)
	{
		this.purchase = purchase;
	}
	
	@Caption("Amount")
	@Column(name = "amount", columnDefinition = "int4")
	public Integer getAmount()
	{
		return this.amount;
	}
	
	public void setAmount(Integer amount)
	{
		this.amount = amount;
	}
	
	@Caption("Price")
	@Column(name = "price", columnDefinition = "float8", precision = 17, scale = 17)
	public Double getPrice()
	{
		return this.price;
	}
	
	public void setPrice(Double price)
	{
		this.price = price;
	}
	
}

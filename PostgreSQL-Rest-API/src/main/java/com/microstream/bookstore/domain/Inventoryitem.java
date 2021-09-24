
package com.microstream.bookstore.domain;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.microstream.bookstore.dal.InventoryitemDAO;
import com.rapidclipse.framework.server.data.DAO;
import com.rapidclipse.framework.server.resources.Caption;


/**
 * Inventoryitem
 */
@DAO(InventoryitemDAO.class)
@Caption("{%id}")
@Entity
@Cacheable(true)
@Table(name = "inventoryitem", schema = "public")
public class Inventoryitem implements java.io.Serializable
{
	
	private long id;
	private Book book;
	private Shop shop;
	private Integer amount;

	public Inventoryitem()
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

	@Caption("Shop")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "shop_id", columnDefinition = "int8")
	public Shop getShop()
	{
		return this.shop;
	}

	public void setShop(Shop shop)
	{
		this.shop = shop;
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

}

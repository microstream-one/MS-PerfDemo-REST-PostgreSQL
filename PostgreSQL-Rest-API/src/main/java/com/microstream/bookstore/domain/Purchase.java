
package com.microstream.bookstore.domain;

import com.microstream.bookstore.dal.PurchaseDAO;
import com.rapidclipse.framework.server.data.DAO;
import com.rapidclipse.framework.server.resources.Caption;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;


/**
 * Purchase
 */
@DAO(PurchaseDAO.class)
@Caption("{%id}")
@Entity
@Cacheable(true)
@Table(name = "purchase", schema = "public")
public class Purchase implements java.io.Serializable
{
	
	private long				id;
	private Date				timestamp;
	private Customer			customer;
	private Employee			employee;
	private Shop				shop;
	private Set<Purchaseitem>	purchaseitems	= new HashSet<>(0);
	
	public Purchase()
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
	
	@Caption("Timestamp")
	@Version
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "timestamp", columnDefinition = "timestamp", length = 29)
	public Date getTimestamp()
	{
		return this.timestamp;
	}
	
	public void setTimestamp(Date timestamp)
	{
		this.timestamp = timestamp;
	}
	
	@Caption("Customer")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", columnDefinition = "int8")
	public Customer getCustomer()
	{
		return this.customer;
	}
	
	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}
	
	@Caption("Employee")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id", columnDefinition = "int8")
	public Employee getEmployee()
	{
		return this.employee;
	}
	
	public void setEmployee(Employee employee)
	{
		this.employee = employee;
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
	
	@Caption("Purchaseitems")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "purchase")
	public Set<Purchaseitem> getPurchaseitems()
	{
		return this.purchaseitems;
	}
	
	public void setPurchaseitems(Set<Purchaseitem> purchaseitems)
	{
		this.purchaseitems = purchaseitems;
	}
	
}

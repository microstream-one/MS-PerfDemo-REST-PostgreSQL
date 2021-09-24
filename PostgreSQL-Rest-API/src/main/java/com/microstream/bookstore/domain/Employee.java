
package com.microstream.bookstore.domain;

import com.microstream.bookstore.dal.EmployeeDAO;
import com.rapidclipse.framework.server.data.DAO;
import com.rapidclipse.framework.server.resources.Caption;
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


/**
 * Employee
 */
@DAO(EmployeeDAO.class)
@Caption("{%name}")
@Entity
@Cacheable(true)
@Table(name = "employee", schema = "public")
public class Employee implements java.io.Serializable
{
	
	private long			id;
	private Address			address;
	private Shop			shop;
	private String			name;
	private Set<Purchase>	purchases	= new HashSet<>(0);
	
	public Employee()
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
	
	@Caption("Address")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id", columnDefinition = "int8")
	public Address getAddress()
	{
		return this.address;
	}
	
	public void setAddress(Address address)
	{
		this.address = address;
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
	
	@Caption("Name")
	@Column(name = "name", columnDefinition = "varchar")
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	@Caption("Purchases")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
	public Set<Purchase> getPurchases()
	{
		return this.purchases;
	}
	
	public void setPurchases(Set<Purchase> purchases)
	{
		this.purchases = purchases;
	}
	
}

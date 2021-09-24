
package com.microstream.bookstore.domain;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.microstream.bookstore.dal.ShopDAO;
import com.rapidclipse.framework.server.data.DAO;
import com.rapidclipse.framework.server.resources.Caption;


/**
 * Shop
 */
@DAO(ShopDAO.class)
@Caption("{%name}")
@Entity
@Cacheable(true)
@Table(name = "shop", schema = "public")
public class Shop implements java.io.Serializable
{
	
	private long id;
	@JsonIgnore
	private Address address;
	@JsonSerialize
	private String name;
	@JsonIgnore
	private Set<Purchase> purchases = new HashSet<>(0);
	@JsonIgnore
	private Set<Employee> employees = new HashSet<>(0);
	@JsonIgnore
	private Set<Inventoryitem> inventoryitems = new HashSet<>(0);
	
	public Shop()
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
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shop")
	public Set<Purchase> getPurchases()
	{
		return this.purchases;
	}
	
	public void setPurchases(Set<Purchase> purchases)
	{
		this.purchases = purchases;
	}
	
	@Caption("Employees")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shop")
	public Set<Employee> getEmployees()
	{
		return this.employees;
	}
	
	public void setEmployees(Set<Employee> employees)
	{
		this.employees = employees;
	}
	
	@Caption("Inventoryitems")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shop")
	public Set<Inventoryitem> getInventoryitems()
	{
		return this.inventoryitems;
	}
	
	public void setInventoryitems(Set<Inventoryitem> inventoryitems)
	{
		this.inventoryitems = inventoryitems;
	}
	
}


package com.microstream.bookstore.domain;

import com.microstream.bookstore.dal.AddressDAO;
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
 * Address
 */
@DAO(AddressDAO.class)
@Caption("{%address}")
@Entity
@Cacheable(true)
@Table(name = "address", schema = "public")
public class Address implements java.io.Serializable
{
	
	private long			id;
	private City			city;
	private String			address;
	private String			address2;
	private String			zipcode;
	private Set<Customer>	customers	= new HashSet<>(0);
	private Set<Employee>	employees	= new HashSet<>(0);
	private Set<Shop>		shops		= new HashSet<>(0);
	private Set<Author>		authors		= new HashSet<>(0);
	private Set<Publisher>	publishers	= new HashSet<>(0);
	
	public Address()
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
	
	@Caption("City")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "city_id", columnDefinition = "int8")
	public City getCity()
	{
		return this.city;
	}
	
	public void setCity(City city)
	{
		this.city = city;
	}
	
	@Caption("Address")
	@Column(name = "address", columnDefinition = "varchar")
	public String getAddress()
	{
		return this.address;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	@Caption("Address2")
	@Column(name = "address2", columnDefinition = "varchar")
	public String getAddress2()
	{
		return this.address2;
	}
	
	public void setAddress2(String address2)
	{
		this.address2 = address2;
	}
	
	@Caption("Zipcode")
	@Column(name = "zipcode", columnDefinition = "varchar")
	public String getZipcode()
	{
		return this.zipcode;
	}
	
	public void setZipcode(String zipcode)
	{
		this.zipcode = zipcode;
	}
	
	@Caption("Customers")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
	public Set<Customer> getCustomers()
	{
		return this.customers;
	}
	
	public void setCustomers(Set<Customer> customers)
	{
		this.customers = customers;
	}
	
	@Caption("Employees")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
	public Set<Employee> getEmployees()
	{
		return this.employees;
	}
	
	public void setEmployees(Set<Employee> employees)
	{
		this.employees = employees;
	}
	
	@Caption("Shops")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
	public Set<Shop> getShops()
	{
		return this.shops;
	}
	
	public void setShops(Set<Shop> shops)
	{
		this.shops = shops;
	}
	
	@Caption("Authors")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
	public Set<Author> getAuthors()
	{
		return this.authors;
	}
	
	public void setAuthors(Set<Author> authors)
	{
		this.authors = authors;
	}
	
	@Caption("Publishers")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
	public Set<Publisher> getPublishers()
	{
		return this.publishers;
	}
	
	public void setPublishers(Set<Publisher> publishers)
	{
		this.publishers = publishers;
	}
	
}

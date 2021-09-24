
package com.microstream.bookstore.domain;

import com.microstream.bookstore.dal.CityDAO;
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
 * City
 */
@DAO(CityDAO.class)
@Caption("{%name}")
@Entity
@Cacheable(true)
@Table(name = "city", schema = "public")
public class City implements java.io.Serializable
{
	
	private long			id;
	private State			state;
	private String			name;
	private Set<Address>	addresses	= new HashSet<>(0);
	
	public City()
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
	
	@Caption("State")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "state_id", columnDefinition = "int8")
	public State getState()
	{
		return this.state;
	}
	
	public void setState(State state)
	{
		this.state = state;
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
	
	@Caption("Addresses")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
	public Set<Address> getAddresses()
	{
		return this.addresses;
	}
	
	public void setAddresses(Set<Address> addresses)
	{
		this.addresses = addresses;
	}
	
}


package com.microstream.bookstore.domain;

import com.microstream.bookstore.dal.StateDAO;
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
 * State
 */
@DAO(StateDAO.class)
@Caption("{%name}")
@Entity
@Cacheable(true)
@Table(name = "state", schema = "public")
public class State implements java.io.Serializable
{
	
	private long		id;
	private Country		country;
	private String		name;
	private Set<City>	cities	= new HashSet<>(0);
	
	public State()
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
	
	@Caption("Country")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "country_id", columnDefinition = "int8")
	public Country getCountry()
	{
		return this.country;
	}
	
	public void setCountry(Country country)
	{
		this.country = country;
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
	
	@Caption("Cities")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "state")
	public Set<City> getCities()
	{
		return this.cities;
	}
	
	public void setCities(Set<City> cities)
	{
		this.cities = cities;
	}
	
}

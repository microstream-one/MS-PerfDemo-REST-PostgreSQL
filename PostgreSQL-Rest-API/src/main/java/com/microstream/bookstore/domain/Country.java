
package com.microstream.bookstore.domain;

import com.microstream.bookstore.dal.CountryDAO;
import com.rapidclipse.framework.server.data.DAO;
import com.rapidclipse.framework.server.resources.Caption;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Country
 */
@DAO(CountryDAO.class)
@Caption("{%name}")
@Entity
@Cacheable(true)
@Table(name = "country", schema = "public")
public class Country implements java.io.Serializable
{
	
	private long		id;
	private String		name;
	private String		code;
	private Set<State>	states	= new HashSet<>(0);
	
	public Country()
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
	
	@Caption("Code")
	@Column(name = "code", columnDefinition = "varchar")
	public String getCode()
	{
		return this.code;
	}
	
	public void setCode(String code)
	{
		this.code = code;
	}
	
	@Caption("States")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
	public Set<State> getStates()
	{
		return this.states;
	}
	
	public void setStates(Set<State> states)
	{
		this.states = states;
	}
	
}

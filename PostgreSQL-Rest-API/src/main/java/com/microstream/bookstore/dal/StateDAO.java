
package com.microstream.bookstore.dal;

import com.microstream.bookstore.domain.State;
import com.rapidclipse.framework.server.jpa.dal.JpaDataAccessObject;


/**
 * Home object for domain model class State.
 * 
 * @see State
 */
public class StateDAO extends JpaDataAccessObject.Default<State, Long>
{
	public final static StateDAO INSTANCE = new StateDAO();
	
	public StateDAO()
	{
		super(State.class);
	}
}

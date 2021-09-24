
package com.microstream.bookstore.dal;

import com.microstream.bookstore.domain.Employee;
import com.rapidclipse.framework.server.jpa.dal.JpaDataAccessObject;


/**
 * Home object for domain model class Employee.
 * 
 * @see Employee
 */
public class EmployeeDAO extends JpaDataAccessObject.Default<Employee, Long>
{
	public final static EmployeeDAO INSTANCE = new EmployeeDAO();
	
	public EmployeeDAO()
	{
		super(Employee.class);
	}
}

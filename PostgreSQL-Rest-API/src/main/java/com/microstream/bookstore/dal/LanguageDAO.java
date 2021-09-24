
package com.microstream.bookstore.dal;

import com.microstream.bookstore.domain.Language;
import com.rapidclipse.framework.server.jpa.dal.JpaDataAccessObject;


/**
 * Home object for domain model class Language.
 * 
 * @see Language
 */
public class LanguageDAO extends JpaDataAccessObject.Default<Language, Long>
{
	public final static LanguageDAO INSTANCE = new LanguageDAO();
	
	public LanguageDAO()
	{
		super(Language.class);
	}
}

package com.microstream.controler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.microstream.bookstore.dal.BookDAO;
import com.microstream.bookstore.domain.Book;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;


@Controller("/book")
public class BookControler
{

	@Get
	@Produces(MediaType.APPLICATION_JSON)
	public String index(String isbn)
	{

		try(BookDAO instance = new BookDAO())
		{
			Book book =
				instance.findAllBooks().stream().filter(b -> b.getIsbn13().equals(isbn)).findFirst().orElse(null);

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(book);
			return json;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "404";
		}
	}

}

package com.microstream.controler;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.microstream.bookstore.dal.BookDAO;
import com.microstream.bookstore.domain.Book;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;


@Controller("/books")
public class BooksControler
{
	@Get
	@Produces(MediaType.APPLICATION_JSON)
	public String index()
	{
		try(BookDAO bookDAO = new BookDAO())
		{
			List<Book> books = bookDAO.findAllBooks().stream().collect(Collectors.toList());
			
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(books);
			// System.out.println(LocalDateTime.now() + " [Rest API - Books called]");
			return json;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "404";
		}
		
	}
}

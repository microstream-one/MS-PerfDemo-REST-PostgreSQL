package com.microstream.controler;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.microstream.bookstore.dal.BookDAO;
import com.microstream.bookstore.dal.InventoryitemDAO;
import com.microstream.bookstore.domain.Book;
import com.microstream.bookstore.domain.Inventoryitem;
import com.microstream.bookstore.domain.Shop;
import com.microstream.bookstore.transport.BookInShop;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;


@Controller("/bookinshops")
public class ShopsControler
{
	@Get
	@Produces(MediaType.APPLICATION_JSON)
	public String index()
	{
		try
		{
			List<Book> books = BookDAO.getInstance().findAllBooks();
			
			List<BookInShop> result = new ArrayList<>();
			try(InventoryitemDAO inventory = new InventoryitemDAO())
			{
				books.forEach(book ->
				{

					List<Inventoryitem> items = inventory.bookinStore(book.getIsbn13());
					BookInShop transport = new BookInShop();
					transport.setBook(book);
					
					transport.setShops(new ArrayList<Shop>());

					items.forEach(inv ->
					{
						transport.getShops().add(inv.getShop());
					});
					
					result.add(transport);

				});
			}
			catch(Exception e)
			{

			}
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(result);
			// System.out.println(LocalDateTime.now() + " [Rest API - bookinshops called]");
			return json;
		}
		catch(JsonProcessingException e)
		{
			e.printStackTrace();
			return "404";
		}
		
	}
}

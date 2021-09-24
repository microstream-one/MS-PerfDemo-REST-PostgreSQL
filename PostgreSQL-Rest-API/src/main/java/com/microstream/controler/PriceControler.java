package com.microstream.controler;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.microstream.bookstore.dal.BookDAO;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;


@Controller("/avgprice")
public class PriceControler
{
	@Get
	@Produces(MediaType.APPLICATION_JSON)
	public String index()
	{

		try(BookDAO instance = new BookDAO())
		{
			List<Double> avgprice = instance.avgprice();
			Double price = 0.0;
			for(Double p : avgprice)
			{
				price = price + p;
			}

			price = price / avgprice.size();
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(price);
			// System.out.println(LocalDateTime.now() + " [Rest API - AVG Price called]");
			return json;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "404";
		}
	}
}

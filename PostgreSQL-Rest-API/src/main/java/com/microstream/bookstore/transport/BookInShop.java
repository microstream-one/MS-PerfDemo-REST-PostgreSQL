package com.microstream.bookstore.transport;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.microstream.bookstore.domain.Book;
import com.microstream.bookstore.domain.Shop;


public class BookInShop
{
	@JsonSerialize
	private Book book;
	@JsonSerialize
	private List<Shop> shops = new ArrayList<>();
	
	public Book getBook()
	{
		return this.book;
	}

	public void setBook(Book book)
	{
		this.book = book;
	}

	public List<Shop> getShops()
	{
		return this.shops;
	}

	public void setShops(List<Shop> shops)
	{
		this.shops = shops;
	}

}

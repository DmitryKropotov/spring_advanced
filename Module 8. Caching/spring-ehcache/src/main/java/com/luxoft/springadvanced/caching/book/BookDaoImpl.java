package com.luxoft.springadvanced.caching.book;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository("bookDao")
public class BookDaoImpl implements BookDao {

	@Cacheable(value="booksCache", key="#author")
	public Book findByAuthor(String author) {
		System.out.println("Executing findByAuthor");
		return new Book(1,"Effective Java","Joshua Bloch");
	}

	
}
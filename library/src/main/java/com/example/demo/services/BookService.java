package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Book;



public interface BookService {
	
	List<Book> getAllBook();

    Book addBook(Book book);

    void deleteBook(int id);

    Book editBook(Book book);

    Book findBook(int id);

	void editPostBook(Book Book);

}

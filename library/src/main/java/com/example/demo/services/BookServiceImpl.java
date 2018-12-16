package com.example.demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Book;
import com.example.demo.repositories.BookRepository;


@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	
	private BookRepository bookRepository;
	
	@Override
	public List<Book> getAllBook() {
		return this.bookRepository.findAll();
	}

	@Override
	public Book addBook(Book book) {
		return this.bookRepository.saveAndFlush(book);
		
	}

	@Override
	public void deleteBook(int id) {
		 if(this.bookRepository.findById(id).orElse(null) != null) {
	            this.bookRepository.deleteById(id);
	        }
		
	}

	@Override
	public Book editBook(Book book) {
		 return  this.bookRepository.saveAndFlush(book);
	      
	}

	
	@Override
	public void editPostBook(Book book) {
		 this.bookRepository.save(book);
		
	}

	@Override
	public Book findBook(int id) {
		return this.bookRepository.getOne(id);
	}

}

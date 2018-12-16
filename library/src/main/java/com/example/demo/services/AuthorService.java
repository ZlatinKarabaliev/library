package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Author;




public interface AuthorService {
 
	Author findById(Integer id);
	
	List<Author> findAllAuthor();
	
    Author addAuthor(Author author);

    void deleteAuthor(int id);

    Author editAuthor(Author author);

    void editPostAuthor(Author author);
	
	
}

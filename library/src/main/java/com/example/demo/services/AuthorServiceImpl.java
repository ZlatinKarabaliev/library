package com.example.demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Author;
import com.example.demo.repositories.AuthorRepository;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository  authorRepository;
	
	@Override
	public Author findById(Integer id) {
		return this.authorRepository.findById(id).orElse(null);
	}

	@Override
	public List<Author> findAllAuthor() {
		return this.authorRepository.findAll();
	}

	@Override
	public Author addAuthor(Author author) {
		return this.authorRepository.saveAndFlush(author);
	}

	@Override
	public void deleteAuthor(int id) {
		 if(this.authorRepository.findById(id).orElse(null) != null) {
	            this.authorRepository.deleteById(id);
	        }
		
	}

	@Override
	public Author editAuthor(Author author) {
		return this.authorRepository.saveAndFlush(author);
	}

	@Override
	public void editPostAuthor(Author author) {
		this.authorRepository.save(author);
		
	}

}

package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Author;
import com.example.demo.models.Book;
import com.example.demo.services.AuthorService;

@Controller
@RequestMapping("/authors")
public class AuthorController {

	private AuthorService authorService;
	
	@Autowired
	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;		
	}
	
	@GetMapping("/show")
	public String showAllAuthors(Model model) {
		List<Author> authors = this.authorService.findAllAuthor();		
		model.addAttribute("authors", authors);
		
		return "authors";		
	}	
	
	@GetMapping("/add-author")
    public String addAuthor(Model model) {
		model.addAttribute("author", new Author()); 
        return "add-author";
    }

    @PostMapping("/add-author")
    public String addAuthorFromView(@ModelAttribute("author") Author author, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println(error);
            }
            return "redirect:/add-author";
        }
        authorService.addAuthor(author);

        return "redirect:/authors/show";
    }

    @GetMapping("/edit-author/{id}")
    public String editAuthor(@PathVariable int id, Model model , @ModelAttribute("author") Author author) {
        model.addAttribute("author", authorService.findById(id));
        return "edit-author";
    }

    @PutMapping("/edit-author/{id}")
    public String editAuthor(@PathVariable int id, @ModelAttribute("author") Author author, BindingResult bindingResult,Model model) {
	    if (bindingResult.hasErrors()) {
	        for (ObjectError error : bindingResult.getAllErrors()) {
	            System.out.println(error);
	        }
	        return "author";
	    }   
	    authorService.editPostAuthor(author);
	    
	    return "redirect:/authors/show";
    }

    @DeleteMapping("/delete-author/{id}")
    public String deleteAuthor(@PathVariable int id) {
        authorService.deleteAuthor(id);

        return "redirect:/authors/show";
    }
    
    @GetMapping("/add-book/{id}")
    public String addBookByAuthor(@PathVariable int id, Model model) {
		Author author = authorService.findById(id);
    	Book book = new Book();
		book.setAuthor(author);
		model.addAttribute("book", book);
		List<Author> authors = this.authorService.findAllAuthor(); 
		model.addAttribute("authors",authors);
        return "add-book";
    }
}

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
import com.example.demo.services.BookService;

@Controller
@RequestMapping("/books")
public class BooksController {

	private BookService bookService;
	private AuthorService authorService;
	
	
	@Autowired
	public BooksController(BookService bookService, AuthorService authorService) {
		this.bookService = bookService;
		this.authorService = authorService;		
	}
	
	@GetMapping("/show")
	public String showAllBooks(Model model) {
		List<Book> books = this.bookService.getAllBook();
		model.addAttribute("books", books);		
		
		return "books";		
	}
	
	
	@GetMapping("/add-book")
    public String addBook( Model model) {
		model.addAttribute("book", new Book());
		List<Author> authors = this.authorService.findAllAuthor();
		model.addAttribute("authors", authors); 
        return "add-book";
    }

    @PostMapping("/add-book")
    public String addBookFromView(@ModelAttribute("book") Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println(error);
            }
            return "redirect:/add-book";
        }      
        bookService.addBook(book);

        return "redirect:/books/show";
    }
   
    @GetMapping("/edit-book/{id}")
    public String ediBook(@PathVariable int id, Model model ) {
    	Book book = bookService.findBook(id);
        model.addAttribute("book", bookService.editBook(book));
    	List<Author> authors = this.authorService.findAllAuthor();
   	 	model.addAttribute("authors", authors);
      
       
        return "edit-book";
    }
  

    @PutMapping("/edit-book/{id}")
    public String editbook(@PathVariable int id, @ModelAttribute("book") Book book, BindingResult bindingResult,Model model)  {
    	if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println(error);
            }
            return "redirect:/edit-book";
        }    
     
    	bookService.editPostBook(book);
    	
        return "redirect:/books/show";        
    }

    @DeleteMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return "redirect:/books/show";
    }   	
}

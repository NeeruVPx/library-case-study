package com.fis.library.bookservice;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {

	@Autowired
	private LibraryRepo repo;

	@GetMapping("/book-service/books")
	public List<Book> retrieveCurrency() {
		System.out.println("here");
		List<Book> books = new ArrayList<Book>();
		books = repo.findAll();
		// Book b1= new Book("102" , "Ulysses","James Joyce",2, 3);
		System.out.println("here" + books);
		// books.add(b1);
		return books;
	}
	
	@PostMapping("/book-service/books")
	public ResponseEntity<Book> saveBook(@RequestBody Book book) {
		System.out.println("Saving book :"+book);
		Book save = repo.save(book);
		
		
		
		ResponseEntity<Book> entity = new ResponseEntity<Book>(save, HttpStatus.OK);
		 return entity;
	}

	@GetMapping(value = {"/book-service/book/{book_id}","/book-service/book"})
	@HystrixCommand(fallbackMethod="fallbackRetrieveBook")  
	public Book retrieveCurrency1(@PathVariable(required = false) String book_id) {
		System.out.println("book_id" +book_id);
		if(book_id == null) {
			return null;
		}
		Book b1 =repo.findByBookId(book_id);
		System.out.println("book returned is "+b1);
		return b1;
	}
	
	@PostMapping("/book-service/book")
	public Book createBook(@RequestBody Book book) {
		System.out.println("Book to be saved "+book);
        return repo.save(book);
	}
	
	public Book fallbackRetrieveBook()  
	{  
	//returning the default configuration     
	return new Book("-1",null, null, 0, 0);   
	}  
}

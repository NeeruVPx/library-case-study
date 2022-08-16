package com.fis.library.subscriptionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "book-service" ,url ="localhost:8050")
public interface LibraryProxy {

	@GetMapping("/book-service/book/{bookID}")
	public  Book retriveExchange(@PathVariable String bookID) ;
	
	@PostMapping("/book-service/books")
	public Book postExchange(@RequestBody Book book) ;

	
}


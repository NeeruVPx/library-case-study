package com.fis.library.subscriptionservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SubscriptionController {

	
	  @Autowired 
	  private LibraryRepo repo;
	 
	  @Autowired
	  private LibraryProxy proxy;

	@GetMapping(value = {"/subscription-service/subscription/{subscriptionName}","/subscription-service/subscription"})
	public List<Subscription> retrieveSubscriptions(@PathVariable(required = false) String subscriptionName) {
		System.out.println("here" +subscriptionName);
		List<Subscription> subscriptions= new ArrayList<Subscription>();
		if(subscriptionName == null)
			subscriptions = repo.findAll();
		else
			subscriptions = repo.findBySubscriberName(subscriptionName);
		
		return subscriptions;
	}
	
	@PostMapping("/subscription-service/subscription")
	  public Subscription newSubscription(@RequestBody Subscription subscription) {
		
		
		//-	Use RestTemplate to call book-service from subscription to check if book is available for subscription or not.
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("book_id",subscription.getBookId());
		ResponseEntity<Book> res = new RestTemplate().getForEntity(
				"http://localhost:8050/book-service/book/{book_id}",
				Book.class,map);
		
		
		if(res == null || res.getStatusCode() != HttpStatus.OK || res.getBody() == null || res.getBody().getBookId() == null) {
			System.out.println("no book id present ");
			return null;
		}
		else {
			Book book = res.getBody();
			if(book.getCopiesAvailable() > 0) {
				System.out.println("Book present , subscription is allowed"+ book);
				book.setCopiesAvailable(book.getCopiesAvailable()-1);
				
				new RestTemplate().postForObject("http://localhost:8050/book-service/books/", book, Book.class);
				
			}
			return  repo.save(subscription);
		}
	}
	
	
	@PostMapping("/subscription-service/subscription-feign")
	public Subscription newSubscriptionFeign(@RequestBody Subscription subscription) { 
		System.out.println("herer");
		System.out.println("subscription.getBookId() :"+subscription.getBookId());
		Book book =  proxy.retriveExchange(subscription.getBookId());
		
		System.out.println("book" +book);
		if(book == null || book.getBookId() == null||book.getBookId() !="-1") {
			System.out.println("no book id present ");
			return null;
		}
		else {
			if(book.getCopiesAvailable() > 0) {
				System.out.println("Book present , subscription is allowed");
				book.setCopiesAvailable(book.getCopiesAvailable()-1);
				proxy.postExchange(book);
				
			}
			System.out.println("subscription :"+subscription);
		
			return  repo.save(subscription);
		}
	
	}
}

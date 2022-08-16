package com.fis.library.subscriptionservice;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Subscription {

	private String bookId;
	@Id
	private int id;
	
	private String subscriberName;

	private Date dateSubscribed;

	private Date dateReturned;

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getSubscriberName() {
		return subscriberName;
	}

	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}

	public Date getDateSubscribed() {
		return dateSubscribed;
	}

	public void setDateSubscribed(Date dateSubscribed) {
		this.dateSubscribed = dateSubscribed;
	}

	public Date getDateReturned() {
		return dateReturned;
	}

	public void setDateReturned(Date dateReturned) {
		this.dateReturned = dateReturned;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Subscription(int id, String bookId, String subscriberName, Date dateSubscribed, Date dateReturned) {
		super();
		this.bookId = bookId;
		this.subscriberName = subscriberName;
		this.dateSubscribed = dateSubscribed;
		this.dateReturned = dateReturned;
		this.id = id;
	}

	public Subscription() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Subscription [id ="+ id +", bookId=" + bookId + ", subscriberName=" + subscriberName + ", dateSubscribed="
				+ dateSubscribed + ", dateReturned=" + dateReturned + "]";
	}

	
}

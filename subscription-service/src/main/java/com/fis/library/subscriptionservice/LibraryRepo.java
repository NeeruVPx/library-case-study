package com.fis.library.subscriptionservice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepo extends JpaRepository<Subscription, Long> {
	List<Subscription> findBySubscriberName(String subscriberName);
}

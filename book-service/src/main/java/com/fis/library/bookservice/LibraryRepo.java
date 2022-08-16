package com.fis.library.bookservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepo extends JpaRepository<Book, Long> {
	Book findByBookId(String bookId);
}

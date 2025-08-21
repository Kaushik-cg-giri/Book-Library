package com.book_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book_management.entity.Book;

@Repository
public interface BookRepository  extends JpaRepository<Book, Long>{

	Book findByAuthor(String author);
}

package com.book_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.book_management.entity.Book;
import com.book_management.exception.BookNotFoundException;
import com.book_management.repository.BookRepository;
import org.springframework.data.domain.Pageable;

@Service
public class BookService {
	
	@Autowired
	private BookRepository repository;

	public Book addBookService(Book book) {
		 repository.save(book);
		 return book;
	}

	public List<Book> getAllBookService() {

		List<Book> bookList = repository.findAll();
		
		if(bookList.isEmpty()) {
			
			throw new BookNotFoundException("No Books are present in Library");
		}
		return bookList;
	}

	public Book fetchBookById(long id) {

		Book book = repository.findById(id).orElseThrow(()->  new BookNotFoundException("No Books are present in Library"));
		return book;
	}

	public Book updateBookById(long id,Book book) {

		Book bookId = repository.findById(id).orElseThrow(()->new BookNotFoundException("No Book are present in this id"));
		
		bookId.setAuthor(book.getAuthor());
		bookId.setTittle(book.getTittle());
		bookId.setPublishedDate(book.getPublishedDate());
		bookId.setGenre(book.getGenre());
		
		repository.save(bookId);
		return bookId;
	}

	public String deleteBookById(long id) {
		
		Book book = repository.findById(id).orElseThrow(()->  new BookNotFoundException("No Books are present in this id"));
		
		if(book!=null) {
			repository.delete(book);
			return "Book deleted sucessfully";
		}
		else {
			return"No Book is present in this ID";
		}
		
	}

	public Book fetchBookByAuthor(String author) {

		Book book = repository.findByAuthor(author);
		if(book==null) {
			throw new BookNotFoundException("No Books are presten in this author name");
		}
		return book;
	}

	public Page<Book> getAllBooksSorted(int pageNumber, int pageSize, String sortBy, String sortDir) {
		
		Sort sort = sortDir.equalsIgnoreCase("asc")?
													Sort.by(sortBy).ascending():
													Sort.by(sortBy).descending();
		
		Pageable pagable = PageRequest.of(pageNumber, pageSize, sort);
		
		Page<Book> bookPage = repository.findAll(pagable);
		
		if(bookPage.isEmpty())
			throw new BookNotFoundException("No Books are present");
		
		return bookPage;
	}



}

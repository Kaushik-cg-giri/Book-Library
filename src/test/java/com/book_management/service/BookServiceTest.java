package com.book_management.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.book_management.entity.Book;
import com.book_management.repository.BookRepository;

@ExtendWith(MockitoExtension.class)  //  tells JUnit to initialize mocks
public class BookServiceTest {
	
	@Mock
	private BookRepository bookRepository;  //Creates a mock object (does not inject automatically).
	
	@InjectMocks
	private BookService bookService; //Injects the mock objects into the class under test
	
	private Book book = new Book();
	
	@BeforeEach   //This run before every test method
	
	void setUp() {
		
		
		book.setId(101);
		book.setAuthor("MT");
		book.setGenre("periodic");
		Date date = new Date(1991,05,01);
		book.setPublishedDate(date);
		book.setTittle("ormakal");
	
		System.out.println("Before Each : Setting up Data");
	}
	
	/************** Valid Tst Cases**********************/
	@Test
	public void testAddBookService_valid() {
		
		// define mock behavior
		when(bookRepository.save(any(Book.class))).thenReturn(book);  //return this fake object
		
		// call the service method
		Book saved = bookService.addBookService(book);  // mock and save the product
		
		// verify and assert
		assertNotNull(saved); // check not null
		assertEquals("MT", saved.getAuthor()); // check actual name and stored name
		
		verify(bookRepository,times(1)).save(book); //verify service call save() ,,did not skip repository
	}
	
	@Test
	public void testGetAllBookService_valid() {
		
		List<Book> listBook = Arrays.asList(book, new Book());
		
		//define Mock behavior
		when(bookRepository.findAll()).thenReturn(listBook);
		
		//call the service method
		List<Book> resultBook = bookService.getAllBookService();
		
		//assert and conform
		assertEquals(2, listBook.size());
		
		// Verify that repository method was called once
		verify(bookRepository,times(1)).findAll();
	}
	
	@Test
	public void testGetBookById_valid() {
		
		//define mock behavior using when
		when(bookRepository.findById((long) 101)).thenReturn(Optional.of(book));
		
		//call the service method
		Book resultBook = bookService.fetchBookById(101);
		
		//assert and conform
		assertNotNull(resultBook);
		assertEquals("ormakal",resultBook.getTittle());
		
		//verify thet repository method calls once
		verify(bookRepository, times(1)).findById((long) 101);
		
	}
	
	@Test
	public void testUpdateBookById_valid() {
		Date date = new Date(1991,05,01);
		
		//create updated DTO
		Book updatedBook = new Book(101,"ormakal","MT vasudevan",date,"periodic");
		
		//define mock behavior using when
		when(bookRepository.findById((long) 101)).thenReturn(Optional.of(book));
		when(bookRepository.save(any(Book.class))).thenReturn(updatedBook);
		
		//call the service method
		Book updated = bookService.updateBookById(101, updatedBook);
		
		//assert and conform
		assertEquals("MT vasudevan", updated.getAuthor());

		//verify that repository and method calls once
		verify(bookRepository, times(1)).save(updated);
	}
	
	@Test
	public void testDeleteBookById_valid() {
		
		//define mock behavior using when
		when(bookRepository.findById((long) 101)).thenReturn(Optional.of(book));
		
		//call the service method
		bookService.deleteBookById(101);
		
		
		//verify that repository that calls once
		verify(bookRepository,times(1)).delete(book);
	}
	
	@Test
	public void testFetchBookByAuthor_valid() {
		
		//define mock behavior using when
		when(bookRepository.findByAuthor("MT")).thenReturn(Optional.of(book));
		
		//call the service method
		Book book = bookService.fetchBookByAuthor("MT");
		
		//assert and conform
		assertEquals("MT", book.getAuthor());
		
		//verify that repository calls once
		verify(bookRepository, times(1)).findByAuthor("MT");
	}
	
	@Test
	public void testGetAllBooksSorted_valid() {
		Date date = new Date(1991,05,01);
		List<Book> listBook = Arrays.asList(book,new Book(101,"ormakal","MT vasudevan",date,"periodic"));
		
		//Define before passing  to mock behavior
		Page<Book> page = new PageImpl<>(listBook);
		
		//define mock behavior using when
		when(bookRepository.findAll(any(Pageable.class))).thenReturn(page);
		
		//call the service method
		Page<Book> afterPagination =  bookService.getAllBooksSorted(0, 1,"tittle","asc");
		
		//assert and conform
		assertNotNull(afterPagination);
		assertEquals(2, afterPagination.getNumberOfElements());
		
		//verify that repository calls once 
		verify(bookRepository,times(1)).findAll(any(Pageable.class));
	}
}

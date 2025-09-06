package com.book_management.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book_management.entity.Book;
import com.book_management.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("books")
public class BookController {

	private static final Logger logger = LoggerFactory.getLogger(BookController.class);; 
	@Autowired
	private BookService service;
	
	@PostMapping("addBook")
	public ResponseEntity<Book> addBooks( @RequestBody Book book){
		System.out.println("insert");
		return new ResponseEntity<Book>(service.addBookService(book), HttpStatus.CREATED);
		
	}
	
	@GetMapping("getAllBooks")
	public ResponseEntity<List<Book>> getAllBooks(){
		return ResponseEntity.ok(service.getAllBookService());
	}
	
	@GetMapping("getBookById/{id}")
	public ResponseEntity<Book>getBook(@PathVariable long id){
		return new ResponseEntity<Book>(service.fetchBookById(id), HttpStatus.OK);
	}

	@PutMapping("updateBookById/{id}")
	public ResponseEntity<Book>updateBook(@PathVariable long id,@RequestBody Book book){
		return new ResponseEntity<Book>(service.updateBookById(id,book), HttpStatus.OK);
	}
	
	@DeleteMapping("DeleteBookByID/{id}")
	public ResponseEntity<String> deleteBookById(@PathVariable long id) {
		return new ResponseEntity<String>(service.deleteBookById(id),HttpStatus.OK);
	}
	
	@GetMapping("getBookByAuthor/searcch")
	public ResponseEntity<Book> getBookByAuther(@RequestParam("author") String author){
		return new ResponseEntity<Book>(service.fetchBookByAuthor(author),HttpStatus.OK);
	}
	
	@GetMapping("getAllBooksArranged")
	public Page<Book> getAllBooksArranged(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "1") int pageSize,
													@RequestParam(defaultValue = "tittle") String sortBy,@RequestParam(defaultValue = "asc") String sortDir){
		return service.getAllBooksSorted(pageNumber,pageSize,sortBy,sortDir);
	}

}

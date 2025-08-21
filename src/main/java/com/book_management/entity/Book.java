package com.book_management.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Book Tittle is mandatory")
	private String tittle;
	
	@NotBlank(message = "Author name is mandatory")
	private String author;
	
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	private Date publishedDate;
	
	@NotBlank(message ="Book genre is mandatory")
	private String genre;

	public Book(long id, String tittle, String author, Date publishedDate, String genre) {
		super();
		this.id = id;
		this.tittle = tittle;
		this.author = author;
		this.publishedDate = publishedDate;
		this.genre = genre;
	}

	public Book() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
	
}

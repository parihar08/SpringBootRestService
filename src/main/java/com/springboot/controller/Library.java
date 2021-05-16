package com.springboot.controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//This annotation helps JPA to understand that this is the class
//which needs to map to database
//Sometimes there is a conflict for JPA if the class name and table name starts
//with same characters. Hence, we are using Library as class name and Storage
//as table name

@Entity
@Table(name="Storage")	//mapping table name we want to use from database
public class Library {
	
	//mapping database columns to class variables 
	
	@Column(name="book_name")
	private String book_name;
	
	@Id		//mentioning that id column is the primary key in the Storage table
	@Column(name="id")
	private String id;
	
	@Column(name="isbn")
	private String isbn;
	
	@Column(name="aisle")
	private int aisle;
	
	@Column(name="author")
	private String author;
	
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getAisle() {
		return aisle;
	}
	public void setAisle(int aisle) {
		this.aisle = aisle;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	

}

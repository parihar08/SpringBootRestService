package com.springboot.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.controller.Library;

public class LibraryRepositoryImpl implements LibraryRepositoryCustom{
	
	@Autowired
	LibraryRepository repository;

	@Override
	public List<Library> findAllByAuthor(String authorName) {
		
		List<Library> booksWithAuthor = new ArrayList<Library>();
		
		List<Library> books =repository.findAll();
		
		for(Library book: books) {
			//if the book is same as we are searching through findAllByAuthor(String authorName), 
			//then add that book into a new list - booksWithAuthor
			if(book.getAuthor().equalsIgnoreCase(authorName)) {
				booksWithAuthor.add(book);
				
			}
			
		}
		return booksWithAuthor;
		
	}
	
	@Override
	public List<Library> findAllBooks() {
	
		List<Library> books =repository.findAll();
		
		return books;
	}
	

}

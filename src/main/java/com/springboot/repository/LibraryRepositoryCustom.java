package com.springboot.repository;

import java.util.List;

import com.springboot.controller.Library;

public interface LibraryRepositoryCustom {
	
	List<Library> findAllByAuthor(String authorName);

	List<Library> findAllBooks();

}

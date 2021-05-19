package com.springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.controller.Library;
import com.springboot.repository.LibraryRepository;

@Service
public class LibraryService {
	
	@Autowired
	LibraryRepository repository;
	
	public String buildId(String isbn, int aisle) {
		
		if(isbn.startsWith("Z")) {	
			return "OLD"+isbn + aisle;
		}
		return isbn + aisle;
	}
	
	public boolean checkBookAlreadyExists(String id) {
		
		Optional<Library> lib = repository.findById(id);
		if(lib.isPresent()) {
			return true;
		}
		else {
			return false;
		}
		
	}

}

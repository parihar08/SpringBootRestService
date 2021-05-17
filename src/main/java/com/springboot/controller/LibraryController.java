package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.repository.LibraryRepository;
import com.springboot.service.LibraryService;

@RestController
public class LibraryController {
	
	@Autowired
	LibraryRepository repository;
	
	@Autowired
	LibraryService libraryService;
	
	@PostMapping("/addBook")
	public ResponseEntity addBookImplementation(@RequestBody Library library) {
		
		//String id = library.getIsbn()+library.getAisle();
		String id = libraryService.buildId(library.getIsbn(),library.getAisle());
		
		//Add Book details into database
		//JPA Repository - save()
		
		AddResponse ar = new AddResponse();
		
		if(!libraryService.checkBookAlreadyExists(id)) {
			
		library.setId(id);
		
		repository.save(library);
		
		HttpHeaders headers = new HttpHeaders(); //to add headers to the response
		
		headers.add("unique", id);
		
		//For Response
		ar.setMessage("Success Book is Added");
		ar.setId(id);
		
		//We need to convert entity/bean into json format and add status code in 
		//response body to display to the user. ResponseEntity is used for it.
		return new ResponseEntity<AddResponse>(ar, headers, HttpStatus.CREATED);
		}
		
		else {
			//Write the code to tell book already exists
			ar.setMessage("Book already exists");
			ar.setId(id);
			return new ResponseEntity<AddResponse>(ar,HttpStatus.ACCEPTED);
		}
		
	}

}

package com.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.springboot.repository.LibraryRepository;
import com.springboot.service.LibraryService;

@RestController
public class LibraryController {
	
	@Autowired
	LibraryRepository repository;
	
	@Autowired
	LibraryService libraryService;
	
	private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);;
	
	@PostMapping("/addBook")
	public ResponseEntity addBookImplementation(@RequestBody Library library) {
		
		//String id = library.getIsbn()+library.getAisle();
		String id = libraryService.buildId(library.getIsbn(),library.getAisle());
		
		//Add Book details into database
		//JPA Repository - save()
		
		AddResponse ar = new AddResponse();
		
		if(!libraryService.checkBookAlreadyExists(id)) {
			logger.info("Book doesn't exist. Creating one!");
			
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
			logger.info("Book already exists. Skipping creation!");
			ar.setMessage("Book already exists");
			ar.setId(id);
			return new ResponseEntity<AddResponse>(ar,HttpStatus.ACCEPTED);
		}
		
	}
	
	@GetMapping("/getBooks/{id}")
	public Library getBookById(@PathVariable(value="id") String id) {
		
		try {
			Library lib = repository.findById(id).get();
			return lib;
		}
		catch(Exception e) {
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@GetMapping("/getBooks/author")
	public List<Library> getBookByAuthor(@RequestParam(value="authorname") String authorname) {
		//We need to create a custom JPA interface and extend it, in which we will 
		//define the find by author logic as by default we only have find by id 
		//logic in spring boot JPA repository
		
		return repository.findAllByAuthor(authorname);
		
	}
	
	@PutMapping("/updateBook/{id}")
	public ResponseEntity<Library> updateBook(@PathVariable(value="id") String id, @RequestBody Library library)
	{
		Library existingBook = repository.findById(id).get();
		
		existingBook.setAisle(library.getAisle());
		existingBook.setAuthor(library.getAuthor());
		existingBook.setBook_name(library.getBook_name());
		
		repository.save(existingBook); //Save the new values to database
		
		return new ResponseEntity<Library>(existingBook, HttpStatus.OK);	
		
	}
	
	@DeleteMapping("/deleteBook")
	public ResponseEntity<String> deleteBook(@RequestBody Library library) {
		
		Library bookToDelete = repository.findById(library.getId()).get();
		
		repository.delete(bookToDelete);
		logger.info("Book is deleted!!");
		return new ResponseEntity<>("Book is deleted",HttpStatus.OK);
		
	}
	
	@GetMapping("/getBooks")
	public List<Library> getBooks() {
		//We need to create a custom JPA interface and extend it, in which we will 
		//define the find by author logic as by default we only have find by id 
		//logic in spring boot JPA repository
		
		return repository.findAllBooks();
		
	}

}

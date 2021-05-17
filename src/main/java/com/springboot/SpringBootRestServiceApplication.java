package com.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springboot.controller.Library;
import com.springboot.repository.LibraryRepository;

@SpringBootApplication
public class SpringBootRestServiceApplication {
//public class SpringBootRestServiceApplication implements CommandLineRunner{

	@Autowired
	LibraryRepository repository;	//instead of creating object
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestServiceApplication.class, args);
	}

	/*@Override
	public void run(String[] args) {
		
		Library lib = repository.findById("fdsefr343").get();
		//Now this lib object holds the data present in our Storage table
		
		System.out.println("Author: "+lib.getAuthor());
		System.out.println("Book Name: "+lib.getBook_name());
		System.out.println("Aisle: "+lib.getAisle());
		System.out.println("Isbn: "+lib.getIsbn());
		
		System.out.println("****************************");
	
		//Insert new record into the Storage table
		
		Library entity = new Library();
		
		entity.setAisle(123);
		entity.setAuthor("Sandeep Parihar");
		entity.setBook_name("Tales of Cricket");
		entity.setIsbn("test");
		entity.setId("test123");
		
		repository.save(entity);	//Commented as we have already inserted 
									//the records initially
		
		//To fetch all the records from the table
		List<Library> allRecords = repository.findAll();
		
		//Suppose we want to get all booknames from the table
		for(Library bookNames: allRecords) {
			System.out.println(bookNames.getBook_name());
		}
		
		//To delete the records from table
		
		repository.delete(entity);
	}
	
	*/

}

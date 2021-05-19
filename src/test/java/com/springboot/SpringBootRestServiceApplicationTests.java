package com.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.springboot.controller.AddResponse;
import com.springboot.controller.Library;
import com.springboot.controller.LibraryController;
import com.springboot.repository.LibraryRepository;
import com.springboot.service.LibraryService;

@SpringBootTest
class SpringBootRestServiceApplicationTests {
	
	@Autowired
	LibraryController con;
	
	@MockBean
	LibraryRepository repository;
	
	@MockBean
	LibraryService libraryService;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void checkBuildIDLogic() {
		
		LibraryService lib = new LibraryService();
		
		String id = lib.buildId("ZMAN", 24);
		assertEquals(id, "OLDZMAN24");
		
		String id1 = lib.buildId("MAN", 24);	
		assertEquals(id1, "MAN24");
		
	}
	
	@Test
	public void addBookTest() {
		
		//mock code to handle external dependencies
		Library lib = buildLibrary();
		
		when(libraryService.buildId(lib.getIsbn(),lib.getAisle()))
		.thenReturn(lib.getId());
		
		when(!libraryService.checkBookAlreadyExists(lib.getId()))
		.thenReturn(false);
		
		ResponseEntity response =con.addBookImplementation(buildLibrary());
		System.out.println(response.getStatusCode());
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	
		AddResponse ad = (AddResponse) response.getBody();
		assertEquals(lib.getId(),ad.getId());
		assertEquals("Success Book is Added", ad.getMessage());
		
	}
	
	@Test
	public Library buildLibrary() {
		Library lib = new Library();
		lib.setAisle(0607);
		lib.setAuthor("Dolly Minhas");
		lib.setBook_name("RN Competencies");
		lib.setIsbn("RN");
		lib.setId("RN0607");
		
		return lib;
	}

}

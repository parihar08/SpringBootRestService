package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.controller.Library;

public interface LibraryRepository extends JpaRepository<Library, String>{
	//<Library,String>	Library is the class where we have defined our database
	//columns in the POJO format and String is the return type of primary key
	//(Id column) of Storage table

}

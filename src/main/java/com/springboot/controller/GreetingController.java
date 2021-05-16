package com.springboot.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	@Autowired
	Greeting greeting;
	
	AtomicLong counter = new AtomicLong();
	
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value="name")String name) {
		
		//define logic to get the response back by reading the input
		
		//Greeting greeting = new Greeting();
		
		//Instead of creating object of the class, use the feature called
		//Dependency injection, we need not create new object, we can add
		//annotation called Autowired
		
		greeting.setId(counter.incrementAndGet());
		greeting.setContent("Hi! I'm learning Spring Boot from "+ name);
		
		return greeting;
		
		
		
	}
	

}
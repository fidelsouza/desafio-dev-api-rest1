package com.research.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.research.demo.Entities.Person;
import com.research.demo.Services.PersonServices;
import com.research.demo.ServicesImpl.PersonServicesImpl;

@RequestMapping(value = "/person")
@RestController()
public class PersonController {

	@Autowired
	PersonServices service;
	
	@PostMapping("/insert")
	ResponseEntity<Person> create(@RequestBody Person person){
		
		Person ret = service.createPerson(person);
		
		return ResponseEntity.status(HttpStatus.OK).body(ret);
	}
}

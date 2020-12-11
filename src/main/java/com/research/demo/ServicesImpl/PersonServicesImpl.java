package com.research.demo.ServicesImpl;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.research.demo.Entities.Person;
import com.research.demo.Repository.PersonRepository;
import com.research.demo.Services.PersonServices;

@Service
public class PersonServicesImpl implements PersonServices {
	@Autowired
	PersonRepository repository;
	
	@Override
	public Person createPerson(Person person) {
		if(Strings.isNotBlank(person.getName()) && Strings.isNotBlank(person.getCpf()) && person.getBirthDate()!=null) {
			return this.repository.save(person);
		}
		else {
			throw new IllegalArgumentException("Todos os dados obrigatórios devem ser informados!");
		}
		
	}
}

package com.research.demo.Repository;

import org.springframework.data.repository.CrudRepository;

import com.research.demo.Entities.Person;


public interface PersonRepository extends CrudRepository<Person,Long>{

}

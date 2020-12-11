package com.research.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.research.demo.Entities.Person;
import com.research.demo.Repository.PersonRepository;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DesafioDevApiRestApplicationTests {
	
	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private PersonRepository personRepo;
	
	@LocalServerPort
	private int port;

	@Test
	void contextLoads() {
	}
	
	@Test
	void savePerson() {

		Person add = new Person("Carlos", "145169149", new Date());

		BDDMockito.when(personRepo.save(add)).thenReturn(add);

		String url = "http://localhost:" + port + "/person/insert";

		restTemplate.getRestTemplate().getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		HttpHeaders headers = new HttpHeaders();

		HttpEntity<Person> requestEntity = new HttpEntity<Person>(add, headers);
		HttpEntity<Person> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Person.class);

		assertThat(response).isNotNull();

	}

}

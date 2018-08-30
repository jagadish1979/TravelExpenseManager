package com.expenses.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.expenses.domain.MoneySpendByPersonsChartData;
import com.expenses.domain.Person;
import com.expenses.service.PersonService;

/**
 * @author Jagadish Anala
 *
 */
@Controller
@RequestMapping("/")
public class PersonController {
	private static final Logger logger = LogManager.getLogger(PersonController.class);

	@Autowired
	private PersonService personService;

	@GetMapping(value = "/findPersonsCount", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> findPersonsCount() throws Exception {
		logger.info("Trying to find PersonsCount ******!");
		Integer personsCount = personService.findPersonsCount();
		return new ResponseEntity<Integer>(personsCount, HttpStatus.OK);
	}

	@GetMapping(value = "/findPersonsNames", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> findPersonsNames() throws Exception {
		logger.info("Trying to find PersonsCount ******!");
		List<String> personsNames = personService.findPersonsNames();
		return new ResponseEntity<List<String>>(personsNames, HttpStatus.OK);
	}
	
	@Transactional
	@GetMapping(value = "/findAllPersons", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Person>> findAllPersons() {
		List<Person> personsList = new ArrayList<Person>();
		logger.info("Trying to find Persons List ******!");
		try {
			personsList = personService.findAllPersons();
			personsList = personsList.stream().peek(person -> Hibernate.initialize(person.getExpenses()))
					.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Person>>(personsList, HttpStatus.OK);
	}

}
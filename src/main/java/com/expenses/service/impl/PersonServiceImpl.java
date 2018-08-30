package com.expenses.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expenses.domain.Person;
import com.expenses.repository.PersonRepository;
import com.expenses.service.PersonService;

/**
 * @author Jagadish Anala
 *
 */
@Service("personService")
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public int findPersonsCount() throws Exception {
		return personRepository.findAll().size();
	}

	@Override
	@Transactional
	public List<Person> findAllPersons() throws Exception {
		return personRepository.findAll().stream().peek(person -> Hibernate.initialize(person.getExpenses())).collect(Collectors.toList());
	}

	@Override
	public List<String> findPersonsNames() throws Exception {
		 return personRepository.findAll().stream().map(Person::getFullName).collect(Collectors.toList());
	}
}

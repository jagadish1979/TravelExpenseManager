package com.expenses.service;

import java.util.List;

import com.expenses.domain.Person;

/**
 * @author Jagadish Anala
 *
 */
public interface PersonService {

	public int findPersonsCount() throws Exception;

	List<Person> findAllPersons() throws Exception;

	List<String> findPersonsNames() throws Exception;
}

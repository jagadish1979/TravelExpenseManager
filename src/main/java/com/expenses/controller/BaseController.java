package com.expenses.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.expenses.domain.Expenses;
import com.expenses.domain.Owed;
import com.expenses.domain.Person;
import com.expenses.service.ExpensesService;
import com.expenses.service.PersonService;

@Controller
@RequestMapping("/")
public class BaseController {
	private static final Logger logger = LogManager.getLogger(BaseController.class);

	@Autowired
	private ExpensesService expensesService;

	@Autowired
	private PersonService personService;
	
	private DecimalFormat decimalFormat = new DecimalFormat("######0.00");

	@Transactional
	protected List<Owed> calculatePersonsOwsMoneyList() throws Exception {
		List<Owed> owedList = new ArrayList<Owed>();
		List<Expenses> expensesList = expensesService.findAllExpenses();
		List<Person> personsList = personService.findAllPersons().stream().peek(person -> Hibernate.initialize(person.getExpenses())).collect(Collectors.toList());
		int personsCount = personService.findPersonsCount();
		double sumOfSpentExpenses = expensesList.stream().mapToDouble(e -> e.getAmount()).sum();
		double shareForEachPerson = sumOfSpentExpenses / personsCount;
		logger.info("sumOfSpentExpenses [ " + sumOfSpentExpenses + " ]");
		logger.info("shareForEachPerson [ " + shareForEachPerson + " ]");
		for (Person person : personsList) {
			double personSpent = person.getExpenses().stream().mapToDouble(e -> e.getAmount()).sum();
			double personOwsOrPulls = shareForEachPerson - personSpent;
			logger.info("Person [ " + person.getFullName() + "] ows to or pulls from the people is [ "
					+ decimalFormat.format(personOwsOrPulls) + " ]");
			
			if(personOwsOrPulls<0) 
				person.setOweMoney(false);
			else person.setOweMoney(true);
			person.setMoneyOwedOrPulled(Double.valueOf(decimalFormat.format(Math.abs(personOwsOrPulls))));
		}
		
		personsList.forEach(p-> logger.info("Person Is Owed Or Pulled money [ "+ p.isOweMoney() + " ] of money [ "+ p.getMoneyOwedOrPulled() +" ]"));
		List<Person> owedPersonsList = personsList.stream().filter(o-> o.isOweMoney() == true).collect(Collectors.toList());
		List<Person> pulledPersonsList = personsList.stream().filter(o-> o.isOweMoney() == false).collect(Collectors.toList());
		for(Person pulledPerson : pulledPersonsList) {
			double owdAmount = pulledPerson.getMoneyOwedOrPulled();
			for(Person owedPerson:owedPersonsList) {
				if(owdAmount>0) {
					if(owdAmount>owedPerson.getMoneyOwedOrPulled() && !(owedPerson.getMoneyOwedOrPulled() <=0)) {
						owdAmount = owdAmount - owedPerson.getMoneyOwedOrPulled();
						owedList.add(createOwed(owedPerson.getMoneyOwedOrPulled(),owedPerson.getFullName(),pulledPerson.getFullName()));
						pulledPerson.setMoneyOwedOrPulled(owdAmount);
						owedPerson.setMoneyOwedOrPulled(0.00);
					}
					else if(owdAmount<=owedPerson.getMoneyOwedOrPulled()){
						owedPerson.setMoneyOwedOrPulled(owedPerson.getMoneyOwedOrPulled() - owdAmount);
						owedList.add(createOwed(owdAmount,owedPerson.getFullName(),pulledPerson.getFullName()));
						pulledPerson.setMoneyOwedOrPulled(0);
						owdAmount = 0;
					}
					
				}
			}
		}
		owedList.forEach(p-> logger.info("Person [ "+p.getFromPerson()+" ] Owes money [ "+ p.getAmount() + " ] to the Person [ "+ p.getToPerson() +" ]"));
		return owedList;
	}
	
	private Owed createOwed(double amount,String fromPerson,String toPerson) {
		Owed owed = new Owed();
		owed.setAmount(Double.valueOf(decimalFormat.format(amount)));
		owed.setFromPerson(fromPerson);
		owed.setToPerson(toPerson);
		return owed;
	}
}

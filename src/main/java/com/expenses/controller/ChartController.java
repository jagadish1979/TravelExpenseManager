package com.expenses.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

import com.expenses.domain.ChartData;
import com.expenses.domain.Expenses;
import com.expenses.domain.MoneySpendByPersonsChartData;
import com.expenses.domain.Owed;
import com.expenses.domain.Person;
import com.expenses.service.ExpensesService;
import com.expenses.service.PersonService;
import com.expenses.util.ExpensesUtil;

/**
 * @author Jagadish Anala
 *
 */
@Controller
@RequestMapping("/")
public class ChartController extends BaseController {
	private static final Logger logger = LogManager.getLogger(ChartController.class);

	@Autowired
	private PersonService personService;

	@Autowired
	private ExpensesService expensesService;
	
	@Transactional
	@GetMapping(value = "/getMoneySpendByPersonsChartData", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MoneySpendByPersonsChartData> getMoneySpendByPersonsChartData() throws Exception {
		logger.info("Gettting Money Spend Chart Data ******!");
		MoneySpendByPersonsChartData chartData = new MoneySpendByPersonsChartData();
		List<Person> personsList =  personService.findAllPersons().stream().peek(person -> Hibernate.initialize(person.getExpenses())).collect(Collectors.toList());
		for(Person person:personsList) {
			chartData.getPersonNames().add(person.getFullName());
			chartData.getMoneySpentList().add(person.getExpenses().stream().mapToDouble(e -> e.getAmount()).sum());
		}
		
		return new ResponseEntity<MoneySpendByPersonsChartData>(chartData, HttpStatus.OK);
	}
	
	@Transactional
	@GetMapping(value = "/getExpensesByDescriptionChartData", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ChartData>> getExpensesByDescriptionChartData() throws Exception {
		logger.info("Gettting Expenses By Description Chart Data ******!");
		List<ChartData> chartDataList = new ArrayList<ChartData>();
		List<Expenses> expensesList = expensesService.findAllExpenses();

		Map<String,Double> expensesMap = expensesList.stream().collect(Collectors.groupingBy(Expenses::getDescription,Collectors.summingDouble(Expenses::getAmount)));
		expensesMap.forEach((description,amount)->{
			ChartData chartData = new ChartData();
			chartData.setLabel(description);
			chartData.setValue(amount);
			chartData.setColor(ExpensesUtil.colorCode());
			chartData.setHighlight(ExpensesUtil.colorCode());
			chartDataList.add(chartData); 
		});
		return new ResponseEntity<List<ChartData>>(chartDataList, HttpStatus.OK);
	}
	
	
	@Transactional
	@GetMapping(value = "/getOwesChartData", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ChartData>> getOwesChartData() throws Exception {
		logger.info("Gettting Owes From Person to Person Chart Data ******!");
		List<ChartData> chartDataList = new ArrayList<ChartData>();
		List<Owed> owedList = calculatePersonsOwsMoneyList();

		owedList.forEach(owed->{
			ChartData chartData = new ChartData();
			chartData.setLabel(owed.getFromPerson() + " Owes To " + owed.getToPerson());
			chartData.setValue(owed.getAmount());
			chartData.setColor(ExpensesUtil.colorCode());
			chartData.setHighlight(ExpensesUtil.colorCode());
			chartDataList.add(chartData); 
		});
		return new ResponseEntity<List<ChartData>>(chartDataList, HttpStatus.OK);
	}
}
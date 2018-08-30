package com.expenses.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.expenses.domain.Expenses;
import com.expenses.domain.Owed;
import com.expenses.service.ExpensesService;

/**
 * @author Jagadish Anala
 *
 */
@Controller
@RequestMapping("/")
public class ExpensesController extends BaseController {
	private static final Logger logger = LogManager.getLogger(ExpensesController.class);

	@Autowired
	private ExpensesService expensesService;

	@GetMapping(value = "/findExpensesCount", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> findExpensesCount() throws Exception {
		logger.info("Trying to find ExpensesCount ******!");
		Integer expensesCount = expensesService.findAllExpenses().size();
		return new ResponseEntity<Integer>(expensesCount, HttpStatus.OK);
	}

	
	@GetMapping(value = "/findAllExpenses", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Expenses>> findAllExpenses() throws Exception  {
		logger.info("Trying to find Expenses List ******!");
		List<Expenses> expensesList = expensesService.findAllExpenses();
		return new ResponseEntity<List<Expenses>>(expensesList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/findPersonsOwsMoneyCount", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> findPersonsOwsMoneyCount() throws Exception {
		int owesCount = calculatePersonsOwsMoneyList().size();
		return new ResponseEntity<Integer>(owesCount, HttpStatus.OK);
	}
	
	@GetMapping(value = "/calculatePersonsOwsMoney", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Owed>> calculatePersonsOwsMoney() throws Exception {
		List<Owed> owedList = calculatePersonsOwsMoneyList();
		return new ResponseEntity<List<Owed>>(owedList, HttpStatus.OK);
	}
	
}
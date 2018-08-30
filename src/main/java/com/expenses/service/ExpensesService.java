package com.expenses.service;

import java.util.List;

import com.expenses.domain.Expenses;

/**
 * @author Jagadish Anala
 *
 */
public interface ExpensesService {

	List<Expenses> findAllExpenses() throws Exception;
	
	List<Expenses> findAllExpensesSpentByPerson(Long personId) throws Exception;
}

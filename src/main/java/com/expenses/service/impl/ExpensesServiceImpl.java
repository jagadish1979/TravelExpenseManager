package com.expenses.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expenses.domain.Expenses;
import com.expenses.repository.ExpensesRepository;
import com.expenses.service.ExpensesService;

/**
 * @author Jagadish Anala
 *
 */
@Service("expensesService")
public class ExpensesServiceImpl implements ExpensesService {

	@Autowired
	private ExpensesRepository expensesRepository;
	
	@Override
	public List<Expenses> findAllExpenses() throws Exception {
		return expensesRepository.findAll();
	}

	@Override
	public List<Expenses> findAllExpensesSpentByPerson(Long personId) throws Exception {
		return expensesRepository.findBySpentBy(personId);
	}
}

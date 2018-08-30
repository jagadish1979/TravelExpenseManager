package com.expenses.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expenses.domain.Expenses;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, Long> {

	List<Expenses> findBySpentBy(Long id);

}

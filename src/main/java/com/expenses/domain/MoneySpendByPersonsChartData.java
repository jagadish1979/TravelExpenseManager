package com.expenses.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MoneySpendByPersonsChartData implements Serializable {

	private List<String> personNames;
	private List<Double> moneySpentList;

	public MoneySpendByPersonsChartData(){
		personNames = new ArrayList<String>();
		moneySpentList = new ArrayList<Double>();
	}
	public List<String> getPersonNames() {
		return personNames;
	}

	public void setPersonNames(List<String> personNames) {
		this.personNames = personNames;
	}

	public List<Double> getMoneySpentList() {
		return moneySpentList;
	}

	public void setMoneySpentList(List<Double> moneySpentList) {
		this.moneySpentList = moneySpentList;
	}

}

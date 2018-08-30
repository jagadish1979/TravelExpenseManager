package com.expenses.domain;

import java.io.Serializable;

public class Owed implements Serializable {

	private double amount;
	private String fromPerson;
	private String toPerson;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getFromPerson() {
		return fromPerson;
	}

	public void setFromPerson(String fromPerson) {
		this.fromPerson = fromPerson;
	}

	public String getToPerson() {
		return toPerson;
	}

	public void setToPerson(String toPerson) {
		this.toPerson = toPerson;
	}

	@Override
	public String toString() {
		return "Owed [amount=" + amount + ", fromPerson=" + fromPerson + ", toPerson=" + toPerson + "]";
	}

}

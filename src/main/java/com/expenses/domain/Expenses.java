package com.expenses.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Expenses extends AbstractEntity implements Serializable{

	@Column(name = "description")
	private String description;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="spentby")
	private Person spentBy;
	
	@Column(name = "spentdate")
	private Date spentDate;
	
	@Column(name = "amount")
	private Double amount;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Person getSpentBy() {
		return spentBy;
	}

	public void setSpentBy(Person spentBy) {
		this.spentBy = spentBy;
	}

	public Date getSpentDate() {
		return spentDate;
	}

	public void setSpentDate(Date spentDate) {
		this.spentDate = spentDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Expenses [description=" + description + ", spentBy=" + spentBy + ", spentDate=" + spentDate
				+ ", amount=" + amount + "]";
	}

}

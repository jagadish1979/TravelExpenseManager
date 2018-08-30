package com.expenses.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Person extends AbstractEntity implements Serializable{
	
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "email")
	private String eMail;
	
	@Column(name = "birth_date")
	private Date birthDate;
	
	@JsonBackReference
	@OneToMany(mappedBy = "spentBy")
	private List<Expenses> expenses;
	
	@Transient
	private String fullName;
	
	@Transient
	private boolean isOweMoney;
	
	@Transient
	private double moneyOwedOrPulled;

	public Person(){
	}
	
	public Person(String firstName,String lastName, String phone, String eMail,Date birthday){
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthday;
		this.eMail = eMail;
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}
	
	public List<Expenses> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expenses> expenses) {
		this.expenses = expenses;
	}
	
	public boolean isOweMoney() {
		return isOweMoney;
	}

	public void setOweMoney(boolean isOweMoney) {
		this.isOweMoney = isOweMoney;
	}

	public double getMoneyOwedOrPulled() {
		return moneyOwedOrPulled;
	}

	public void setMoneyOwedOrPulled(double moneyOwedOrPulled) {
		this.moneyOwedOrPulled = moneyOwedOrPulled;
	}
	
	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone + ", eMail=" + eMail
				+ ", birthDate=" + birthDate + ", expenses=" + expenses + ", fullName=" + fullName + ", isOweMoney="
				+ isOweMoney + "]";
	}
	
}

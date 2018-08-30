package com.expenses.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class User extends AbstractEntity {

	@Column(name = "username")
	private String userName;

	@Column(name = "password")
	private char[] password;

	@Column(name = "fullname")
	private String fullname;

	public User() {
	}

	public User(String userName, char[] password) {
		this.password = password;
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", fullname=" + fullname + "]";
	}

}

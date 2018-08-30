package com.expenses.service;

import com.expenses.domain.LoginModel;
import com.expenses.exception.DecodingException;
import com.expenses.exception.LoginException;

/**
 * @author Jagadish Anala
 *
 */
public interface LoginService {
	public LoginModel validateLogin(LoginModel loginModel) throws LoginException, DecodingException;
}

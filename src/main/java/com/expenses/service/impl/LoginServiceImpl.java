package com.expenses.service.impl;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.expenses.domain.LoginModel;
import com.expenses.domain.User;
import com.expenses.exception.DecodingException;
import com.expenses.exception.LoginException;
import com.expenses.repository.UserRepository;
import com.expenses.service.LoginService;

/**
 * @author Jagadish Anala
 *
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

	private static final Logger logger = Logger.getLogger(LoginServiceImpl.class.getName());

	private UserRepository userRepository;

	@Value("${error.login.invalid}")
	private String loginErrorMessage;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public LoginModel validateLogin(LoginModel loginModel) throws LoginException, DecodingException {
		loginModel = findLoginDetails(loginModel);
		if (loginModel != null) {
			return loginModel;
		} else
			throw new LoginException(loginErrorMessage);
	}

	// Need to modify the code when connected to database.
	private LoginModel findLoginDetails(LoginModel loginModel) throws LoginException, DecodingException {
		User loggedInUser = userRepository.findByUserNameAndPassword(loginModel.getUserName(), loginModel.getPassword());
		if (loggedInUser!=null) {
			logger.info(loggedInUser.toString());
			loginModel.setFullName(loggedInUser.getFullname());
			return loginModel;
		}
		return null;
	}
}

package com.dh.shortener.web;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.dh.shortener.model.Login;

public class LoginValidator {
	
	public void validate (Login login, Errors errors){
		
		String username = login.getUserName();
		String password = login.getPassword();
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Complete all fields");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "Complete all fields");
	}

}

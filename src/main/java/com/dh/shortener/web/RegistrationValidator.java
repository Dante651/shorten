package com.dh.shortener.web;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.dh.shortener.model.RegistrationHelper;

public class RegistrationValidator {
	
	public void validate(RegistrationHelper registration, Errors errors){
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "Complete all fields");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Complete all fields");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Complete all fields");
	}

}

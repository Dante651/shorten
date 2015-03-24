package com.dh.shortener.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dh.shortener.model.RegistrationHelper;
import com.dh.shortener.model.User;
import com.dh.shortener.services.UserService;

@Controller
@RequestMapping(value="/registration")
public class RegistrationController {
	
	private UserService userService;

	@Autowired
	public RegistrationController(UserService userService) {
		this.userService = userService;
	}
	
	@ModelAttribute("user")
	public User getUser(){
		return new User();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getRegistrationForm(Model model){
		model.addAttribute("jsName", "registration");
		model.addAttribute("title", "Registration");
		
		return "registration";
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String registerUser(@RequestBody @Validated RegistrationHelper registration, HttpServletResponse response, BindingResult result){
		new RegistrationValidator().validate(registration, result);
		if(result.hasErrors()){
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "Error in registration form";
		}
		try {
			User user = new User(registration);
			userService.addUser(user);
			response.setStatus(HttpServletResponse.SC_CREATED);
			return "User created";
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "An error occurred. Please try again";
		}
	}
	
	

}

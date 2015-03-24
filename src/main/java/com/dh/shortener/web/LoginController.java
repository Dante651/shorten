package com.dh.shortener.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dh.shortener.model.Login;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	
	private AuthenticationManager authenticationManager;
	
	@Autowired
	public LoginController(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@ModelAttribute("login")
	public Login getLogin(){
		return new Login();
	}
	
//	@SessionAttributes("user")
//	public User getUser(){
//		return new User();
//	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String loginForm(Model model, HttpServletRequest request) {
		model.addAttribute("title", "Login");
		model.addAttribute("jsName", "login");
		System.out.println(request.getSession().getId());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null && !auth.getName().equals("anonymousUser") && auth.isAuthenticated()){
			return "redirect:/";
		}
		return "login";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public String logIn(@RequestBody @Validated Login login, HttpServletResponse response, HttpServletRequest request, BindingResult result){
		new LoginValidator().validate(login, result);
		if(result.hasErrors()){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "Complete all fields";
		}
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login.getUserName(), login.getPassword());
		//token.setDetails(login);
		
		try {
			Authentication auth = authenticationManager.authenticate(token);
			SecurityContext secCon = SecurityContextHolder.getContext();
			secCon.setAuthentication(auth);
//			HttpSession session = request.getSession(true);
//			session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, secCon);
			response.setStatus(HttpServletResponse.SC_OK);
			
			return "Success";
			
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "Failed to log in";
		}
		
	}

}

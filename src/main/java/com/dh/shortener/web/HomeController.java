package com.dh.shortener.web;

import java.security.Principal;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dh.shortener.model.ShortenUrl;
import com.dh.shortener.model.SimpleUrl;
import com.dh.shortener.model.Url;
import com.dh.shortener.model.UserUrlHelper;
import com.dh.shortener.services.ShortenUrlService;
import com.dh.shortener.services.UserService;
import com.dh.shortener.services.UserUrlHelperService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private ShortenUrlService shortenUrlService;
	private UserService userService;
	private UserUrlHelperService userUrlHelperService;
	
	@Autowired
	public HomeController(ShortenUrlService shortenUrlService, UserService userService, UserUrlHelperService userUrlHelperService) {
		this.shortenUrlService = shortenUrlService;
		this.userService = userService;
		this.userUrlHelperService = userUrlHelperService;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		model.addAttribute("simpleUrl", new SimpleUrl());
		model.addAttribute("jsName", "simpleShorten");
		model.addAttribute("title", "Create URL");

		return "home";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.PUT)
	@ResponseBody
	public String addShortenUrl(@RequestBody @Validated Url url, HttpServletResponse response, Principal principal, BindingResult result){
		ValidationUtils.rejectIfEmptyOrWhitespace(result, "url", "Paste URL");
		if(result.hasErrors()){
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "Paste URL";
		}
		
		String genUrl = shortenUrlService.generateUrl();
		ShortenUrl shortenUrl = new ShortenUrl(url.getUrl(), genUrl);
		DateTime dt = new DateTime();
		shortenUrl.setDate(dt.toString("dd-MM-yyyy HH:mm:ss"));
		shortenUrlService.addShortenUrl(shortenUrl);
		if(principal != null){
			long userId = userService.findIdByUserName(principal.getName());
			long shortenId = shortenUrl.getId();
			UserUrlHelper userUrlHelper = new UserUrlHelper(userId, shortenId);
			userUrlHelperService.add(userUrlHelper);
		}
		response.setStatus(HttpServletResponse.SC_CREATED);
		return genUrl;
	}
	
	@RequestMapping(value="/{shorten}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.TEMPORARY_REDIRECT)
	public void redirect(@PathVariable("shorten") String shorten, HttpServletResponse response){
		String url = shortenUrlService.findUrlByShorten(shorten);
		shortenUrlService.incrementCount(shorten);
		response.setHeader("Location", url);	
		
		
	}
}

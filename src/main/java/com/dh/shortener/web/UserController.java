package com.dh.shortener.web;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dh.shortener.model.User;
import com.dh.shortener.services.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getUserPage(Model model) {
		model.addAttribute("title", "Users List");
		model.addAttribute("jsName", "usersList");
		return "user";
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Set<User> getUserList(HttpServletResponse response) {
		try {
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			return userService.findOnlyUsers();
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public String updateUser(@PathVariable("id") long id,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, String[]> action = request.getParameterMap();
		System.out.println(action.toString());
		if (action.containsKey("userName")) {
			if (action.get("userName")[0].isEmpty()) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return "Set username";
			} else {
				userService.updateUserName(action.get("userName")[0], id);
				System.out.println(action.get("userName")[0].isEmpty());
				response.setStatus(HttpServletResponse.SC_CREATED);
			}
		}
		if (action.containsKey("email")) {
			if (action.get("email")[0].isEmpty()) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return "Set email";
			} else {
				userService.updateEmail(action.get("email")[0], id);
				response.setStatus(HttpServletResponse.SC_CREATED);
			}
		}
		if (action.containsKey("role")) {
			if (action.get("role")[0].isEmpty()) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return "Set role";
			} else {
				userService.updateRole(action.get("role")[0], id);
				response.setStatus(HttpServletResponse.SC_CREATED);
			}
		}
		if (action.containsKey("password")) {
			if (action.get("password")[0].isEmpty()) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return "Set password";
			} else {
				userService.updatePassword(action.get("password")[0], id);
				response.setStatus(HttpServletResponse.SC_CREATED);
			}
		}
		response.setStatus(HttpServletResponse.SC_ACCEPTED);
		return "Updated";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getSingleUserPage(Model model) {
		model.addAttribute("title", "Edit user");
		model.addAttribute("jsName", "user");
		return "singleUser";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User getSpecifiedUser(@PathVariable("id") long id,
		HttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException {
		long userId = userService.findIdByUserName(principal.getName());
		if(userId != id){
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
			return null;
		}
		try {
			User user = userService.getUser(id);
			response.setStatus(HttpServletResponse.SC_OK);
			return user;
		} catch (NullPointerException e) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

}

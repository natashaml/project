package com.itran.booksjournal.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itran.booksjournal.model.User;
import com.itran.booksjournal.service.SecurityProcessor;
import com.itran.booksjournal.service.UserPageProcessor;

@Controller
@RequestMapping(value = "/users")
public class UserPageController
{
	@Autowired
	UserPageProcessor upProcessor;

	@Autowired
	SecurityProcessor securityProcessor;

	@RequestMapping(method = RequestMethod.GET)
	public String givePageOfUsers(ModelMap modelMap, Principal principal)
	{
		securityProcessor.identifyViewer(principal, modelMap);
		List<User> users = upProcessor.getUsers();
		modelMap.addAttribute("users", users);
		return "users";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String giveUserPage(@PathVariable("id") Integer id,
			ModelMap modelMap, Principal principal)
	{
		return securityProcessor.getSecuredUserPage(principal, modelMap, id);
	}

	@RequestMapping(value = "/{id}/newBook", method = RequestMethod.GET)
	public String giveNewBookPage(@PathVariable("id") Integer id,
			ModelMap modelMap, Principal principal)
	{
		securityProcessor.identifyViewer(principal, modelMap);
		return "newBook";
	}
}

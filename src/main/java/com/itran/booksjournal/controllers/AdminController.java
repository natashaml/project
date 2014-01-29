package com.itran.booksjournal.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itran.booksjournal.service.AdminProcessor;
import com.itran.booksjournal.service.SecurityProcessor;

@Controller
@RequestMapping(value = "/admin")
public class AdminController
{
	@Autowired
	AdminProcessor adminProcessor;

	@Autowired
	SecurityProcessor securityProcessor;

	@Secured("hasRole(1)")
	@RequestMapping(method = RequestMethod.GET)
	public String giveAdminPage(ModelMap modelMap, Principal principal)
	{
		securityProcessor.identifyViewer(principal, modelMap);
		adminProcessor.prepareUsers(modelMap);
		return "admin";
	}

	@Secured("hasRole(1)")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String goToDeleteUserPage(@PathVariable("id") Integer id,
			ModelMap modelMap)
	{
		adminProcessor.deleteUser(id);
		return "redirect:/admin";
	}
}

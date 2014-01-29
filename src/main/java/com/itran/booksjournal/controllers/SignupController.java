package com.itran.booksjournal.controllers;

import java.security.Principal;

import com.itran.booksjournal.service.SecurityProcessor;
import com.itran.booksjournal.service.SignupForm;
import com.itran.booksjournal.service.SignupProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/signup")
public class SignupController
{

	@Autowired
	private SignupProcessor signupProcessor;

	@Autowired
	private SecurityProcessor securityProcessor;

	@RequestMapping(method = RequestMethod.GET)
	public String giveSignupPage(ModelMap modelMap, Principal principal)
	{
		securityProcessor.identifyViewer(principal, modelMap);
		SignupForm signupForm = new SignupForm();
		modelMap.put("signupForm", signupForm);
		return "signup";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String giveActivation(@PathVariable("id") Integer id,
			ModelMap modelMap, Principal principal)
	{
		securityProcessor.identifyViewer(principal, modelMap);
		if (signupProcessor.activateUser(id))
			return "signup-success";
		else
			return "signup-error";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String signup(SignupForm signupForm, BindingResult result)
	{
		return signupProcessor.processSignup(signupForm, result);
	}
}

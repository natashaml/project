package com.itran.booksjournal.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itran.booksjournal.service.SecurityProcessor;

@Controller
@RequestMapping(value = "/login")
public class LoginController
{
	@Autowired
	SecurityProcessor securityProcessor;

	@RequestMapping(value = "/welcome")
	public String loginSuccesful(ModelMap modelMap, Principal principal)
	{
		securityProcessor.identifyViewer(principal, modelMap);
		modelMap.addAttribute("login_message", "Login Succesful");
		return "redirect:/";
	}

	@RequestMapping(value = "")
	public String giveloginPage(ModelMap modelMap)
	{
		return "login";
	}

	@Secured("hasAnyRole(0, 1)")
	@RequestMapping(value = "/logout")
	public String logout(ModelMap modelMap)
	{
		modelMap.addAttribute("login_message", "Logout Completed");
		return "redirect:/";
	}
}

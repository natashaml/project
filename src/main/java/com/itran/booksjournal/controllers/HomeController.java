package com.itran.booksjournal.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itran.booksjournal.service.HomeProcessor;
import com.itran.booksjournal.service.SecurityProcessor;

@Controller
public class HomeController
{
	@Autowired
	HomeProcessor homeProcessor;

	@Autowired
	SecurityProcessor securityProcessor;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String giveHomePage(ModelMap modelMap, Principal principal)
	{
		String tags = homeProcessor.getCloudTags();
		modelMap.addAttribute("tags", tags);
		securityProcessor.identifyViewer(principal, modelMap);
		return "home";
	}
}

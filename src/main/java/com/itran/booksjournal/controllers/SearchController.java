package com.itran.booksjournal.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itran.booksjournal.service.SearchProcessor;
import com.itran.booksjournal.service.SecurityProcessor;

@Controller
@RequestMapping("/search")
public class SearchController
{
	@Autowired
	SearchProcessor searchProcessor;

	@Autowired
	SecurityProcessor securityProcessor;

	@RequestMapping(value = "/{target}", method = RequestMethod.GET)
	public String searchTarget(
			@RequestParam(value = "searchBy", required = false) String searchBy,
			@PathVariable("target") String target, ModelMap modelMap,
			Principal principal)
	{
		securityProcessor.identifyViewer(principal, modelMap);
		searchProcessor.processSearch(searchBy, target, modelMap);
		return "search";
	}
}

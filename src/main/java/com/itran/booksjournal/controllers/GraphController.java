package com.itran.booksjournal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itran.booksjournal.service.GraphProcessor;

@Controller
public class GraphController
{

	@Autowired
	GraphProcessor graphProcessor;

	@RequestMapping(value = "/graph", method = RequestMethod.GET)
	public String setGraphic(ModelMap modelMap)
	{
		graphProcessor.getBooks(modelMap);
		return "graph";
	}

}

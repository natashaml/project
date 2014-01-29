package com.itran.booksjournal.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itran.booksjournal.service.ReadProcessor;

@Controller
@RequestMapping("/read")
public class ReadController
{
	@Autowired
	ReadProcessor readProcessor;

	@RequestMapping(value = "/{bookId}", method = RequestMethod.GET)
	public String readBook(@PathVariable("bookId") Integer bookId,
			ModelMap modelMap, Principal principal)
	{
		return readProcessor.readBookPage(principal, modelMap, bookId);
	}
	
}

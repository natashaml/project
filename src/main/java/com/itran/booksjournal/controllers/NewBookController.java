package com.itran.booksjournal.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itran.booksjournal.model.Book;
import com.itran.booksjournal.service.NewBookForm;
import com.itran.booksjournal.service.NewBookProcessor;
import com.itran.booksjournal.service.SecurityProcessor;

@Controller
public class NewBookController
{
	@Autowired
	NewBookProcessor nbProcessor;

	@Autowired
	SecurityProcessor securityProcessor;

	@RequestMapping(value = "users/{userId}/newBook", method = RequestMethod.POST)
	public String processForm(@PathVariable("userId") Integer userId,
			NewBookForm addBookForm, Principal principal, ModelMap modelMap)
	{
		securityProcessor.identifyViewer(principal, modelMap);
		Book newBook = nbProcessor.addBook(addBookForm, userId);
		if (newBook == null)
			return "newBook";
		else
			return "redirect:/users/" + userId + "/" + newBook.getId();
	}
}
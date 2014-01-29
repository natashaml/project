package com.itran.booksjournal.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itran.booksjournal.service.DeleteProcessor;

	@Controller
	@RequestMapping("/delete")
	public class DeleteController
	{
		@Autowired
		DeleteProcessor deleteProcessor;

		@RequestMapping(value = "/{bookId}", method = RequestMethod.GET)
		public String deleteBook(@PathVariable("bookId") Integer bookId,
				ModelMap modelMap, Principal principal)
		{
			return deleteProcessor.deleteBook(principal, modelMap, bookId);
		}
		
}

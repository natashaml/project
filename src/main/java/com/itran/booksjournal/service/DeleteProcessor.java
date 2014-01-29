package com.itran.booksjournal.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.itran.booksjournal.dao.BookDao;
import com.itran.booksjournal.dao.UserDao;
import com.itran.booksjournal.model.Book;

@Service
public class DeleteProcessor
{
	@Autowired
	private BookDao bookDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private SecurityProcessor securityProcessor;

	public String deleteBook(Principal principal, ModelMap modelMap,
			Integer bookId)
	{
		securityProcessor.identifyViewer(principal, modelMap);
		Book book = bookDao.getEagerById(bookId);
		bookDao.deleteBook(book);
		return "redirect:/";
	}
}


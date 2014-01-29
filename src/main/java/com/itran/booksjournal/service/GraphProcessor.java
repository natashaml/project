package com.itran.booksjournal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.itran.booksjournal.dao.BookDao;
import com.itran.booksjournal.model.Book;

@Service
public class GraphProcessor
{
    @Autowired
    BookDao bookDao;
    
   
	public void getBooks(ModelMap modelMap)
	{
		List<Book> books = bookDao.getPopular(5);
		modelMap.addAttribute("books", books);
	}
}

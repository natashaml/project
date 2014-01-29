package com.itran.booksjournal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itran.booksjournal.dao.BookDao;
import com.itran.booksjournal.dao.TagDao;
import com.itran.booksjournal.dao.UserDao;
import com.itran.booksjournal.model.Book;
import com.itran.booksjournal.model.Tag;

@Service
public class NewBookProcessor
{
	@Autowired
	SearchProcessor searchProcessor;

	@Autowired
	BookDao bookDao;

	@Autowired
	UserDao userDao;

	@Autowired
	TagDao tagDao;

	public Book addBook(NewBookForm newBookForm, Integer userId)
	{
		Book book = new Book();

		book.setName(newBookForm.getTitle());
		book.setCreative(newBookForm.getCreative());
		book.setSynopsis(newBookForm.getSynopsis());

		List<String> tagNames = searchProcessor.getKeyWords(newBookForm
				.getTags());
		List<Tag> tags = tagDao.addTags(tagNames);
		book.setTags(tags);

		book.setUser(userDao.getLazyById(userId));
		book.setVersion(1);
		book.setLastModified(System.currentTimeMillis());

		return bookDao.addBook(book);
	}

}

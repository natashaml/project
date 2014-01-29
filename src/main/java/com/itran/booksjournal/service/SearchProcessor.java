package com.itran.booksjournal.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.itran.booksjournal.dao.BookDao;
import com.itran.booksjournal.dao.ChapterDao;
import com.itran.booksjournal.dao.TagDao;
import com.itran.booksjournal.model.Book;
import com.itran.booksjournal.model.Chapter;
import com.itran.booksjournal.model.Tag;

@Service
public class SearchProcessor
{
	@Autowired
	TagDao tagDao;

	@Autowired
	BookDao bookDao;

	@Autowired
	ChapterDao chapterDao;

	public void processSearch(String searchBy, String target, ModelMap modelMap)
	{
		List<Book> books;

		if (searchBy == null)
			books = getByTags(target);
		else if (searchBy.equals("creative"))
			books = bookDao.searchByCreative(target);
		else if (searchBy.equals("title"))
			books = getByTitle(target);
		else
			books = getByTags(target);
		modelMap.addAttribute("books", books);
	}

	public List<String> getKeyWords(String searchInput)
	{
		List<String> keyWords = new LinkedList<String>();
		StringTokenizer st = new StringTokenizer(searchInput);
		while (st.hasMoreTokens())
		{
			keyWords.add(st.nextToken());
		}
		return keyWords;
	}

	private List<Book> getByTags(String searchInput)
	{
		List<String> keyWords = getKeyWords(searchInput);
		List<Tag> tags = tagDao.getRelatedTags(keyWords);
		HashSet<Book> result = new HashSet<Book>(500);
		Iterator<Tag> i = tags.iterator();
		while (i.hasNext())
		{
			result.addAll(i.next().getBooks());
		}
		ArrayList<Book> res = new ArrayList<Book>(result);
		Collections.sort(res, Collections.reverseOrder());
		return res;
	}

	private List<Book> getByTitle(String searchInput)
	{
		List<String> keyWords = getKeyWords(searchInput);
		List<Book> result = bookDao.searchByName(keyWords);
		Collections.sort(result, Collections.reverseOrder());
		return result;
	}
}

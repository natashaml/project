package com.itran.booksjournal.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itran.booksjournal.dao.TagDao;
import com.itran.booksjournal.dao.UserDao;
import com.itran.booksjournal.model.Tag;

@Service
public class HomeProcessor
{
    @Autowired
    UserDao userDao;
    
    @Autowired
	private TagDao tagDao;
          
    public String getCloudTags()
    {
		StringBuilder result = new StringBuilder();
		result.append("<tags>");
		Iterator<Tag> i = tagDao.getTags(60).iterator();
		while(i.hasNext())
		{
		    Tag current = i.next();
		    result.append("<a href='http://localhost:8080/booksjournal/search/");
		    result.append(current.getName());
		    result.append("' style='font-size:12pt;'>");
		    result.append(current.getName());
		    result.append("</a>");
		}
		result.append("</tags>");
		return result.toString();
    }
}

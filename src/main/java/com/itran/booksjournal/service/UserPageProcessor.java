package com.itran.booksjournal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itran.booksjournal.dao.UserDao;
import com.itran.booksjournal.model.User;

@Service
public class UserPageProcessor
{
	@Autowired
	UserDao userDao;

	public User getUser(String login)
	{
		return userDao.getLazyByName(login);
	}

	public List<User> getUsers()
	{
		return userDao.getLazyAllUsers();
	}
}

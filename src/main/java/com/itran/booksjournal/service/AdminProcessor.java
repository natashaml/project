package com.itran.booksjournal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.itran.booksjournal.dao.UserDao;
import com.itran.booksjournal.model.User;

@Service
public class AdminProcessor
{
	@Autowired
	UserDao userDao;

	public void prepareUsers(ModelMap modelMap)
	{
		List<User> users = userDao.getLazyAllUsers();
		modelMap.addAttribute("users", users);
	}

	public void deleteUser(Integer id)
	{
		User user = userDao.getLazyById(id);
		userDao.deleteUser(user);
	}

	public void setAdmin(Integer id)
	{
		userDao.setAsAdmin(id);
	}

	public void unsetAdmin(Integer id)
	{
		userDao.setAsUser(id);
	}
}

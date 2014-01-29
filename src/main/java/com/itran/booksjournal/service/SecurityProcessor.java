package com.itran.booksjournal.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.itran.booksjournal.dao.UserDao;
import com.itran.booksjournal.model.User;

@Service
public class SecurityProcessor
{
	@Autowired
	UserDao userDao;

	public void identifyViewer(Principal principal, ModelMap modelMap)
	{
		if (principal == null)
			modelMap.addAttribute("person", "Guest");
		else
			identifyUser(principal, modelMap);
	}

	public String getSecuredUserPage(Principal principal, ModelMap modelMap,
			Integer id)
	{
		User owner = userDao.getEagerById(id);
		if (owner == null)
			return "redirect:/";
		else
		{
			modelMap.addAttribute("owner", owner);

			if (principal == null)
				modelMap.addAttribute("isMyPage", "NO");
			else
			{
				User viewer = userDao.getLazyByName(principal.getName());
				modelMap.addAttribute("login", viewer.getName());

				if (owner.getId().equals(viewer.getId()))
					modelMap.addAttribute("isMyPage", "YES");
				else
					modelMap.addAttribute("isMyPage", "NO");
			}
			return "userPage";
		}
	}

	public boolean isMyEditorPage(Principal principal, Integer userId)
	{
		if (principal == null)
			return false;
		User user = userDao.getLazyByName(principal.getName());
		if (user == null)
			return false;
		if (user.getId().equals(userId))
			return true;
		return true;
	}

	private void identifyUser(Principal principal, ModelMap modelMap)
	{
		String login = principal.getName();
		User user = userDao.getLazyByName(login);

		if (user == null)
			modelMap.addAttribute("error", "Couldn't load user from database");
		else
		{
			if (user.getAdmin() == true)
				modelMap.addAttribute("person", "Admin");
			else
				modelMap.addAttribute("person", "User");

			modelMap.addAttribute("user", user);
		}
	}

}

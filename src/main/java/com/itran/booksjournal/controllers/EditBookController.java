package com.itran.booksjournal.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itran.booksjournal.service.EditBookProcessor;
import com.itran.booksjournal.service.EditChapterForm;

@Controller
public class EditBookController
{
	@Autowired
	EditBookProcessor ebProcessor;

	@RequestMapping(value = "/users/{userId}/{bookId}")
	public String getEditorPage(@PathVariable("userId") Integer userId,
			@PathVariable("bookId") Integer bookId, ModelMap modelMap,
			@RequestParam(value = "action", required = false) String action,
			Principal principal)
	{
		return ebProcessor.getEditBookPage(principal, userId, bookId, modelMap,
				action);
	}

	@RequestMapping(value = "/users/{userId}/{bookId}/{chapterId}", method = RequestMethod.POST)
	public String saveChapter(@PathVariable("userId") Integer userId,
			@PathVariable("bookId") Integer bookId,
			@PathVariable("chapterId") Integer chapterId, ModelMap modelMap,
			Principal principal, EditChapterForm saveChapterForm)
	{
		return ebProcessor.saveChapter(saveChapterForm, principal, userId,
				bookId, chapterId, modelMap);
	}

	/*
	 * @RequestMapping(value = "/users/{userId}/{bookId}/{chapterId}", method =
	 * RequestMethod.POST) public String saveMyChapter(@PathVariable("userId")
	 * Integer userId,
	 * 
	 * @PathVariable("bookId") Integer bookId,
	 * 
	 * @PathVariable("chapterId") Integer chapterId, ModelMap modelMap,
	 * Principal principal, EditChapterForm saveChapterForm) { return
	 * ebProcessor.saveMyChapter(saveChapterForm, principal, userId, bookId,
	 * chapterId, modelMap); }
	 */

	@RequestMapping(value = "/users/{userId}/{bookId}/{chapterId}", method = RequestMethod.GET)
	public String editChapter(@PathVariable("userId") Integer userId,
			@PathVariable("bookId") Integer bookId,
			@PathVariable("chapterId") Integer chapterId,
			@RequestParam(value = "action", required = false) String action,
			ModelMap modelMap, Principal principal)
	{
		return ebProcessor.editChapter(modelMap, principal, userId, bookId,
				chapterId, action);
	}

}

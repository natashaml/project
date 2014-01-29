package com.itran.booksjournal.service;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.itran.booksjournal.dao.BookDao;
import com.itran.booksjournal.dao.ChapterDao;
import com.itran.booksjournal.dao.UserDao;
import com.itran.booksjournal.model.Book;
import com.itran.booksjournal.model.Chapter;

@Service
public class EditBookProcessor
{
	@Autowired
	BookDao bookDao;

	@Autowired
	ChapterDao chapterDao;

	@Autowired
	UserDao userDao;

	@Autowired
	SecurityProcessor securityProcessor;

	// http://localhost:8080/booksjournal/users/{userId}/{bookId}
	public String getEditBookPage(Principal principal, Integer userId,
			Integer bookId, ModelMap modelMap, String action)
	{
		Book book = bookDao.getEagerById(bookId);
		if (!securityProcessor.isMyEditorPage(principal, userId)
				|| book == null)
			return "redirect:/";

		modelMap.addAttribute("book", book);

		if (action != null)
		{
			return performAction(action, book, 0, userId, bookId);
		}

		if (book.getChapters().isEmpty())
			return "edit";

		Chapter startChapter = book.getChapters().get(0);

		modelMap.addAttribute("currentChapter", startChapter);

		return "redirect:/users/" + userId + "/" + bookId + "/"
				+ startChapter.getId();
	}

	// http://localhost:8080/booksjournal/users/{userId}/{bookId}/{chapterId} POST
	public String saveChapter(EditChapterForm saveChapterForm,
			Principal principal, Integer userId, Integer bookId,
			Integer chapterId, ModelMap modelMap)
	{
		Book book = bookDao.getEagerById(bookId);
		if (!securityProcessor.isMyEditorPage(principal, userId)
				|| book == null)
			return "redirect:/";

		modelMap.addAttribute("user",
				userDao.getLazyByName(principal.getName()));
		modelMap.addAttribute("book", book);
		modelMap.addAttribute("currentChapter",
				saveChapter(chapterId, saveChapterForm));

		return "edit";
	}

	/*
	 * public String saveMyChapter(EditChapterForm saveChapterForm, Principal
	 * principal, Integer userId, Integer bookId, Integer chapterId, ModelMap
	 * modelMap) { Book book = bookDao.getEagerById(bookId);
	 * if(!securityProcessor.isMyEditorPage(principal, userId) || book == null)
	 * return "redirect:/";
	 * 
	 * List<Chapter> myChapter;
	 * 
	 * modelMap.addAttribute("user",
	 * userDao.getLazyByName(principal.getName()));
	 * modelMap.addAttribute("book", book);
	 * modelMap.addAttribute("currentChapter", saveChapter(chapterId,
	 * saveChapterForm));
	 * 
	 * return "edit"; }
	 */

	// http://localhost:8080/booksjournal/users/{userId}/{bookId}/{chapterId}
	public String editChapter(ModelMap modelMap, Principal principal,
			Integer userId, Integer bookId, Integer chapterId, String action)
	{
		if (chapterId == 0)
			return "redirect:/users/" + userId + "/" + bookId;

		Book book = bookDao.getEagerById(bookId);
		if (!securityProcessor.isMyEditorPage(principal, userId)
				|| book == null)
			return "redirect:/";

		if (action != null)
		{
			return performAction(action, book, chapterId, userId, bookId);
		}

		setChapter(modelMap, principal, book, chapterId);

		return "edit";
	}

	private void setChapter(ModelMap modelMap, Principal principal, Book book,
			Integer chapterId)
	{
		Chapter startChapter = chapterDao.findById(chapterId);
		if (startChapter != null)
		{
			modelMap.addAttribute("user",
					userDao.getLazyByName(principal.getName()));
			modelMap.addAttribute("book", book);
			modelMap.addAttribute("currentChapter", startChapter);
		}

	}

	private String performAction(String action, Book book, Integer chapterId,
			Integer userId, Integer bookId)
	{
		if (action.equals("delete"))
		{
			chapterDao.deleteById(chapterId);
		} else if (action.equals("addChapter"))
		{
			addChapter(book);
		}
		return "redirect:/users/" + userId + "/" + bookId;
	}

	private Chapter saveChapter(Integer id, EditChapterForm chapterForm)
	{
		Chapter updatedChapter = chapterDao.findById(id);

		if (updatedChapter == null)
			return null;

		updatedChapter.setTitle(chapterForm.getTitle());
		updatedChapter.setText(chapterForm.getChapterText());
		updatedChapter.setVersion(updatedChapter.getVersion() + 1);

		chapterDao.updateChapter(updatedChapter);

		return chapterDao.findById(id);
	}

	private Short lastChapterNumber(Book book)
	{
		List<Chapter> chapters = book.getChapters();
		Iterator<Chapter> i = chapters.iterator();
		Chapter cur;
		if (!i.hasNext())
			return 0;
		do
		{
			cur = i.next();
		} while (i.hasNext());
		return cur.getNumber();
	}

	private boolean addChapter(Book book)
	{
		if (book == null)
			return false;

		Chapter newChapter = new Chapter();

		newChapter.setBook(book);
		newChapter.setTitle("New Chapter");
		newChapter.setVersion(1);
		newChapter.setLastModified(System.currentTimeMillis());
		newChapter.setNumber((short) (lastChapterNumber(book) + 1));

		chapterDao.addNewChapter(newChapter);

		return true;
	}
}

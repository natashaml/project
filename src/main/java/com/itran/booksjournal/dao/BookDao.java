package com.itran.booksjournal.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itran.booksjournal.model.Book;

@Repository
public class BookDao
{
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public Book addBook(Book book)
	{
		if (getLazyByName(book.getName()).isEmpty())
		{
			createBook(book);
			return getLazyByName(book.getName()).get(0);
		} else
			return null;
	}

	@Transactional
	private void createBook(Book book)
	{
		sessionFactory.getCurrentSession().save(book);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Book> getPopular(Integer number)
	{
		return sessionFactory.getCurrentSession()
				.createQuery("FROM Book ORDER BY rating DESC")
				.setMaxResults(number).list();
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Book> getLastAdded(Integer number)
	{
		return sessionFactory.getCurrentSession()
				.createQuery("FROM Book ORDER BY lastModified DESC")
				.setMaxResults(number).list();
	}

	@Transactional
	public void deleteBook(Book book)
	{
		sessionFactory.getCurrentSession().delete(book);
	}

	@Transactional
	public Book getLazyById(Serializable id)
	{
		return (Book) sessionFactory.getCurrentSession().get(Book.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Book> getLazyByName(String name)
	{
		return (List<Book>) sessionFactory.getCurrentSession()
				.createQuery("FROM Book WHERE name =?").setString(0, name)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Book> searchByCreative(String creative)
	{
		return (List<Book>) sessionFactory.getCurrentSession()
				.createQuery("FROM Book WHERE creative =?")
				.setString(0, creative).list();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Book> searchBySynopsis(List<String> keyWords)
	{
		Session session = sessionFactory.getCurrentSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);

		Iterator<String> i = keyWords.iterator();
		HashSet<Book> result = new HashSet<Book>();

		while (i.hasNext())
		{
			QueryBuilder qb = fullTextSession.getSearchFactory()
					.buildQueryBuilder().forEntity(Book.class).get();
			org.apache.lucene.search.Query query = qb.keyword()
					.onField("synopsis").matching(i.next()).createQuery();

			org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(
					query, Book.class);

			result.addAll(hibQuery.list());
		}
		return new LinkedList<Book>(result);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Book> searchByName(List<String> keyWords)
	{
		Iterator<String> i = keyWords.iterator();
		List<Book> result = new LinkedList<Book>();

		while (i.hasNext())
		{
			Session session = sessionFactory.getCurrentSession();
			FullTextSession fullTextSession = Search
					.getFullTextSession(session);

			QueryBuilder qb = fullTextSession.getSearchFactory()
					.buildQueryBuilder().forEntity(Book.class).get();
			org.apache.lucene.search.Query query = qb.keyword().onField("name")
					.matching(i.next()).createQuery();

			org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(
					query, Book.class);

			result.addAll(hibQuery.list());
		}

		return result;
	}

	@Transactional
	@SuppressWarnings("deprecation")
	public Book getEagerById(Serializable id)
	{
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Book.class)
				.setFetchMode("chapters", FetchMode.EAGER)
				.add(Restrictions.idEq(id));
		return (Book) c.uniqueResult();
	}

	@Transactional
	public void updateBook(Book book)
	{
		sessionFactory.getCurrentSession().update(book);
	}
}

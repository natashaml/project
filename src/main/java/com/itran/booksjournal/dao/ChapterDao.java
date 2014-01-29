package com.itran.booksjournal.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itran.booksjournal.model.Chapter;

@Repository
public class ChapterDao
{
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public Chapter findById(Serializable id)
	{
		return (Chapter) sessionFactory.getCurrentSession().get(Chapter.class,
				id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Chapter> searchByText(List<String> keyWords)
	{
		Session session = sessionFactory.getCurrentSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);

		Iterator<String> i = keyWords.iterator();
		HashSet<Chapter> result = new HashSet<Chapter>();

		while (i.hasNext())
		{
			QueryBuilder qb = fullTextSession.getSearchFactory()
					.buildQueryBuilder().forEntity(Chapter.class).get();
			org.apache.lucene.search.Query query = qb.keyword().onField("text")
					.matching(i.next()).createQuery();

			org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(
					query, Chapter.class);

			result.addAll(hibQuery.list());
		}
		return new ArrayList<Chapter>(result);
	}

	@Transactional
	public void updateChapter(Chapter updatedChapter)
	{
		Session session = sessionFactory.getCurrentSession();

		String queryText = "UPDATE Chapter SET title = :title,"
				+ " text = :text, lastModified = :lastModified,"
				+ " version = :version WHERE id = :id";
		Query query = session.createQuery(queryText);
		query.setParameter("title", updatedChapter.getTitle());
		query.setParameter("text", updatedChapter.getText());
		query.setParameter("lastModified", System.currentTimeMillis());
		query.setParameter("version", updatedChapter.getVersion());
		query.setParameter("id", updatedChapter.getId());

		query.executeUpdate();
	}

	@Transactional
	public void deleteById(Integer chapterId)
	{
		Session session = sessionFactory.getCurrentSession();

		Chapter ch = (Chapter) session.get(Chapter.class, chapterId);
		session.delete(ch);

	}

	@Transactional
	public void addNewChapter(Chapter newChapter)
	{
		Session session = sessionFactory.getCurrentSession();
		session.save(newChapter);
	}
}

package com.itran.booksjournal.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itran.booksjournal.model.Tag;

@Repository
public class TagDao
{
    @Autowired
    SessionFactory sessionFactory;
    
    @Cacheable(value = "tags")
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Tag> getTags()
    {
	return sessionFactory.getCurrentSession()
		.createQuery("SELECT FROM tag ORDER BY popularity DESC").list();
    }

    @Cacheable(value = "tags")
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Tag> getTags(Integer number)
    {
	Query q = sessionFactory.getCurrentSession().createQuery(
		"FROM Tag ORDER BY popularity DESC");
	q.setMaxResults(number);
	return q.list();
    }

    @Cacheable(value = "tags")
    @Transactional
    public Tag findById(Serializable id)
    {
	return (Tag) sessionFactory.getCurrentSession().get(Tag.class, id);
    }

    @CacheEvict(value = "tags",allEntries=true)
    @Transactional
    public List<Tag> addTags(List<String> tagNames)
    {
	Iterator<String> i = tagNames.iterator();
	LinkedList<Tag> result = new LinkedList<Tag>();

	while(i.hasNext())
	    result.add(addTag(i.next()));
	
	return result;
    }

    @CacheEvict(value = {"users","books","chapters","tags"},allEntries=true)
    @Transactional
    public Tag addTag(String name)
    {
	Tag existingTag = findByName(name);
	if(existingTag == null)
	{
	    Tag newTag = new Tag();
	    newTag.setName(name);
	    createTag(newTag);
	    return findByName(name);
	}
	else
	    return existingTag;
    }

    @CacheEvict(value = {"users","books","chapters","tags"},allEntries=true)
    @Transactional
    private void createTag(Tag newTag)
    {
	sessionFactory.getCurrentSession().save(newTag);
    }

    @Cacheable(value = "tags")
    @Transactional
    public Tag findByName(String name)
    {
	return (Tag) sessionFactory.getCurrentSession()
		.createQuery("FROM Tag WHERE name =?").setString(0, name)
		.uniqueResult();
    }

    @Cacheable(value = "tags")
    @Transactional
    public List<Tag> getRelatedTags(List<String> keyWords)
    {

	Iterator<String> i = keyWords.iterator();
	List<Tag> relatedTags = new LinkedList<Tag>();
	while(i.hasNext())
	{
	    String current = i.next();
	    relatedTags.addAll(getTagsLike(current));
	}
	return relatedTags;
    }

    @Cacheable(value = "tags")
    @SuppressWarnings({ "unchecked", "deprecation" })
    @Transactional
    public List<Tag> getTagsLike(String likeWhat)
    {
	Session session = sessionFactory.getCurrentSession();
	Criteria cr = session.createCriteria(Tag.class).setFetchMode("books",
		FetchMode.EAGER);
	return (List<Tag>) cr.add(
		Restrictions.like("name", likeWhat, MatchMode.ANYWHERE)).list();
    }

}

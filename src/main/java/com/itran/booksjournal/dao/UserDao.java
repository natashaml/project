package com.itran.booksjournal.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itran.booksjournal.model.User;

@Repository
public class UserDao
{
    @Autowired
    SessionFactory sessionFactory;
    

    @Transactional
    @SuppressWarnings("unchecked")
    public List<User> getLazyAllUsers()
    {
	return sessionFactory.getCurrentSession().createQuery("FROM User")
		.list();
    }


    @Transactional
    public User getLazyByName(String name)
    {
	return (User) sessionFactory.getCurrentSession()
		.createCriteria(User.class).add(Restrictions.eq("name", name))
		.uniqueResult();
    }


    @Transactional
    public User getLazyById(Serializable userId)
    {
	return (User) sessionFactory.getCurrentSession()
		.get(User.class, userId);
    }


    @Transactional
    @SuppressWarnings("deprecation")
    public User getEagerById(Integer id)
    {
	Session session = sessionFactory.getCurrentSession();
	Criteria cr = session.createCriteria(User.class).setFetchMode("books",
		FetchMode.EAGER);
	return (User) cr.add(Restrictions.eq("id", id)).uniqueResult();
    }


    @Transactional
    public boolean setAsActivated(Integer userId)
    {
	Query query = sessionFactory.getCurrentSession().createQuery(
		"UPDATE User SET activated=1 WHERE id= ? ");
	query.setInteger(0, userId);
	return query.executeUpdate() > 0;
    }


    @Transactional
    public boolean setAsAdmin(Integer userId)
    {
	Query query = sessionFactory.getCurrentSession().createQuery(
		"UPDATE User SET admin=true WHERE id= ? ");
	query.setInteger(0, userId);
	return query.executeUpdate() > 0;
    }


    @Transactional
    public boolean setAsUser(Integer userId)
    {
	Query query = sessionFactory.getCurrentSession().createQuery(
		"UPDATE User SET admin=false WHERE id= ? ");
	query.setInteger(0, userId);
	return query.executeUpdate() > 0;
    }


    @Transactional
    public void createUser(User user)
    {
	sessionFactory.getCurrentSession().save(user);	
    }


    @Transactional
    public void deleteUser(User user)
    {
	sessionFactory.getCurrentSession().delete(user);	
    }
}

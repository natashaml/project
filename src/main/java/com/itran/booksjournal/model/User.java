package com.itran.booksjournal.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.hibernate.search.annotations.IndexedEmbedded;

@SuppressWarnings("serial")
@Entity
@Table(name = "user")
public class User extends Model implements Serializable
{

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private String email;

	private String password;

	private boolean admin;

	private boolean activated;

	@IndexedEmbedded
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Book> books;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public List<Book> getBooks()
	{
		return books;
	}

	public void setBooks(List<Book> books)
	{
		this.books = books;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public boolean getAdmin()
	{
		return admin;
	}

	public void setAdmin(boolean admin)
	{
		this.admin = admin;
	}

	public boolean isActivated()
	{
		return activated;
	}

	public void setActivated(boolean activated)
	{
		this.activated = activated;
	}

	public static String getEntityName()
	{
		return "user";
	}

}
package com.itran.booksjournal.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "tag")
public class Tag extends Model implements Serializable
{
	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private Integer popularity;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags", cascade = CascadeType.PERSIST)
	private List<Book> books;

	public List<Book> getBooks()
	{
		return books;
	}

	public void setBooks(List<Book> books)
	{
		this.books = books;
	}

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

	public Integer getPopularity()
	{
		return popularity;
	}

	public void setPopularity(Integer popularity)
	{
		this.popularity = popularity;
	}

	public static String getEntityName()
	{
		return "tag";
	}

}

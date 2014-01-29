package com.itran.booksjournal.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@SuppressWarnings("serial")
@Indexed(index = "fulltext")
@Entity
@Table(name = "chapter")
public class Chapter extends Model implements Comparable<Chapter>, Serializable
{

	@Id
	@GeneratedValue
	private Integer id;

	private short number;

	private short position;

	private Long lastModified;

	private String title;

	@Column(name = "text", columnDefinition = "TEXT")
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String text;

	@Version
	private Integer version;

	@ManyToOne
	@JoinColumn(name = "bookId")
	private Book book;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Book getBook()
	{
		return book;
	}

	public void setBook(Book book)
	{
		this.book = book;
	}

	public short getNumber()
	{
		return number;
	}

	public void setNumber(short number)
	{
		this.number = number;
	}

	public short getPosition()
	{
		return position;
	}

	public void setPosition(short position)
	{
		this.position = position;
	}

	public Long getLastModified()
	{
		return lastModified;
	}

	public void setLastModified(Long lastModified)
	{
		this.lastModified = lastModified;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public Integer getVersion()
	{
		return version;
	}

	public void setVersion(Integer version)
	{
		this.version = version;
	}

	@Override
	public int compareTo(Chapter c)
	{
		return this.number - c.number;
	}

	public static String getEntityName()
	{
		return "chapter";
	}
}

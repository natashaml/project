package com.itran.booksjournal.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

@SuppressWarnings("serial")
@Entity
@Indexed
@Table(name = "book")
public class Book extends Model implements Comparable<Book>, Serializable
{
    @Id
    @GeneratedValue
    private Integer id;

    private Long lastModified;

    @Column(name = "synopsis", columnDefinition = "TEXT")
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String synopsis;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String name;

    private String creative;

    private Integer rating;

    @Version
    private Integer version;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Fetch(FetchMode.SELECT)
    @ManyToMany(fetch = FetchType.LAZY, cascade =
    { CascadeType.PERSIST })
    @JoinTable(name = "bookTag", joinColumns =
    { @JoinColumn(name = "bookId") }, inverseJoinColumns =
    { @JoinColumn(name = "tagId") })
    private List<Tag> tags;

    @IndexedEmbedded
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chapter> chapters;

    public List<Tag> getTags()
    {
	return tags;
    }

    public void setTags(List<Tag> tags)
    {
	this.tags = tags;
    }

    public List<Chapter> getChapters()
    {
	//Collections.sort(chapters);
	return chapters;
    }

    public void setChapters(List<Chapter> chapters)
    {
	this.chapters = chapters;
    }

    public Chapter getChapter(Integer number)
    {
	Iterator<Chapter> i = chapters.iterator();
	while(i.hasNext())
	{
	    Chapter current = i.next();
	    if(current.getNumber() == number)
		return current;
	}
	return null;
    }

    public Integer getId()
    {
	return id;
    }

    public void setId(Integer id)
    {
	this.id = id;
    }

    public User getUser()
    {
	return user;
    }

    public void setUser(User user)
    {
	this.user = user;
    }

    public Long getLastModified()
    {
	return lastModified;
    }

    public void setLastModified(Long lastModified)
    {
	this.lastModified = lastModified;
    }

    public String getSynopsis()
    {
	return synopsis;
    }

    public void setSynopsis(String synopsis)
    {
	this.synopsis = synopsis;
    }

    public String getCreative()
    {
	return creative;
    }

    public void setCreative(String creative)
    {
	this.creative = creative;
    }

    public Integer getRating()
    {
	return rating;
    }

    public void setRating(Integer rating)
    {
	this.rating = rating;
    }

    public Integer getVersion()
    {
	return version;
    }

    public void setVersion(Integer version)
    {
	this.version = version;
    }

    public String getName()
    {
	return name;
    }

    public void setName(String name)
    {
	this.name = name;
    }

    public static String getEntityName()
    {
	return "book";
    }

    @Override
    public int compareTo(Book another)
    {
	return this.getRating() - another.getRating();
    }
}

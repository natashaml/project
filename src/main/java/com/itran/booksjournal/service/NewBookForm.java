package com.itran.booksjournal.service;

public class NewBookForm
{
	private String title;

	private String tags;

	private String creative;

	private String synopsis;

	public String getSynopsis()
	{
		return synopsis;
	}

	public void setSynopsis(String synopsis)
	{
		this.synopsis = synopsis;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getTags()
	{
		return tags;
	}

	public void setTags(String tags)
	{
		this.tags = tags;
	}

	public String getCreative()
	{
		return creative;
	}

	public void setCreative(String creative)
	{
		this.creative = creative;
	}
}

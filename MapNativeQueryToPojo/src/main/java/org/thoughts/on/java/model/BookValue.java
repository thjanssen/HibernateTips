package org.thoughts.on.java.model;

import java.util.Date;

public class BookValue {

	private String title;
	private Date publishingDate;

	public BookValue(String title, Date publishingDate) {
		this.title = title;
		this.publishingDate = publishingDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPublishingDate() {
		return publishingDate;
	}

	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}

}

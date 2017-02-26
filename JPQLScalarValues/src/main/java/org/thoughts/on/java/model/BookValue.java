package org.thoughts.on.java.model;

public class BookValue {

	public Long id;
	public String title;
	public String publisherName;

	public BookValue(Long id, String title, String publisherName) {
		this.id = id;
		this.title = title;
		this.publisherName = publisherName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	@Override
	public String toString() {
		return "BookValue [id=" + id + ", title=" + title + ", publisherName="
				+ publisherName + "]";
	}
}

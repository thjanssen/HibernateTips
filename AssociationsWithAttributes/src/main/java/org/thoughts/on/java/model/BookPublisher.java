package org.thoughts.on.java.model;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class BookPublisher {

	@EmbeddedId
	private BookPublisherId id;
	
	@Enumerated(EnumType.STRING)
	private Format format;
	
	@ManyToOne
	@JoinColumn(name = "fk_book")
	@MapsId("bookId")
	private Book book;
	
	@ManyToOne
	@JoinColumn(name = "fk_publisher")
	@MapsId("publisherId")
	private Publisher publisher;

	public BookPublisherId getId() {
		return id;
	}

	public void setId(BookPublisherId id) {
		this.id = id;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		return "BookPublisher [id=" + id + ", format=" + format + "]";
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookPublisher other = (BookPublisher) obj;
		
		return Objects.equals(id, other.id);
	}
	
	
}

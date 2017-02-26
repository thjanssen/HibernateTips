package org.thoughts.on.java.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
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
	
	@Embeddable
	public static class BookPublisherId implements Serializable {

		private static final long serialVersionUID = -3287715633608041039L;

		private Long bookId;
		
		private Long publisherId;

		public BookPublisherId() {
			
		}
		
		public BookPublisherId(Long bookId, Long publisherId) {
			this.bookId = bookId;
			this.publisherId = publisherId;
		}

		public Long getBookId() {
			return bookId;
		}

		public Long getPublisherId() {
			return publisherId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((bookId == null) ? 0 : bookId.hashCode());
			result = prime * result
					+ ((publisherId == null) ? 0 : publisherId.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			BookPublisherId other = (BookPublisherId) obj;

			return Objects.equals(bookId, other.bookId) 
					&& Objects.equals(publisherId, other.getPublisherId());
		}

		@Override
		public String toString() {
			return "BookPublisherId [bookId=" + bookId + ", publisherId="
					+ publisherId + "]";
		}
	}

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

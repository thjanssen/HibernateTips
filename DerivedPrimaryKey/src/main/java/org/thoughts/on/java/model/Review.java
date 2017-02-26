package org.thoughts.on.java.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Review {

	@EmbeddedId
	private ReviewId id;

	private String comment;

	@ManyToOne
	@JoinColumn(name = "fk_book")
	@MapsId("bookId")
	private Book book;

	@Embeddable
	public static class ReviewId implements Serializable {

		private static final long serialVersionUID = -5073745645379676235L;

		private String userName;

		private Long bookId;

		public ReviewId() {

		}

		public ReviewId(String userName, Long bookId) {
			this.userName = userName;
			this.bookId = bookId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public Long getBookId() {
			return bookId;
		}

		public void setBookId(Long bookId) {
			this.bookId = bookId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((bookId == null) ? 0 : bookId.hashCode());
			result = prime * result
					+ ((userName == null) ? 0 : userName.hashCode());
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

			ReviewId other = (ReviewId) obj;

			return Objects.equals(bookId, other.bookId)
					&& Objects.equals(userName, other.userName);
		}
	}

	public ReviewId getId() {
		return id;
	}

	public void setId(ReviewId id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
		Review other = (Review) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

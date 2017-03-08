package org.thoughts.on.java.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class BookPublisherId implements Serializable {

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
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
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
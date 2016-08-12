package org.thoughts.on.java.model;

import javax.persistence.Entity;

@Entity(name = "Book")
public class Book extends Publication {

	private static final long serialVersionUID = 7599585067389345376L;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Book)) {
			return false;
		}
		Book other = (Book) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}
}
package org.thoughts.on.java.model;

import javax.persistence.Entity;

@Entity
public class Book extends Publication {

	private int numPages;

	public int getNumPages() {
		return numPages;
	}

	public void setNumPages(int numPages) {
		this.numPages = numPages;
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
		Book other = (Book) obj;
		if (getId() != null) {
			if (!getId().equals(other.getId())) {
				return false;
			}
		}
		return true;
	}
}
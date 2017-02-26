package org.thoughts.on.java.model;

import javax.persistence.Entity;

@Entity
public class BlogPost extends Publication {

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "BlogPost [url=" + url + "]";
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
		BlogPost other = (BlogPost) obj;
		if (getId() != null) {
			if (!getId().equals(other.getId())) {
				return false;
			}
		}
		return true;
	}
	
	
}

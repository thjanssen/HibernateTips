package org.thoughts.on.java.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestUnidirectionalManyToMany {

	Logger log = Logger.getLogger(this.getClass().getName());

	private EntityManagerFactory emf;

	@Before
	public void init() {
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
	}

	@After
	public void close() {
		emf.close();
	}

	@Test
	public void unidirectionalManyToMany() {
		log.info("... unidirectionalManyToMany ...");

		// Add a new Review
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Book b = em.find(Book.class, 1L);
		
		Author a = new Author();
		a.setFirstName("Thorben");
		a.setLastName("Janssen");
		
		b.getAuthors().add(a);
		
		em.persist(a);
		
		em.getTransaction().commit();
		em.close();
		
		// Get Book entity with Authors
		em = emf.createEntityManager();
		em.getTransaction().begin();

		b = em.find(Book.class, 1L);
		
		List<Author> authors = b.getAuthors();
		Assert.assertTrue(authors.contains(a));
		
		em.getTransaction().commit();
		em.close();
	}
}

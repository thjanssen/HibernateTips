package org.thoughts.on.java.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestUnidirectionalOneToMany {

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
	public void unidirectionalOneToMany() {
		log.info("... bidirectionalOneToMany ...");

		// Add a new Review
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Book b = em.find(Book.class, 1L);
		
		Review r = new Review();
		r.setComment("This is a comment");
		r.setBook(b);
				
		em.persist(r);
		
		em.getTransaction().commit();
		em.close();
		
		// Get Book entity with Reviews
		em = emf.createEntityManager();
		em.getTransaction().begin();

		r = em.find(Review.class, 1L);
		
		b = r.getBook();
		Assert.assertEquals(new Long(1), b.getId());
		
		em.getTransaction().commit();
		em.close();
	}
}

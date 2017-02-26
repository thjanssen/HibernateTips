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
		log.info("... unidirectionalOneToMany ...");

		// Add a new Review
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Book b = em.find(Book.class, 1L);
		
		Review r = new Review();
		r.setComment("This is a comment");
		
		b.getReviews().add(r);
		
		em.persist(r);
		
		em.getTransaction().commit();
		em.close();
		
		// Get Book entity with Reviews
		em = emf.createEntityManager();
		em.getTransaction().begin();

		b = em.find(Book.class, 1L);
		
		List<Review> reviews = b.getReviews();
		Assert.assertTrue(reviews.contains(r));
		
		em.getTransaction().commit();
		em.close();
	}
}

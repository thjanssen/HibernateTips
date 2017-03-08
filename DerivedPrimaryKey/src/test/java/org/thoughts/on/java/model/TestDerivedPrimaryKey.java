package org.thoughts.on.java.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDerivedPrimaryKey {

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
	public void derivedPrimaryKey() {
		log.info("... derivedPrimaryKey ...");

		// Persist a new Review
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
	
		Review r = new Review();
		r.setId(new ReviewId());
		r.getId().setUserName("peter");
		r.setBook(em.find(Book.class, 1L));
		
		r.setComment("This is a comment");
		
		em.persist(r);
		
		em.getTransaction().commit();
		em.close();
		
		// Read the Review
		em = emf.createEntityManager();
		em.getTransaction().begin();

		r = em.find(Review.class, new ReviewId("peter", 1L));
		Assert.assertNotNull(r);
		
		em.getTransaction().commit();
		em.close();
	}
}

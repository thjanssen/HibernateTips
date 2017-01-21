package org.thoughts.on.java.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMapOptionalAssociation {

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
	public void testOptional() {
		log.info("... testOptional ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Book b = em.find(Book.class, 1L);
		Assert.assertTrue(b.getPublisher().isPresent());
		log.info(b.getTitle() + " was published by " + b.getPublisher().get().getName());
		
		b = em.find(Book.class, 2L);
		Assert.assertFalse(b.getPublisher().isPresent());
		log.info(b.getTitle() + " has no publisher");

		em.getTransaction().commit();
		em.close();
	}
}

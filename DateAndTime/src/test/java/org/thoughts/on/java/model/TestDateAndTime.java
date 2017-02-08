package org.thoughts.on.java.model;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDateAndTime {

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
	public void persistDateAndTime() {
		log.info("... persistDateAndTime ...");

		EntityManager em = emf.createEntityManager();
		
		// Persist a new Book entity
		em.getTransaction().begin();
		
		Book b = new Book();
		b.setTitle("Hibernate Tips");
		b.setPublishingDate(LocalDate.of(2017, 4, 4));
		em.persist(b);
		
		log.info("Persisted: "+b);
		
		em.getTransaction().commit();
		
		// Read the new entity in a 2nd transaction
		em.getTransaction().begin();
		
		Book b2 = em.find(Book.class, b.getId());
		Assert.assertEquals(b.getId(), b2.getId());
		Assert.assertEquals(b.getPublishingDate(), b2.getPublishingDate());
		
		log.info("Read: " + b2);
		
		em.getTransaction().commit();
		
		em.close();
	}
}

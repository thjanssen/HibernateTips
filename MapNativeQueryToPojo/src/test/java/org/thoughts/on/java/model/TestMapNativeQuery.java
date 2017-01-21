package org.thoughts.on.java.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMapNativeQuery {

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
	public void explicitMapping() {
		log.info("... explicitMapping ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		BookValue b = (BookValue) em.createNativeQuery("SELECT b.publishingDate as date, b.title, b.id FROM book b WHERE b.id = 1", "BookValueMapping").getSingleResult();
		Assert.assertTrue(b instanceof BookValue);
		Assert.assertEquals("Effective Java", b.getTitle());
		
		em.getTransaction().commit();
		em.close();
	}
}

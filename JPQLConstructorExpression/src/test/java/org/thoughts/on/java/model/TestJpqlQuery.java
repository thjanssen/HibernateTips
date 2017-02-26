package org.thoughts.on.java.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestJpqlQuery {

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
	public void pojoProjection() {
		log.info("... pojoProjection ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		TypedQuery<BookValue> q = em.createQuery("SELECT new org.thoughts.on.java.model.BookValue(b.id, b.title, b.publisher.name) FROM Book b WHERE b.id = :id", BookValue.class);
		q.setParameter("id", 1L);
		BookValue b = q.getSingleResult();
		
		Assert.assertTrue(b instanceof BookValue);
		Assert.assertEquals(new Long(1), ((BookValue)b).getId());
		log.info(b);
		
		em.getTransaction().commit();
		em.close();
	}
}

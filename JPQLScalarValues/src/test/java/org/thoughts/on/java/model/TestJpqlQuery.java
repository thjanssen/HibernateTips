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
	public void scalarValueProjection() {
		log.info("... scalarValueProjection ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		TypedQuery<Object[]> q = em.createQuery("SELECT b.title, b.publisher.name FROM Book b WHERE b.id = :id", Object[].class);
		q.setParameter("id", 1L);
		Object[] result = q.getSingleResult();
		
		Assert.assertTrue(result[0] instanceof String);
		Assert.assertEquals("Hibernate Tips", result[0]);
		Assert.assertTrue(result[1] instanceof String);
		log.info(result[0] + " was published by " + result[1]);
		
		em.getTransaction().commit();
		em.close();
	}
}

package org.thoughts.on.java.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPrimaryKey {

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
	public void testPrimaryKey() {
		log.info("... testPrimaryKey ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = new Author();
		a.setId(1L);
		a.setFirstName("Thorben");
		a.setLastName("Janssen");
		
		log.info("Persist new Author entity.");
		em.persist(a);
		
		log.info("Call flush");
		em.flush();
		
		em.getTransaction().commit();
		em.close();
		
		em = emf.createEntityManager();
		em.getTransaction().begin();

		a = em.find(Author.class, 1L);
		Assert.assertEquals(new Long(1), a.getId());
		
		em.getTransaction().commit();
		em.close();
	}
}

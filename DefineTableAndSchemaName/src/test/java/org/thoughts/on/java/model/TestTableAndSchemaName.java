package org.thoughts.on.java.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestTableAndSchemaName {

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
	public void selectFromView() {
		log.info("... selectFromView ...");

		// Persist a new Author entity
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = new Author();
		a.setFirstName("firstName");
		a.setLastName("lastName");
		em.persist(a);
		
		em.getTransaction().commit();
		em.close();
		
		// Use a new EntityManager to read the Author entity from the database and not the cache
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		a = em.createQuery("SELECT a FROM Author a WHERE firstName = 'firstName'", Author.class).getSingleResult();
		Assert.assertNotNull(a);
		log.info(a);
		
		em.getTransaction().commit();
		em.close();
	}
}

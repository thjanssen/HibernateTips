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
	public void implicitMapping() {
		log.info("... implicitMapping ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Book b = (Book) em.createNativeQuery("SELECT * FROM book b WHERE id = 1", Book.class).getSingleResult();
		Assert.assertTrue(b instanceof Book);
		Assert.assertEquals(new Long(1), b.getId());
		Assert.assertEquals("Hibernate Tips", b.getTitle());
		
		em.getTransaction().commit();
		em.close();
	}
	
	@Test
	public void explicitMapping() {
		log.info("... explicitMapping ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Book b = (Book) em.createNativeQuery("SELECT id as bookId, version, title, publishingDate, publisherid FROM book b WHERE id = 1", "BookMapping").getSingleResult();
		Assert.assertTrue(b instanceof Book);
		Assert.assertEquals(new Long(1), b.getId());
		Assert.assertEquals("Hibernate Tips", b.getTitle());
		
		em.getTransaction().commit();
		em.close();
	}
}

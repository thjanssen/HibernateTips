package org.thoughts.on.java.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestNativeQuery {

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
	public void adHocNativeQuery() {
		log.info("... adHocNativeQuery ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Query q = em.createNativeQuery("SELECT * FROM book b WHERE id = ?", Book.class);
		q.setParameter(1, 1);
		Book b = (Book) q.getSingleResult();
		Assert.assertTrue(b instanceof Book);
		Assert.assertEquals(new Long(1), ((Book)b).getId());
		
		em.getTransaction().commit();
		em.close();
	}
}

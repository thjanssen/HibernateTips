package org.thoughts.on.java.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestJoinUnassociatedEntities {

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
	public void joinUnassociated() {
		log.info("... joinUnassociated ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Query q = em.createQuery("SELECT b.title, count(r.id) FROM Book b INNER JOIN Review r ON r.fkBook = b.id GROUP BY b.title");
		Object[] r = (Object[]) q.getSingleResult();
		log.info(r[0] + " received " + r[1] + " reviews.");
		
		em.getTransaction().commit();
		em.close();
	}
}

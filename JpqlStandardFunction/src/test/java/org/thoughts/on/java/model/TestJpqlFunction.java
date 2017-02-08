package org.thoughts.on.java.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestJpqlFunction {

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
	public void callSizeFunction() {
		log.info("... callSizeFunction ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Query q = em.createQuery("SELECT a, size(a.books) FROM Author a GROUP BY a.id");
		@SuppressWarnings("unchecked")
		List<Object[]> results = q.getResultList();
		
		for (Object[] r :  results) {
			log.info(r[0] + " wrote " +  r[1] + " books.");
		}

		em.getTransaction().commit();
		em.close();
	}
}

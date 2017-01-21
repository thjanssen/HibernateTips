package org.thoughts.on.java.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestTreat {

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

	@SuppressWarnings("unchecked")
	@Test
	public void testTreat() {
		log.info("... testTreat ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<Object[]> result = em
				.createQuery(
						"SELECT a, p FROM Author a JOIN a.publications p WHERE treat(p AS Book).title LIKE '%Java%'")
				.getResultList();

		for (Object[] o : result) {
			log.info(o[0] + " wrote " + o[1]);
		}

		em.getTransaction().commit();
		em.close();
	}
}

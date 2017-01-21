package org.thoughts.on.java.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestIdentityStrategy {

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
	public void testIdentityStrategy() {
		log.info("... testIdentityStrategy ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = new Author();
		a.setFirstName("Thorben");
		a.setLastName("Janssen");
		
		log.info("Persist new Author entity.");
		em.persist(a);
		
		log.info("Call flush");
		em.flush();
		
		em.getTransaction().commit();
		em.close();
	}
	
	private void logBookPrices(EntityManager em) {
		List<Object[]> books = em.createQuery("SELECT b.title, b.price FROM Book b").getResultList();
		for (Object[] b : books) {
			log.info(b[0] + ": " + b[1]);
		}
	}
}

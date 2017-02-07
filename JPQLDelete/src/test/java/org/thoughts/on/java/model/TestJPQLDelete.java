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

public class TestJPQLDelete {

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
	public void deleteBooks() {
		log.info("... deleteBooks ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		logBooks(em);
		
		Query query = em.createQuery("DELETE Book b");
		query.executeUpdate();

		logBooks(em);
		
		em.getTransaction().commit();
		em.close();
	}
	
	private void logBooks(EntityManager em) {
		@SuppressWarnings("unchecked")
		List<String> titles = em.createQuery("SELECT b.title FROM Book b").getResultList();
		for (String title : titles) {
			log.info(title);
		}
	}
}

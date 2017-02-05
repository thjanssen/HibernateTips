package org.thoughts.on.java.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCriteriaDelete {

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
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaDelete<Book> delete = cb.createCriteriaDelete(Book.class);
		delete.from(Book.class);
		
		Query query = em.createQuery(delete);
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

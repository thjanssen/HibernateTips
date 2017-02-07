package org.thoughts.on.java.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCriteriaUpdate {

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
	public void updateBookPrices() {
		log.info("... updateBookPrices ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		logBookPrices(em);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<Book> update = cb.createCriteriaUpdate(Book.class);
		Root<Book> root = update.from(Book.class);
		update.set(Book_.price, cb.prod(root.get(Book_.price), 1.1));
		
		Query query = em.createQuery(update);
		query.executeUpdate();

		logBookPrices(em);
		
		em.getTransaction().commit();
		em.close();
	}
	
	private void logBookPrices(EntityManager em) {
		@SuppressWarnings("unchecked")
		List<Object[]> books = em.createQuery("SELECT b.title, b.price FROM Book b").getResultList();
		for (Object[] b : books) {
			log.info(b[0] + ": " + b[1]);
		}
	}
}

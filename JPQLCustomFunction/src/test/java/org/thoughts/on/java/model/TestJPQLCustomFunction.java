package org.thoughts.on.java.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestJPQLCustomFunction {

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
	public void callFunction() {
		log.info("... callFunction ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		
		TypedQuery<Book> q = em.createQuery(
			    "SELECT b "
			    + "FROM Book b "
			    + "WHERE :double2 > function('calculate', b.price, :double1)"
			    , Book.class);
		q.setParameter("double1", 10.0D);
		q.setParameter("double2", 40.0D);
		List<Book> books = q.getResultList();
		
		for (Book b : books) {
			log.info(b);
		}

		em.getTransaction().commit();
		em.close();
	}
}

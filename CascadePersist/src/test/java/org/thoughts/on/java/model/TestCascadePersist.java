package org.thoughts.on.java.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCascadePersist {

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
	public void testCascade() {
		log.info("... testCascade ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = new Author();
		a.setFirstName("John");
		a.setLastName("Doe");
		
		Book b1 = new Book();
		b1.setTitle("John's first book");
		a.getBooks().add(b1);
		
		Book b2 = new Book();
		b2.setTitle("John's second book");
		a.getBooks().add(b2);

		em.persist(a);
		
		em.getTransaction().commit();
		em.close();
	}
}

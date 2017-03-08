package org.thoughts.on.java.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestAssociation {

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
	public void associationWithAttributes() {
		log.info("... associationWithAttributes ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Book b = em.find(Book.class, 1L);
		Publisher p = em.find(Publisher.class, 1L);
		
		BookPublisher bp = new BookPublisher();
		bp.setId(new BookPublisherId());
		bp.setBook(b);
		bp.setPublisher(p);
		bp.setFormat(Format.PAPERBACK);
		
		em.persist(bp);
		
		em.getTransaction().commit();
		em.close();
	}
}

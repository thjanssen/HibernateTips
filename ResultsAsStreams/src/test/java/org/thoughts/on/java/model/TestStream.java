package org.thoughts.on.java.model;

import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestStream {

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
	public void resultsAsList() {
		log.info("... resultsAsList ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Session session = em.unwrap(Session.class);
		
		List<Book> books = session.createQuery("SELECT b FROM Book b", Book.class).getResultList();
		books.stream()
		    .map(b -> b.getTitle() + " was published on " + b.getPublishingDate())
		    .forEach(m -> log.info(m));
		
		em.getTransaction().commit();		
		em.close();
	}
	
	@Test
	public void resultsAsStream() {
		log.info("... resultsAsStream ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Session session = em.unwrap(Session.class);
		
		Stream<Book> books = session.createQuery("SELECT b FROM Book b", Book.class).stream();
		books.map(b -> b.getTitle() + " was published on " + b.getPublishingDate())
		    .forEach(m -> log.info(m));
		
		em.getTransaction().commit();		
		em.close();
	}
}

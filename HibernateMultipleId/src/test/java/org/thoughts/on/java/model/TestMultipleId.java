package org.thoughts.on.java.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.hibernate.MultiIdentifierLoadAccess;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMultipleId {

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
	public void multipleId() {
		log.info("... multipleId ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Session session = em.unwrap(Session.class);

		MultiIdentifierLoadAccess<Book> multi = session.byMultipleIds(Book.class);
		List<Book> books = multi.multiLoad(1L, 2L, 3L);
		
		Assert.assertEquals(3, books.size());

		em.getTransaction().commit();
		em.close();
	}
	
	@Test
	public void multipleIdWithBatchSize() {
		log.info("... multipleIdWithBatchSize ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Session session = em.unwrap(Session.class);

		MultiIdentifierLoadAccess<Book> multi = session.byMultipleIds(Book.class);
		List<Book> books = multi.withBatchSize(2).multiLoad(1L, 2L, 3L);
		
		Assert.assertEquals(3, books.size());

		em.getTransaction().commit();
		em.close();
	}
	
	@Test
	public void multipleIdWithSessionCheck() {
		log.info("... multipleIdWithSessionCheck ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Session session = em.unwrap(Session.class);

		@SuppressWarnings("unused")
		Book b = em.find(Book.class, 1L);
		
		MultiIdentifierLoadAccess<Book> multi = session.byMultipleIds(Book.class);
		List<Book> books = multi.enableSessionCheck(true).multiLoad(1L, 2L, 3L);
		
		Assert.assertEquals(3, books.size());

		em.getTransaction().commit();
		em.close();
	}
}

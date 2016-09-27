package org.thoughts.on.java.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.hibernate.LazyInitializationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestJoinFetch {

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

	@Test(expected = LazyInitializationException.class)
	public void selectFromWithoutJoinFetch() {
		log.info("... selectFromWithoutJoinFetch ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = em.createQuery("SELECT a FROM Author a WHERE id = 1", Author.class).getSingleResult();
		
		em.getTransaction().commit();
		em.close();
		
		log.info(a.getFirstName()+" "+a.getLastName()+" wrote "+a.getBooks().size()+" books.");
	}
	
	@Test
	public void selectFromWithJoinFetch() {
		log.info("... selectFromWithJoinFetch ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = em.createQuery("SELECT a FROM Author a JOIN FETCH a.books WHERE a.id = 1", Author.class).getSingleResult();
		
		em.getTransaction().commit();
		em.close();
		
		log.info(a.getFirstName()+" "+a.getLastName()+" wrote "+a.getBooks().size()+" books.");
	}
	
	
	@Test
	public void create() {
		log.info("... create ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = new Author();
		a.setFirstName("my first name");
		a.setLastName("my last name");
		em.persist(a);
		
		em.getTransaction().commit();
		em.close();
	}
	
	@Test
	public void findAndupdate() {
		log.info("... findAndupdate ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = em.find(Author.class, 1L);
		a.setLastName("new last name");
		
		em.getTransaction().commit();
		em.close();
	}
	
	@Test
	public void findAnd2Updates() {
		log.info("... findAnd2Updates ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = em.find(Author.class, 1L);
		updateFirstName(a);
		
		// do something useful
		
		updateLastName(a);
		
		em.getTransaction().commit();
		em.close();
	}
	
	private void updateFirstName(Author a) {
		// do something useful
		
		a.setFirstName("New first name");
	}
	
	private void updateLastName(Author a) {
		// do something useful
		
		a.setLastName("new last name");
	}
	
	@Test
	public void select() {
		log.info("... select ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		TypedQuery<Author> q = em.createQuery("SELECT a FROM Author a WHERE id = :id", Author.class);
		q.setParameter("id", 1L);
		Author a = q.getSingleResult();
		
		em.getTransaction().commit();
		em.close();
	}
}

package org.thoughts.on.java.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestTransient {

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
	public void emFindAge() {
		log.info("... emFindAge ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = em.find(Author.class, 1L);
		Assert.assertEquals(43, a.getAge());
		log.info(a.getFirstName() + " " + a.getLastName() + " is " + a.getAge() + " years old.");
		
		em.getTransaction().commit();
		em.close();
	}
	
	@Test
	public void emFindCalculatedAge() {
		log.info("... emFindCalculatedAge ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = em.find(Author.class, 1L);
		Assert.assertEquals(43, a.getCalculatedAge());
		log.info(a.getFirstName() + " " + a.getLastName() + " is " + a.getCalculatedAge() + " years old.");
		
		em.getTransaction().commit();
		em.close();
	}
	
	@Test
	public void query() {
		log.info("... query ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = em.createQuery("SELECT a FROM Author a WHERE a.id = 1", Author.class).getSingleResult();
		Assert.assertEquals(43, a.getAge());
		log.info(a.getFirstName() + " " + a.getLastName() + " is " + a.getAge() + " years old.");
		
		em.getTransaction().commit();
		em.close();
	}
}

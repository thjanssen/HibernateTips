package org.thoughts.on.java.model;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestUUIDPrimaryKey {

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
	public void testUUIDPrimaryKeyV4() {
		log.info("... testUUIDPrimaryKeyV4 ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = new Author();
		a.setFirstName("Thorben");
		a.setLastName("Janssen");
		
		log.info("Persist new Author entity.");
		em.persist(a);
		
		log.info("Call flush");
		em.flush();
		
		em.getTransaction().commit();
		em.close();
		
		em = emf.createEntityManager();
		em.getTransaction().begin();

		UUID uuid = a.getId();
		
		a = em.find(Author.class, uuid);
		Assert.assertEquals(uuid, a.getId());
		
		em.getTransaction().commit();
		em.close();
	}
	
	@Test
	public void testUUIDPrimaryKeyV1() {
		log.info("... testUUIDPrimaryKeyV1 ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Book b = new Book();
		b.setTitle("Hibernate Tips");
		
		log.info("Persist new Book entity.");
		em.persist(b);
		
		log.info("Call flush");
		em.flush();
		
		em.getTransaction().commit();
		em.close();
		
		em = emf.createEntityManager();
		em.getTransaction().begin();

		UUID uuid = b.getId();
		
		b = em.find(Book.class, uuid);
		Assert.assertEquals(uuid, b.getId());
		
		em.getTransaction().commit();
		em.close();
	}
}

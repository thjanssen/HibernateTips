package org.thoughts.on.java.model;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestGeneratedColumn {

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
	public void createAuthor() {
		log.info("... createAuthor ...");

		EntityManager em = emf.createEntityManager();
		
		// Transaction 1
		em.getTransaction().begin();

		Author a = new Author();
		a.setFirstName("Thorben");
		a.setLastName("Janssen");
		em.persist(a);
		
		em.getTransaction().commit();
		
		log.info(a);
		LocalDateTime creationTime = a.getLastUpdate();
		Assert.assertNotNull(creationTime);
		
		// Transaction 2
		em.getTransaction().begin();
		
		a = em.find(Author.class, a.getId());
		a.setFirstName("Changed Firstname");
		
		em.getTransaction().commit();
		
		log.info(a);
		LocalDateTime updateTime = a.getLastUpdate();
		Assert.assertNotNull(updateTime);
		Assert.assertNotEquals(updateTime, creationTime);
		
		
		em.close();
	}
}

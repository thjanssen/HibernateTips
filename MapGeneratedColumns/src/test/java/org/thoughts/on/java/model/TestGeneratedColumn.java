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
		em.getTransaction().begin();

		Author a = new Author();
		a.setFirstName("Firstname");
		a.setLastName("Lastname");
		em.persist(a);
		em.flush();
		a.setFirstName("Changed Firstname");
		em.flush();
		Assert.assertNotNull(a.getLastUpdate());

		em.getTransaction().commit();
		em.close();
	}
}

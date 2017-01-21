package org.thoughts.on.java.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestStatistics {

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
	public void logStatistics() {
		log.info("... logStatistics ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<Author> authors = em.createQuery("SELECT a FROM Author a", Author.class).getResultList();

		for (Author a : authors) {
			log.info(a.getFirstName() + " " + a.getLastName() + " wrote " + a.getBooks().size());
		}
		
		em.getTransaction().commit();
		em.close();
	}
	
	@Test
	public void statisticsAPI() {
		log.info("... statisticsAPI ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<Author> authors = em.createQuery("SELECT a FROM Author a", Author.class).getResultList();

		for (Author a : authors) {
			log.info(a.getFirstName() + " " + a.getLastName() + " wrote " + a.getBooks().size());
		}
		
		SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
		Statistics stats = sessionFactory.getStatistics();
		long queryCount = stats.getQueryExecutionCount();
		long collectionFetchCount = stats.getCollectionFetchCount();
		log.info("QueryCount: "+queryCount);
		log.info("CollectionFetchCount: "+collectionFetchCount);
		
		em.getTransaction().commit();
		em.close();
	}
}

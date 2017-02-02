package org.thoughts.on.java.model;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestEntityGraph {

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

	@SuppressWarnings("unchecked")
	@Test
	public void selectWithEntityGraph() {
		log.info("... selectWithEntityGraph ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		EntityGraph<Author> graph = em.createEntityGraph(Author.class);
		graph.addAttributeNodes(Author_.books);
		
		TypedQuery<Author> q = em.createQuery("SELECT a FROM Author a WHERE a.id = 1", Author.class);
		q.setHint("javax.persistence.fetchgraph", graph);
		Author a = q.getSingleResult();
		
		em.getTransaction().commit();
		em.close();
		
		log.info(a.getFirstName()+" "+a.getLastName()+" wrote "+a.getBooks().size()+" books.");
	}
	
	@Test
	public void selectWithNamedEntityGraph() {
		log.info("... selectWithNamedEntityGraph ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		EntityGraph<?> graph = em.createEntityGraph("graph.AuthorBooks");
		TypedQuery<Author> q = em.createQuery("SELECT a FROM Author a WHERE a.id = 1", Author.class);
		q.setHint("javax.persistence.fetchgraph", graph);
		Author a = q.getSingleResult();
		
		em.getTransaction().commit();
		em.close();
		
		log.info(a.getFirstName()+" "+a.getLastName()+" wrote "+a.getBooks().size()+" books.");
	}
}

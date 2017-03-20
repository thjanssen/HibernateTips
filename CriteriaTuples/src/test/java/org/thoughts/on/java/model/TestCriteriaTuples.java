package org.thoughts.on.java.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCriteriaTuples {

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
	public void selectTuples() {
		log.info("... selectTuples ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> q = cb.createTupleQuery();
		Root<Author> author = q.from(Author.class);
		q.multiselect(author.get(Author_.firstName).alias("firstName"), 
						author.get(Author_.lastName).alias("lastName"));

		TypedQuery<Tuple> query = em.createQuery(q);
		List<Tuple> authorNames = query.getResultList();

		for (Tuple authorName : authorNames) {
			log.info(authorName.get("firstName") + " "
					+ authorName.get("lastName"));
		}

		em.getTransaction().commit();
		em.close();
	}
}

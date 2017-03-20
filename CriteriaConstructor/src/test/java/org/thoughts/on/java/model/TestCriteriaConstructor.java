package org.thoughts.on.java.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCriteriaConstructor {

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
	public void selectPojo() {
		log.info("... selectPojo ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<AuthorValue> q = cb.createQuery(AuthorValue.class);
		Root<Author> root = q.from(Author.class);
		q.select(cb.construct(AuthorValue.class, root.get(Author_.firstName), root.get(Author_.lastName)));

		TypedQuery<AuthorValue> query = em.createQuery(q);
		List<AuthorValue> authors = query.getResultList();

		for (AuthorValue author : authors) {
			log.info(author.getFirstName() + " "
					+ author.getLastName());
		}

		em.getTransaction().commit();
		em.close();
	}
}

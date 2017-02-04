package org.thoughts.on.java.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
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
	public void callFunction() {
		log.info("... callFunction ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Book> cq = cb.createQuery(Book.class);
		Root<Book> root = cq.from(Book.class);
		
		ParameterExpression<Double> doubleParam1 = cb.parameter(Double.class);
		ParameterExpression<Double> doubleParam2 = cb.parameter(Double.class);
		cq.where(cb.greaterThan(doubleParam2, cb.function("calculate", Double.class, root.get(Book_.price), doubleParam1)));

		TypedQuery<Book> q = em.createQuery(cq);
		q.setParameter(doubleParam1, 10.0D);
		q.setParameter(doubleParam2, 40.0D);
		List<Book> books = q.getResultList();
		
		for (Book b : books) {
			log.info(b);
		}

		em.getTransaction().commit();
		em.close();
	}
}

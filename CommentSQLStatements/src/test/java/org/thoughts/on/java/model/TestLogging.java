package org.thoughts.on.java.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLogging {

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
	public void selectAuthorsJPQL() {
		log.info("... selectAuthorsJPQL ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		TypedQuery<Author> q = em.createQuery("SELECT a FROM Author a WHERE a.id = :id", Author.class);
		q.setParameter("id", 1L);
		q.setHint("org.hibernate.comment", "This is my comment");
		q.getSingleResult();

		em.getTransaction().commit();
		em.close();
	}
	
	@Test
	public void selectAuthorsNative() {
		log.info("... selectAuthorsNative ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Query q = em.createNativeQuery("SELECT * FROM Author a WHERE a.id = :id", Author.class);
		q.setParameter("id", 1L);
		q.setHint("org.hibernate.comment", "This is my comment");
		q.getSingleResult();

		em.getTransaction().commit();
		em.close();
	}
	
	@Test
	public void selectAuthorsCriteria() {
		log.info("... selectAuthorsCriteria ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Author> cq = cb.createQuery(Author.class);
		Root<Author> root = cq.from(Author.class);
		cq.select(root);
		ParameterExpression<Long> idParam = cb.parameter(Long.class, "id");
		cq.where(cb.equal(root.get("id"), idParam));
		TypedQuery<Author> q = em.createQuery(cq);
		q.setParameter("id", 1L);
		q.setHint("org.hibernate.comment", "This is my comment");
		q.getSingleResult();

		em.getTransaction().commit();
		em.close();
	}
}

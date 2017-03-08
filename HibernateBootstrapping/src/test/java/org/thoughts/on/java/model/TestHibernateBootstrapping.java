package org.thoughts.on.java.model;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestHibernateBootstrapping {

	Logger log = Logger.getLogger(this.getClass().getName());

	private SessionFactory sessionFactory;
	private Session session;

	@Before
	public void init() {
		ServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
				.configure().build();
		sessionFactory = new MetadataSources(standardRegistry)
				.addAnnotatedClass(Author.class).buildMetadata()
				.buildSessionFactory();
		session = sessionFactory.openSession();
	}

	@After
	public void close() {
		session.close();
	}

	@Test
	public void accessHibernateSession() {
		log.info("... accessHibernateSession ...");

		session.beginTransaction();

		Author a = new Author();
		a.setFirstName("Thorben");
		a.setLastName("Janssen");
		session.persist(a);

		session.getTransaction().commit();
	}
}

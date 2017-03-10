package org.thoughts.on.java;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.thoughts.on.java.model.Author;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootstrappingTest {

	Logger log = Logger.getLogger(this.getClass().getName());

	@Autowired
	private EntityManager em;
	
	@Test
	@Transactional
	@Commit
	public void accessHibernateSession() {
		log.info("... accessHibernateSession ...");
		
		Author a = new Author();
		a.setFirstName("Thorben");
		a.setLastName("Janssen");
		em.persist(a);
	}
}

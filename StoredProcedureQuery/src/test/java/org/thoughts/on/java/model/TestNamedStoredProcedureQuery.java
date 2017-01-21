package org.thoughts.on.java.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestNamedStoredProcedureQuery {

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
	public void calculate() {

		EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("calculate");
		query.setParameter("x", 1.23d);
		query.setParameter("y", 4d);
		query.execute();
		Double sum = (Double) query.getOutputParameterValue("sum");
		log.info("Calculation result: 1.23 + 4 = " + sum);

        em.getTransaction().commit();
        em.close();
	}
}

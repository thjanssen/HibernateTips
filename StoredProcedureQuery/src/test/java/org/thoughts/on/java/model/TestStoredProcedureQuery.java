package org.thoughts.on.java.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestStoredProcedureQuery {

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
		log.info("... calculate ...");
		EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
		// define the stored procedure
		StoredProcedureQuery query = em.createStoredProcedureQuery("calculate");
		query.registerStoredProcedureParameter("x", Double.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("y", Double.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("sum", Double.class, ParameterMode.OUT);
		
		// set input parameter
		query.setParameter("x", 1.23d);
		query.setParameter("y", 4d);
		
		// call the stored procedure and get the result
		query.execute();
		Double sum = (Double) query.getOutputParameterValue("sum");
		log.info("Calculation result: 1.23 + 4 = " + sum);

        em.getTransaction().commit();
        em.close();
	}
}

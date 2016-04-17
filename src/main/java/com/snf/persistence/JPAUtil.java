package com.snf.persistence;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

@ApplicationScoped
public class JPAUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	static final Logger log = Logger.getLogger(JPAUtil.class);

	private static EntityManagerFactory factory = null;
	
	@Produces
	@RequestScoped
	public EntityManager createEntityManager() {
		if(factory==null){
			factory = Persistence.createEntityManagerFactory("salaoBeleza");
		}
		return factory.createEntityManager();
	}

	public void closeEntityManager(@Disposes EntityManager manager) {
		manager.close();
	}

}

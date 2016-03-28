package com.snf.persistence;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import com.snf.controller.CommonsController;

@SessionScoped
public class JPAUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	static final Logger log = Logger.getLogger(JPAUtil.class);

	private EntityManagerFactory factory;
	
	@Inject
	private CommonsController commons;

	public JPAUtil() {
		factory = Persistence.createEntityManagerFactory("salaoBeleza");
	}

	@Produces
	@RequestScoped
	public EntityManager createEntityManager() {
		try{
			commons.getUsuario();
			Map<String, String> properties = new HashMap<>();
			properties.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost/salaoBeleza");
			factory = Persistence.createEntityManagerFactory("salaoBeleza", properties);
		}catch(Exception e){
			return factory.createEntityManager();
		}
		return factory.createEntityManager();
	}

	public void closeEntityManager(@Disposes EntityManager manager) {
		manager.close();
	}

}

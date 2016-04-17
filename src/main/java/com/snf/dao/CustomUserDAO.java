package com.snf.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;

import com.snf.builder.JPQLBuilder;
import com.snf.model.Usuario;
import com.snf.persistence.JPAUtil;

public class CustomUserDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	static final Logger log = Logger.getLogger(CustomUserDAO.class);
	
	private static EntityManager springManager;
	
	public CustomUserDAO() {
		if(springManager==null) {
			springManager = new JPAUtil().createEntityManager();
		}
	}
	
	public Usuario getUsuarioByLogin(String login){
		try {
			return new JPQLBuilder()
					.select("u")
					.from(Usuario.class, "u")
					.innerJoinFetch("u.roles", "r")
					.where("u.login = :log", login)
					.contruir(springManager, Usuario.class)
					.getSingleResult();
			
		} catch (NoResultException e) {
			log.error(e.toString());
			throw e;
		} catch (PersistenceException persistenceException) {
			log.error(persistenceException.toString());
			throw persistenceException;
		}
	}

	public Usuario atualizarUsuario(Usuario usuario) {
    	try{
    		springManager.getTransaction().begin();
            usuario = springManager.merge(usuario);
            springManager.getTransaction().commit();
    	}catch(Exception e){
    		springManager.getTransaction().rollback();
    		log.error(e.toString());
    		throw e;    		
    	}
    	return usuario;
    	
    }

}

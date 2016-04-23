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
	
	public Usuario getUsuarioByLogin(String login){
		try {
			EntityManager em = JPAUtil.createEntityManager();
			return new JPQLBuilder()
					.select("u")
					.from(Usuario.class, "u")
					.innerJoinFetch("u.roles", "r")
					.where("u.login = :log", login)
					.contruir(em, Usuario.class)
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
		EntityManager em = JPAUtil.createEntityManager();
    	try{
    		em.getTransaction().begin();
            usuario = em.merge(usuario);
            em.getTransaction().commit();
    	}catch(Exception e){
    		em.getTransaction().rollback();
    		log.error(e.toString());
    		throw e;    		
    	}
    	return usuario;
    	
    }

}

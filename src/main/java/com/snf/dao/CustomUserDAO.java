package com.snf.dao;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.snf.builder.JPQLBuilder;
import com.snf.model.Usuario;
import com.snf.persistence.JPAUtil;

public class CustomUserDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	static final Logger log = Logger.getLogger(CustomUserDAO.class);
	
	private static EntityManager springManager;
	
	public CustomUserDAO() {
		if(springManager==null)
			springManager = new JPAUtil().createEntityManager();
	}
	
	public Usuario getUsuarioByLogin(String login){
		try {
			sincronizarBanco();
			
			JPQLBuilder stringBuilder = new JPQLBuilder()
					.select("u")
					.from(Usuario.class, "u")
					.innerJoinFetch("u.roles", "r")
					.innerJoinFetch("r.tipoUsuario", "tp")
					.where("u.login = :log", login);
			Query q = springManager.createQuery(stringBuilder.contruir()).setHint("org.hibernate.cacheMode", "IGNORE");
			colocarParametros(q, stringBuilder);
			return (Usuario) q.getSingleResult();
		} catch (NoResultException e) {
			log.error(e.toString());
			throw e;
		} catch (PersistenceException persistenceException) {
			log.error(persistenceException.toString());
			throw persistenceException;
		}
	}

	private void sincronizarBanco() {
		Query querySync = springManager.createQuery("SELECT u FROM Usuario u");
		Usuario user = (Usuario) querySync.getResultList().get(0);
		atualizarUsuario(user);
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
	
	public void colocarParametros(Query query, JPQLBuilder builder) {
    	for (Map.Entry<String, Object> parametros : builder.getParametros().entrySet()) {
			query.setParameter(parametros.getKey(), parametros.getValue());
		}
    }

}

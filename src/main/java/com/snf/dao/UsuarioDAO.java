package com.snf.dao;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import com.snf.builder.JPQLBuilder;
import org.apache.log4j.Logger;

import com.snf.genericDao.GenericDAO;
import com.snf.model.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario, Long> {

	private static final long serialVersionUID = 5201746283603277625L;
	
	private static final Logger log = Logger.getLogger(UsuarioDAO.class);
	
	public Usuario getUsuarioByLogin(String login){
		try {
			return new JPQLBuilder()
					.select("u")
					.from(Usuario.class, "u")
					.innerJoinFetch("u.roles", "r")
					.where("u.login = :log", login)
					.contruir(getManager(), Usuario.class)
					.getSingleResult();
			
		} catch (NoResultException e) {
			log.error(e.toString());
			throw e;
		} catch (PersistenceException persistenceException) {
			log.error(persistenceException.toString());
			throw persistenceException;
		}
	}
	

}

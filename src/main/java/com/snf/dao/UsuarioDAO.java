package com.snf.dao;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.snf.builder.JPQLBuilder;
import com.snf.model.Usuario;

@Repository
public class UsuarioDAO extends GenericDAO<Usuario, Long> {

	private static final long serialVersionUID = 5201746283603277625L;
	
	public Usuario getUsuarioByLoginSenha(String login,String senha){
		try {
			Query q = getManager().createQuery("SELECT u FROM Usuario u WHERE u.login=:log AND u.senha=:sen");
			q.setParameter("log", login);
			q.setParameter("sen", senha);
			return (Usuario) q.getSingleResult();
		} catch (Exception e) {
			throw e;
		}
	}
	public Usuario getUsuarioByLogin(String login){
		try {
			JPQLBuilder stringBuilder = new JPQLBuilder()
					.select("u")
					.from(Usuario.class, "u")
					.innerJoinFetch("u.roles", "r")
					.innerJoinFetch("r.tipoUsuario", "tp")
					.where("u.login = :log", login);
			
			Query q = getManager().createQuery(stringBuilder.contruir());
			colocarParametros(q, stringBuilder);
			
			return (Usuario) q.getSingleResult();
		} catch (NoResultException e) {
			throw e;
		} catch (PersistenceException persistenceException) {
			throw persistenceException;
		}
	}

}

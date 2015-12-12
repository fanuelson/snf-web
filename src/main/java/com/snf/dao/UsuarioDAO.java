package com.snf.dao;

import javax.persistence.Query;

import com.snf.model.Usuario;

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

}

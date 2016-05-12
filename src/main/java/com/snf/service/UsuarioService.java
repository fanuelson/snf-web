package com.snf.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.snf.dao.UsuarioDAO;
import com.snf.model.Usuario;

@Stateless
public class UsuarioService implements Serializable {

	private static final long serialVersionUID = -6071998648651080305L;

	static final Logger log = Logger.getLogger(UsuarioService.class);

	private UsuarioDAO usuarioDAO;

	public void salvar(Usuario usuario) {
		usuarioDAO.save(usuario);
	}

	public Usuario getByNumero(Long id) {
		return usuarioDAO.getById(id);
	}

	public List<Usuario> getAll() {
		return usuarioDAO.getAll();
	}

	public void remover(Usuario usuario) {
		usuarioDAO.delete(usuario.getIdUsuario());
	}
}

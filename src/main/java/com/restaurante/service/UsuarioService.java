package com.restaurante.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.restaurante.dao.UsuarioDAO;
import com.restaurante.model.Usuario;

public class UsuarioService implements Serializable {

	private static final long serialVersionUID = -6071998648651080305L;
	
	@Inject
	UsuarioDAO usuarioDAO;
	
	public void salvar(Usuario usuario){
		usuarioDAO.save(usuario);
	}
	
	public Usuario getByNumero(Long id){
		return usuarioDAO.getById(id);
	}
	
	public List<Usuario> getAll(){
		return usuarioDAO.getAll();
	}
	
	public void remover(Usuario usuario){
		usuarioDAO.delete(usuario.getIdUsuario());
	}
	
	public Usuario getUsuarioByLoginSenha(String login,String senha){
		return usuarioDAO.getUsuarioByLoginSenha(login, senha);
	}
}

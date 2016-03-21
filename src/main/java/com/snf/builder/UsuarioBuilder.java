package com.snf.builder;

import com.snf.enums.TipoUsuario;
import com.snf.model.Usuario;

public class UsuarioBuilder {

	private Usuario usuario;
	
	public UsuarioBuilder() {
		usuario = new Usuario();
	}
	
	public UsuarioBuilder comNome(String nome) {
		usuario.setNome(nome);
		return this;
	}
	
	public UsuarioBuilder comLogin(String login) {
		usuario.setLogin(login);
		return this;
	}
	public UsuarioBuilder comSenha(String senha) {
		usuario.setSenha(senha);
		return this;
	}
	public UsuarioBuilder doTipo(TipoUsuario tipo) {
		usuario.setTipo(tipo);
		return this;
	}
	
	public Usuario contruir() {
		return this.usuario;
	}
}

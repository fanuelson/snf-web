package com.snf.builder;

import com.snf.model.Usuario;

public class UsuarioBuilder {

	private Usuario usuario;
	
	public UsuarioBuilder(Usuario usuario) {
		this.usuario = usuario;
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
	
	public UsuarioBuilder comNumeroTentativas(Integer numero) {
		usuario.setTentativas(numero);
		return this;
	}
	
	public Usuario contruir() {
		return this.usuario;
	}
}

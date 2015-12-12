package com.snf.VM;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import com.snf.model.Usuario;

@Named
public class LoginVM implements Serializable {

	private static final long serialVersionUID = -1929860329397639880L;

	@Inject
	Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}

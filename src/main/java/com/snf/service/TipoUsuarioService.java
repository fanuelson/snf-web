package com.snf.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.snf.dao.TipoUsuarioDAO;
import com.snf.model.TipoUsuario;

@Stateless
public class TipoUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TipoUsuarioDAO tipoUsuarioDAO;
	
	public List<TipoUsuario> getAll() {
		return tipoUsuarioDAO.getAll();
	}
	
	public TipoUsuario getById(Long id) {
		return tipoUsuarioDAO.getById(id);
	}
}

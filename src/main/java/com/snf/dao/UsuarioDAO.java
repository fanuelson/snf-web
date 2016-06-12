package com.snf.dao;

import org.apache.log4j.Logger;

import com.snf.genericDao.GenericDAO;
import com.snf.model.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario, Long> {

	private static final long serialVersionUID = 5201746283603277625L;
	
	private static final Logger log = Logger.getLogger(UsuarioDAO.class);

}

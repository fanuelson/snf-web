package com.snf.service;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.snf.dao.CustomUserDAO;
import com.snf.model.Usuario;

@Service
public class CustomUserService implements UserDetailsService, Serializable {

	private static final long serialVersionUID = 1L;

	static final Logger log = Logger.getLogger(CustomUserService.class);

	@Autowired
	private CustomUserDAO usuarioDAO;

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		return usuarioDAO.getUsuarioByLogin(arg0);
	}

	public void atualizar(Usuario usuario) {
		usuarioDAO.atualizarUsuario(usuario);
	}

}

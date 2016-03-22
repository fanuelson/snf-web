package com.snf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.snf.dao.UsuarioDAO;


@Service
public class CustomUserService implements UserDetailsService {

	@Autowired
	UsuarioDAO usuarioDAO;
	
	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		return usuarioDAO.getUsuarioByLogin(arg0);
	}

}

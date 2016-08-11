package com.snf.security;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJBException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.snf.library.Encriptador;
import com.snf.model.Usuario;
import com.snf.service.UsuarioService;
import com.snf.util.MessagesUtils;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	static final Logger log = Logger.getLogger(CustomAuthenticationProvider.class);
	
	@Autowired
	private Encriptador enc;
	
	public CustomAuthenticationProvider() {
		super();
	}
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
		String password = (String) auth.getCredentials();
		
		try{
			UsuarioService us = (UsuarioService) new InitialContext().lookup("java:global/snf-web/UsuarioService");
			Usuario usuario = (Usuario) us.getUsuario(username);
			
			if(enc.checkPassword(password, usuario.getSenha())){
				Collection<GrantedAuthority> grantedAuths = new ArrayList<>();
				grantedAuths.addAll(usuario.getAuthorities());
				auth = getAuth(usuario);
			}else{
				throw new BadCredentialsException(MessagesUtils.getMessage("mensagem.erro.login.dados.invalidos"));
			}
			
		} catch (EJBException persistenceException){
  			log.error(persistenceException.toString());
  			throw new BadCredentialsException(MessagesUtils.getMessage("mensagem.nenhum.registro.encontrado")); 
		} catch (NamingException e) {
			throw new BadCredentialsException(MessagesUtils.getMessage("mensagem.erro.conexao.login"));
		}
		
		return auth ;
	}
	
	private Authentication getAuth(Usuario usuario) {
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getSenha(), usuario.getRoles());
		auth.setDetails(usuario);
		return auth;
	}

	@Override
	public boolean supports(Class<?> arg0) {
	        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(arg0)
	                && arg0.equals(UsernamePasswordAuthenticationToken.class);
	}

}

package com.snf.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import com.snf.model.Role;
import com.snf.model.Usuario;
import com.snf.rest.RestClient;
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
			Usuario usuario = RestClient.httpGetJson("/usuarios/"+username, Usuario.class);
			
			if(enc.checkPassword(password, usuario.getSenha())){
				Collection<GrantedAuthority> grantedAuths = new ArrayList<>();
				List<Role> roles = RestClient.httpGetJsonCollection("/usuarios/"+username+"/roles", Role.class);
				usuario.setRoles(roles);
				grantedAuths.addAll(usuario.getAuthorities());
				auth = getAuth(usuario);
			}else{
				log.error(new BadCredentialsException("SENHA ERRADA").toString());
				throw new BadCredentialsException(MessagesUtils.getMessage("mensagem.erro.login"));
			}
			
		} catch (RuntimeException e) {
			log.error(e);
			throw new BadCredentialsException(e.getMessage());
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

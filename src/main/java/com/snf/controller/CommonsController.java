package com.snf.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.snf.enums.TipoUsuario;
import com.snf.model.Usuario;

@Named
@Component
@RequestScoped
public class CommonsController implements Serializable {

	private static final long serialVersionUID = 2452147366225351811L;
	
	final static Logger log = Logger.getLogger(CommonsController.class);

	private static final String PATH_MENU_PUBLICO = "";
	private static final String PATH_MENU_GERENTE = "/pages/menus/menu-gerente.xhtml";
	private static final String PATH_MENU_CAIXA = "/pages/menus/menu-caixa.xhtml";

	private static final String PATH_PAGINA_INICIAL_CAIXA = "/pages/home/inicio.xhtml";
	private static final String PATH_PAGINA_INICIAL_GERENTE = "/pages/home/inicioGerente.xhtml";
	private static final String PATH_PAGINA_LOGIN = "/login.xhtml";

	public Usuario getUsuario() {
		try {
			return (Usuario) SecurityContextHolder.getContext().getAuthentication().getDetails();
		} catch (Exception e) {
			log.error(e.toString());
			return null;
		}
	}

	public String getMenu() {
		switch (getUsuario() != null ? getUsuario().getTipo() : TipoUsuario.INEXISTENTE) {
		case CAIXA:
			return PATH_MENU_CAIXA;
		case GERENTE:
			return PATH_MENU_GERENTE;
		default:
			return PATH_MENU_PUBLICO;
		}
	}

	public String getPaginaInicial() {
		switch (getUsuario() != null ? getUsuario().getTipo() : TipoUsuario.INEXISTENTE) {
		case CAIXA:
			return PATH_PAGINA_INICIAL_CAIXA;
		case GERENTE:
			return PATH_PAGINA_INICIAL_GERENTE;
		default:
			return PATH_PAGINA_LOGIN;
		}
	}

	public boolean loginEfetuado() {
		return getUsuario() != null ? true : false;
	}

}

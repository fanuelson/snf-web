package com.snf.controller;

import java.io.Serializable;

import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;

import com.snf.model.Usuario;

@Named
public class CommonsController implements Serializable {

	private static final long serialVersionUID = 2452147366225351811L;

	static final Logger log = Logger.getLogger(CommonsController.class);

	private static final String PATH_MENU = "/pages/menus/menu.xhtml";

	private static final String PATH_PAGINA_INICIAL = "/pages/home/agenda.xhtml";

	public Usuario getUsuarioLogado() {
		try {
			return (Usuario) SecurityContextHolder.getContext().getAuthentication().getDetails();
		} catch (Exception e) {
			log.error(e.toString());
			return null;
		}
	}

	public String getMenu() {
		return PATH_MENU;
	}

	public String getPaginaInicial() {
		return PATH_PAGINA_INICIAL;
	}

	public boolean loginEfetuado() {
		return getUsuarioLogado() != null ? true : false;
	}

}

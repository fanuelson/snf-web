package com.snf.controller;

import java.io.Serializable;

import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.snf.enums.TipoUsuario;
import com.snf.model.Usuario;

@Named
@Component
public class CommonsController implements Serializable {

	private static final long serialVersionUID = 2452147366225351811L;

	static final Logger log = Logger.getLogger(CommonsController.class);

	private static final String PATH_MENU = "/pages/menus/menu.xhtml";

	private static final String PATH_PAGINA_INICIAL_CAIXA = "/pages/home/agenda.xhtml";
	private static final String PATH_PAGINA_INICIAL_GERENTE = "/pages/home/agenda.xhtml";
	private static final String PATH_PAGINA_LOGIN = "/login.xhtml";

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
		switch (getUsuarioLogado() != null ? getUsuarioLogado().getTipo() : TipoUsuario.INEXISTENTE) {
		case CAIXA:
			return PATH_PAGINA_INICIAL_CAIXA;
		case FUNCIONARIO:
			return PATH_PAGINA_INICIAL_CAIXA;
		case GERENTE:
			return PATH_PAGINA_INICIAL_GERENTE;
		default:
			return PATH_PAGINA_LOGIN;
		}
	}

	public boolean loginEfetuado() {
		return getUsuarioLogado() != null ? true : false;
	}

}

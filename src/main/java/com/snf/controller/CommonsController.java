package com.snf.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.snf.enums.TipoUsuario;
import com.snf.model.Usuario;
import com.snf.util.FacesUtils;

@Named
@RequestScoped
public class CommonsController implements Serializable {
	
	private static final String PATH_PAGINA_INICIAL_CAIXA = "pages/home/inicio.xhtml";
	private static final String PATH_PAGINA_INICIAL_GERENTE = "pages/home/inicioGerente.xhtml";
	
	private static final long serialVersionUID = 2452147366225351811L;

	public Usuario getUsuario() {
		return (Usuario) FacesUtils.getValueSessionMap("usuarioLogado");
	}
	
	public void setUsuarioLogado(Usuario user){
		FacesUtils.addSessionMap("usuarioLogado", user);
	}

	public void redirectHome() throws IOException{
		switch(getUsuario()!=null ? getUsuario().getTipo() : TipoUsuario.INEXISTENTE){
		case CAIXA:
			FacesUtils.redirect(PATH_PAGINA_INICIAL_CAIXA);
			break;
		case GERENTE:
			FacesUtils.redirect(PATH_PAGINA_INICIAL_GERENTE);
			break;
		default:
			FacesUtils.redirect("login.xhtml");
		}
		
		return;
	}
	
	public String getMenu(){
		switch(getUsuario()!=null ? getUsuario().getTipo() : TipoUsuario.INEXISTENTE){
			case CAIXA:
				return "/pages/menus/menu-caixa.xhtml";
			case GERENTE:
				return "/pages/menus/menu-gerente.xhtml";
			default:
				return "/pages/menus/menu-publico.xhtml";
		}
	}
	
	public String getPaginaInicial(){
		if(getUsuario()!=null){
			switch(getUsuario()!=null ? getUsuario().getTipo() : TipoUsuario.INEXISTENTE){
			case CAIXA:
				return "/"+PATH_PAGINA_INICIAL_CAIXA;
			case GERENTE:
				return "/"+PATH_PAGINA_INICIAL_GERENTE;
			default:
				return "../../login.xhtml";
			}
		}
		else
			return "/login.xhtml";
	}
	
	public boolean loginEfetuado(){
		return getUsuario() != null ? true : false;
	}
	
	public void sair() throws IOException{
		FacesUtils.removeSessionMap("usuarioLogado");
		FacesUtils.invalidateSession();
		redirectHome();
	}
	
}

package com.snf.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.snf.enums.TipoUsuario;

@Named
@SessionScoped
public class PermissaoController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CommonsController commonsController;
	
	public void permissao() throws IOException{
		if(!commonsController.loginEfetuado())
			commonsController.redirectHome();
	}
	
	public void permissaoCaixa() throws IOException{
		if(commonsController.getUsuario()==null || !commonsController.getUsuario().getTipo().equals(TipoUsuario.CAIXA))
			redirecionarPaginaLogin();
		return;
	}
	
	public void permissaoGerente() throws IOException{
		if(commonsController.getUsuario()==null || !commonsController.getUsuario().getTipo().equals(TipoUsuario.GERENTE))
			redirecionarPaginaLogin();
		return;
	}
	
	public void permissaoCaixaEGerente() throws IOException{
		if(commonsController.getUsuario()==null)
			redirecionarPaginaLogin();
		return;
	}
	
	private void redirecionarPaginaLogin() throws IOException{
		commonsController.redirectHome();
		return;
	}
}
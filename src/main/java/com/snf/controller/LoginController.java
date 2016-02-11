package com.snf.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import com.snf.VM.LoginVM;
import com.snf.library.Encripta;
import com.snf.model.Usuario;
import com.snf.service.UsuarioService;
import com.snf.util.FacesUtils;
import com.snf.util.MessagesUtils;

@Named
@ViewScoped
public class LoginController implements Serializable {
	
	private static final long serialVersionUID = -5093610007145542638L;

	@Inject
	private LoginVM loginVM;
	
	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	private CommonsController commonsController;
	
	@Inject
	private Encripta encripta;
	
	public void logar() throws IOException {
		try{
			Usuario usuario = usuarioService.getUsuarioByLoginSenha(loginVM.getUsuario().getLogin(),
					encripta.encripta(loginVM.getUsuario().getSenha()));

			commonsController.setUsuarioLogado(usuario);
			FacesUtils.redirect("pages/home/inicio.xhtml");
			
		}catch(NoResultException e){
			MessagesUtils.exibirMensagemErro("mensagem.erro.login");
			
		}catch(Exception e){
			MessagesUtils.exibirMensagemErro("mensagem.erro.salvar.registro");
		}
		

	}

	public LoginVM getLoginVM() {
		return loginVM;
	}

	public void setLoginVM(LoginVM loginVM) {
		this.loginVM = loginVM;
	}
	
	

}

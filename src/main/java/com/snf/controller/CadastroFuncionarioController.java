package com.snf.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.snf.library.Encripta;
import com.snf.model.Funcionario;
import com.snf.model.Role;
import com.snf.service.FuncionarioService;
import com.snf.util.MessagesUtils;
import com.snf.vm.CadastroFuncionarioVM;

@Named
@ViewScoped
public class CadastroFuncionarioController implements Serializable {

	private static final long serialVersionUID = -3202126812210206703L;
	
	static final Logger log = Logger.getLogger(CadastroFuncionarioController.class);

	@Inject
	private FuncionarioService funcionarioService;
	
	@Inject
	private CadastroFuncionarioVM cadastroFuncionarioVM;
	
	@Inject
	private Encripta encriptador;
	
	@PostConstruct
	public void init() {
	}
	
	public void salvar(){
		try{
			if(isSenhasIguais()){
				Funcionario func = cadastroFuncionarioVM.getFuncionario();
				Role role = cadastroFuncionarioVM.getRole();
				preencherDadosUsuario(func, role);
				funcionarioService.salvar(func);
				MessagesUtils.exibirMensagemSucesso("mensagem.sucesso.salvar.registro");
				limparFuncionario();
			}else{
				MessagesUtils.exibirMensagemErro("mensagem.senhas.nao.iguais");
			}
			limparSenhas();
		} catch (Exception e) {
			log.error(e.toString());
			MessagesUtils.exibirMensagemErro("mensagem.erro.salvar.registro");
		}
	}

	private void preencherDadosUsuario(Funcionario func, Role role) {
		role.setUsuario(func);
		String senha = cadastroFuncionarioVM.getSenha();
		func.getRoles().add(role);
		func.setSenha(encriptador.encriptar(senha));
	}

	private void limparFuncionario() {
		cadastroFuncionarioVM.setFuncionario(new Funcionario());
	}
	
	private void limparSenhas() {
		cadastroFuncionarioVM.setSenha("");
		cadastroFuncionarioVM.setSenhaNovamente("");
	}

	public CadastroFuncionarioVM getCadastroFuncionarioVM() {
		return cadastroFuncionarioVM;
	}

	public void setCadastroFuncionarioVM(CadastroFuncionarioVM cadastroFuncionarioVM) {
		this.cadastroFuncionarioVM = cadastroFuncionarioVM;
	}
	
	public boolean isSenhasIguais() {
		String senha = cadastroFuncionarioVM.getSenha().trim();
		String senhaNovamente = cadastroFuncionarioVM.getSenhaNovamente().trim();
		return senha.equals(senhaNovamente);
	}

}

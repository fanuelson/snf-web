package com.snf.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.snf.model.Funcionario;
import com.snf.service.FuncionarioService;
import com.snf.util.MessagesUtils;
import com.snf.vm.CadastroFuncionarioVM;

@Named
@ViewScoped
public class CadastroFuncionarioController implements Serializable {

	private static final long serialVersionUID = -3202126812210206703L;

	@Inject
	private FuncionarioService funcionarioService;
	
	@Inject
	private CadastroFuncionarioVM cadastroFuncionarioVM;
	
	public void salvar(){
		try{
			funcionarioService.salvar(cadastroFuncionarioVM.getFuncionario());
			limparCampos();
			MessagesUtils.exibirMensagemSucesso("mensagem.sucesso.salvar.registro");
		} catch (Exception e) {
			e.printStackTrace();
			MessagesUtils.exibirMensagemErro("mensagem.erro.salvar.registro");
		}
	}

	private void limparCampos() {
		cadastroFuncionarioVM.setFuncionario(new Funcionario());
	}

	public CadastroFuncionarioVM getCadastroFuncionarioVM() {
		return cadastroFuncionarioVM;
	}

	public void setCadastroFuncionarioVM(CadastroFuncionarioVM cadastroFuncionarioVM) {
		this.cadastroFuncionarioVM = cadastroFuncionarioVM;
	}
	
	
}

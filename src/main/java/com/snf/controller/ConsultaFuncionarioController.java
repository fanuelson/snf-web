package com.snf.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.snf.model.Funcionario;
import com.snf.service.FuncionarioService;
import com.snf.util.CollectionsUtils;
import com.snf.util.MessagesUtils;

@Named
@ViewScoped
public class ConsultaFuncionarioController implements Serializable {

	private static final long serialVersionUID = -1288711489651556752L;
	
	static final Logger log = Logger.getLogger(ConsultaFuncionarioController.class);
	
	@Inject
	private FuncionarioService funcionarioService;
	
	private List<Funcionario> funcionarios;
	
	private List<Funcionario> filteredFuncionarios;
	
	@PostConstruct
	public void init(){
		funcionarios = funcionarioService.getAll();
	}
	
	public void remover(Funcionario funcionario){
		try{
			funcionarioService.remover(funcionario);
			funcionarios.remove(funcionario);
			if(!CollectionsUtils.isNullOrEmpty(filteredFuncionarios))
				filteredFuncionarios.remove(funcionario);
			MessagesUtils.exibirMensagemSucesso("mensagem.sucesso.remover.registro");
		}catch(Exception e){
			log.error(e.toString());
			MessagesUtils.exibirMensagemErro("mensagem.erro.remover.registro");
		}
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Funcionario> getFilteredFuncionarios() {
		return filteredFuncionarios;
	}

	public void setFilteredFuncionarios(List<Funcionario> filteredFuncionarios) {
		this.filteredFuncionarios = filteredFuncionarios;
	}
	
	
	

}

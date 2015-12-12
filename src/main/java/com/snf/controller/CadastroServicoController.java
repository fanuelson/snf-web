package com.snf.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.snf.VM.CadastroServicoVM;
import com.snf.model.Funcionario;
import com.snf.model.Servico;
import com.snf.service.FuncionarioService;
import com.snf.service.ServicoService;
import com.snf.util.DataUtil;
import com.snf.util.MessagesUtils;

@Named
@ViewScoped
public class CadastroServicoController implements Serializable {

	private static final long serialVersionUID = -5932008545595398278L;

	@Inject
	private FuncionarioService funcionarioService;
	
	@Inject
	private ServicoService servicoService;
	
	@Inject
	private CadastroServicoVM cadastroServicoVM;
	
	private List<Funcionario> funcionarios;
	
	@PostConstruct
	public void init(){
		funcionarios = funcionarioService.getAll();
	}
	
	public void salvar(){
		try{
			cadastroServicoVM.getServico().setData(DataUtil.dateToTimeStamp(cadastroServicoVM.getData()));
			servicoService.salvar(cadastroServicoVM.getServico());
			limparCampos();
			MessagesUtils.exibirMensagemSucesso("mensagem.sucesso.salvar.registro");
		}catch(Exception e){
			e.printStackTrace();
			MessagesUtils.exibirMensagemErro("mensagem.erro.salvar.registro");
		}
	}
	
	private void limparCampos(){
		cadastroServicoVM.setServico(new Servico());
		cadastroServicoVM.setData(new Date());
	}

	public CadastroServicoVM getCadastroServicoVM() {
		return cadastroServicoVM;
	}

	public void setCadastroServicoVM(CadastroServicoVM cadastroServicoVM) {
		this.cadastroServicoVM = cadastroServicoVM;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
}

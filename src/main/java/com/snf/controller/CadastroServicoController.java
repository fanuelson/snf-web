package com.snf.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.snf.model.Funcionario;
import com.snf.model.Servico;
import com.snf.service.CaixaService;
import com.snf.service.FuncionarioService;
import com.snf.service.ServicoService;
import com.snf.util.DataUtil;
import com.snf.util.MessagesUtils;
import com.snf.vm.CadastroServicoVM;

@Named
@ViewScoped
public class CadastroServicoController implements Serializable {

	private static final long serialVersionUID = -5932008545595398278L;
	
	static final Logger log = Logger.getLogger(CadastroServicoController.class);

	@Inject
	private FuncionarioService funcionarioService;
	
	@Inject
	private ServicoService servicoService;
	
	@Inject
	private CadastroServicoVM cadastroServicoVM;
	
	@Inject
	private CaixaService caixaService;
	
	private List<Funcionario> funcionarios;
	
	@PostConstruct
	public void init(){
		funcionarios = funcionarioService.getAll();
		cadastroServicoVM.setNaoExisteCaixaAberto(!caixaService.existeCaixaAberto());
	}
	
	public void salvar(){
		try{
			cadastroServicoVM.getServico().setData(DataUtil.dateToTimeStamp(cadastroServicoVM.getData()));
			servicoService.salvar(cadastroServicoVM.getServico());
			limparCampos();
			MessagesUtils.exibirMensagemSucesso("mensagem.sucesso.salvar.registro");
		}catch(Exception e){
			log.error(e.toString());
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

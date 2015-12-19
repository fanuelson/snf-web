package com.snf.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.snf.VM.ConsultaServicoVM;
import com.snf.model.Funcionario;
import com.snf.model.Servico;
import com.snf.service.FuncionarioService;
import com.snf.service.ServicoService;
import com.snf.util.MessagesUtils;

@Named
@ViewScoped
public class ConsultaServicoController implements Serializable {

	private static final long serialVersionUID = -8784362664957105320L;

	@Inject
	private ServicoService servicoService;
	
	@Inject
	private FuncionarioService funcionarioService;
	
	@Inject
	private ConsultaServicoVM consultaServicoVM;
	
	private List<Servico> servicos;
	
	private List<Servico> servicosFiltered;
	
	private List<Funcionario> funcionarios;
	
	private double valorTotalPesquisa;
	
	@PostConstruct
	public void init(){
		valorTotalPesquisa = 0;
		servicos = servicoService.getAll();
		funcionarios = funcionarioService.getAll();
		calcularValorTotalPesquisa();
	}
	
	public void calcularValorTotalPesquisa(){
		valorTotalPesquisa = 0;
		for (Servico servico : servicos) {
			valorTotalPesquisa+=servico.getValor();
		}
	}
	
	public void pesquisar(){
		if(periodoPesquisaValido()){
			pesquisarPeriodoFuncionario();
			calcularValorTotalPesquisa();
			limparFiltered();
		}else
			MessagesUtils.exibirMensagemErro("mensagem.erro.pesquisa.periodo");
	}
	
	public void remover(Servico servico){
		try{
			servicoService.remover(servico);
			servicos.remove(servico);
			if(servicosFiltered!=null)
				servicosFiltered.remove(servico);
			MessagesUtils.exibirMensagemSucesso("mensagem.sucesso.remover.registro");
			
		}catch(Exception e){
			e.printStackTrace();
			MessagesUtils.exibirMensagemErro("mensagem.erro.remover.registro");
		}
	}
	
	private void pesquisarPeriodoFuncionario(){
		servicos = servicoService.getServicosByPeriodoAndFuncionario(consultaServicoVM.getDataInicio(), consultaServicoVM.getDataFim(), consultaServicoVM.getFuncionario());
	}
	
	private void limparFiltered(){
		servicosFiltered = null;
	}
	
	private boolean periodoPesquisaValido(){
		 if(consultaServicoVM.getDataInicio()!=null && consultaServicoVM.getDataFim()!=null){
			 if(consultaServicoVM.getDataInicio().before(consultaServicoVM.getDataFim()))
				return true;
			 else
				return false; 
		 }
		 return true;
	}

	public ConsultaServicoVM getConsultaServicoVM() {
		return consultaServicoVM;
	}

	public void setConsultaServicoVM(ConsultaServicoVM consultaServicoVM) {
		this.consultaServicoVM = consultaServicoVM;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public List<Servico> getServicosFiltered() {
		return servicosFiltered;
	}

	public void setServicosFiltered(List<Servico> servicosFiltered) {
		this.servicosFiltered = servicosFiltered;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public double getValorTotalPesquisa() {
		return valorTotalPesquisa;
	}

	public void setValorTotalPesquisa(double valorTotalPesquisa) {
		this.valorTotalPesquisa = valorTotalPesquisa;
	}
	
}
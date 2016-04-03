package com.snf.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;

import com.snf.model.Caixa;
import com.snf.model.Servico;
import com.snf.service.CaixaService;
import com.snf.vm.ConsultaTransacaoVM;

@Named
@ViewScoped
public class ConsultaTransacaoController implements Serializable {

	private static final long serialVersionUID = -8784362664957105320L;

	static final Logger log = Logger.getLogger(ConsultaTransacaoController.class);

	@Inject
	private CaixaService caixaService;

	@Inject
	private ConsultaTransacaoVM consultaTransacaoVM;

	@PostConstruct
	public void init() {
		consultaTransacaoVM.setCaixas(caixaService.getAllOrderByDataAberturaFetchTransacoes());
	}

	public void selecionar(SelectEvent event) {
		Caixa caixaSelecionado = (Caixa) event.getObject();
		consultaTransacaoVM.setCaixaSelecionado(caixaSelecionado);
	}

	public void pesquisar() {

	}

	public void remover(Servico servico) {

	}

	public ConsultaTransacaoVM getConsultaTransacaoVM() {
		return consultaTransacaoVM;
	}

	public void setConsultaTransacaoVM(ConsultaTransacaoVM consultaTransacaoVM) {
		this.consultaTransacaoVM = consultaTransacaoVM;
	}

}

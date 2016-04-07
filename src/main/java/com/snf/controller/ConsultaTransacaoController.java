package com.snf.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;

import com.snf.model.Caixa;
import com.snf.model.Transacao;
import com.snf.service.CaixaService;
import com.snf.util.CollectionsUtils;
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
		List<Caixa> caixas = caixaService.getAllOrderByDataAberturaFetchTransacoes();
		consultaTransacaoVM.setCaixas(caixas);
		if (!CollectionsUtils.isNullOrEmpty(caixas)) {
			consultaTransacaoVM.setCaixaSelecionado(caixas.get(0));
		}
	}

	public void selecionarCaixa(SelectEvent event) {
		Caixa caixaSelecionado = (Caixa) event.getObject();
		consultaTransacaoVM.setCaixaSelecionado(caixaSelecionado);
	}

	public void selecionarTransacao(SelectEvent event) {
		Transacao transacaoSelecionada = (Transacao) event.getObject();
		consultaTransacaoVM.setTransacaoSelecionada(transacaoSelecionada);
	}

	public Double getValorTotalCaixas() {
		Double valorTotal = 0.0;
		for (Caixa caixa : consultaTransacaoVM.getCaixas()) {
			valorTotal += (caixa.getValorAtual() - caixa.getValorInicial());
		}
		return valorTotal;
	}

	public Double getValorTotalTransacoes() {
		Double valorTotal = 0.0;
		for (Transacao transacao : consultaTransacaoVM.getCaixaSelecionado().getTransacoes()) {
			valorTotal += transacao.getValor();
		}
		return valorTotal;
	}

	public ConsultaTransacaoVM getConsultaTransacaoVM() {
		return consultaTransacaoVM;
	}

	public void setConsultaTransacaoVM(ConsultaTransacaoVM consultaTransacaoVM) {
		this.consultaTransacaoVM = consultaTransacaoVM;
	}

}

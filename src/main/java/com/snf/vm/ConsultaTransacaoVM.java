package com.snf.vm;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.snf.abstractLazyModel.AbstractLazyDataModel;
import com.snf.model.Caixa;
import com.snf.model.Transacao;

public class ConsultaTransacaoVM implements Serializable {

	private static final long serialVersionUID = 340286260547316478L;

	private Date dataInicio;

	private Date dataFim;

	private Double valorTotalCaixas = 0.0;
	
	@Inject
	private AbstractLazyDataModel<Caixa> caixas;

	@Inject
	private Caixa caixaSelecionado;

	private Transacao transacaoSelecionada;

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Caixa getCaixaSelecionado() {
		return caixaSelecionado;
	}

	public void setCaixaSelecionado(Caixa caixaSelecionado) {
		this.caixaSelecionado = caixaSelecionado;
	}

	public Transacao getTransacaoSelecionada() {
		return transacaoSelecionada;
	}

	public void setTransacaoSelecionada(Transacao transacaoSelecionada) {
		this.transacaoSelecionada = transacaoSelecionada;
	}

	public AbstractLazyDataModel<Caixa> getCaixas() {
		return caixas;
	}

	public void setCaixas(AbstractLazyDataModel<Caixa> caixas2) {
		this.caixas = caixas2;
	}

	public Double getValorTotalCaixas() {
		return valorTotalCaixas;
	}

	public void setValorTotalCaixas(Double valorTotalCaixas) {
		this.valorTotalCaixas = valorTotalCaixas;
	}
	
}

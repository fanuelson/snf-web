package com.snf.vm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.snf.model.Caixa;

public class ConsultaTransacaoVM implements Serializable {

	private static final long serialVersionUID = 340286260547316478L;

	private Date dataInicio;

	private Date dataFim;

	private List<Caixa> caixas = new ArrayList<>();

	private List<Caixa> caixasFiltered = new ArrayList<>();

	@Inject
	private Caixa caixaSelecionado;

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

	public List<Caixa> getCaixas() {
		return caixas;
	}

	public void setCaixas(List<Caixa> caixas) {
		this.caixas = caixas;
	}

	public Caixa getCaixaSelecionado() {
		return caixaSelecionado;
	}

	public void setCaixaSelecionado(Caixa caixaSelecionado) {
		this.caixaSelecionado = caixaSelecionado;
	}

	public List<Caixa> getCaixasFiltered() {
		return caixasFiltered;
	}

	public void setCaixasFiltered(List<Caixa> caixasFiltered) {
		this.caixasFiltered = caixasFiltered;
	}

}

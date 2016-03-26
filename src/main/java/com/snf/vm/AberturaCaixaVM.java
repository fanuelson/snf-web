package com.snf.vm;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.snf.model.Caixa;

public class AberturaCaixaVM implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Caixa caixa;
	
	private List<Caixa> caixas;
	
	private boolean existeCaixaAberto;
	
	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public boolean isExisteCaixaAberto() {
		return existeCaixaAberto;
	}

	public void setExisteCaixaAberto(boolean existeCaixaAberto) {
		this.existeCaixaAberto = existeCaixaAberto;
	}

	public List<Caixa> getCaixas() {
		return caixas;
	}

	public void setCaixas(List<Caixa> caixas) {
		this.caixas = caixas;
	}
	
}

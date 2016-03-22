package com.snf.VM;

import java.io.Serializable;

import javax.inject.Inject;

import com.snf.model.Caixa;

public class AberturaCaixaVM implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Caixa caixa;
	
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
	
}

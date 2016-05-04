package com.snf.vm;

import java.io.Serializable;

import javax.inject.Inject;

import com.snf.abstractLazyModel.AbstractLazyDataModel;
import com.snf.model.Caixa;

public class AberturaCaixaVM implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Caixa caixaAberto;
	
	@Inject
	private AbstractLazyDataModel<Caixa> caixas;
	
	private boolean existeCaixaAberto;
	
	public Caixa getCaixaAberto() {
		return caixaAberto;
	}

	public void setCaixaAberto(Caixa caixaAberto) {
		this.caixaAberto = caixaAberto;
	}

	public boolean isExisteCaixaAberto() {
		return existeCaixaAberto;
	}

	public void setExisteCaixaAberto(boolean existeCaixaAberto) {
		this.existeCaixaAberto = existeCaixaAberto;
	}

	public AbstractLazyDataModel<Caixa> getCaixas() {
		return caixas;
	}

	public void setCaixas(AbstractLazyDataModel<Caixa> caixas) {
		this.caixas = caixas;
	}
	
}

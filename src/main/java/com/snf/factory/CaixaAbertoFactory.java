package com.snf.factory;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.snf.model.Caixa;
import com.snf.qualifier.CaixaAberto;
import com.snf.service.CaixaService;

public class CaixaAbertoFactory {
	
	@Inject
	private CaixaService caixaService;
	
	@Produces
	@CaixaAberto
	public Caixa getCaixaAberto() {
		return caixaService.getCaixaAberto();
	}

}

package com.snf.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.snf.dao.CaixaDAO;
import com.snf.model.Caixa;

public class CaixaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CaixaDAO caixaDAO;
	
	public Caixa salvar(Caixa caixa) {
		return caixaDAO.save(caixa);
	}
	
	public Caixa abrirCaixa(Caixa caixa) {
		caixa.setDataAbertura(new Date());
		caixa.setValorAtual(caixa.getValorInicial());
		return caixaDAO.save(caixa);
	}
	
	public Caixa getCaixaAberto() {
		List<Caixa> caixas = caixaDAO.getAll();
		for (Caixa caixa : caixas) {
			if(caixa.isAberto()) {
				return caixa;
			}
		}
		return null;
	}
	
	public void fecharCaixa(Caixa caixa) {
		caixa.fechar();
		caixaDAO.save(caixa);
	}
	
	public List<Caixa> getAll() {
		return caixaDAO.getAll();
	}
	
	public boolean remover(Caixa caixa) {
		return caixaDAO.delete(caixa);
	}

}

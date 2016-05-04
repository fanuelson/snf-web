package com.snf.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.snf.dao.CaixaDAO;
import com.snf.lazyModel.PaginaDataModel;
import com.snf.model.Caixa;

public class CaixaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	static final Logger log = Logger.getLogger(CaixaService.class);
	
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
		return caixaDAO.getCaixaAberto();
	}
	
	public boolean existeCaixaAberto() {
		return getCaixaAberto()!=null;
	}
	
	public Caixa fecharCaixa(Caixa caixa) {
		caixa.fechar();
		return caixaDAO.save(caixa);
	}
	
	public List<Caixa> getAll() {
		return caixaDAO.getAll();
	}
	
	public List<Caixa> getAllOrderByDataAbertura() {
		return caixaDAO.getAllOrderByDataAbertura();
	}
	
	public PaginaDataModel<Caixa> getAllOrderByDataAbertura(PaginaDataModel<Caixa> paginaCaixas){
		return caixaDAO.getAllOrderByDataAbertura(paginaCaixas);
	}
	
	public List<Caixa> getAllOrderByDataAberturaFetchTransacoes() {
		return caixaDAO.getAllOrderByDataAberturaFetchTransacoes();
	}
	
	public boolean remover(Caixa caixa) {
		return caixaDAO.delete(caixa);
	}

}

package com.snf.dao;

import java.util.List;

import com.snf.builder.JPQLBuilder;
import com.snf.genericDao.GenericDAO;
import com.snf.lazyModel.PaginaDataModel;
import com.snf.model.Caixa;

public class CaixaDAO extends GenericDAO<Caixa, Long> {

	private static final long serialVersionUID = 1L;

	public List<Caixa> getAllOrderByDataAbertura() {
		List<Caixa> caixas = null;

		try {
			getManager().clear();
			caixas = new JPQLBuilder()
					.select("c")
					.from(Caixa.class, "c")
					.orderBy("c.dataAbertura")
					.desc()
					.contruir(getManager(), Caixa.class)
					.getResultList();

		} catch (Exception e) {
			log.error(e.toString());
		}

		return caixas;
	}
	
	public PaginaDataModel<Caixa> getAllOrderByDataAbertura(PaginaDataModel<Caixa> paginaCaixas) {
		
		try {
			getManager().clear();
			paginaCaixas = new JPQLBuilder()
					.select("c")
					.from(Caixa.class, "c")
					.orderBy("c.dataAbertura")
					.desc()
					.contruirPaginado(getManager(), paginaCaixas, Caixa.class);
			
		} catch (Exception e) {
			log.error(e.toString());
		}
		
		return paginaCaixas;
	}
	
	public List<Caixa> getAllOrderByDataAberturaFetchTransacoes() {
		List<Caixa> caixas = null;

		try {
			getManager().clear();
			caixas = new JPQLBuilder()
					.select("DISTINCT c")
					.from(Caixa.class, "c")
					.leftJoinFetch("c.transacoes", "t")
					.orderBy("c.dataAbertura")
					.desc()
					.contruir(getManager(), Caixa.class)
					.getResultList();
		} catch (Exception e) {
			log.error(e.toString());
		}

		return caixas;
	}
	
	public PaginaDataModel<Caixa> getAllOrderByDataAberturaFetchTransacoes(PaginaDataModel<Caixa> paginaCaixas) {

		try {
			getManager().clear();
			paginaCaixas = new JPQLBuilder()
					.select("DISTINCT c")
					.from(Caixa.class, "c")
					.leftJoinFetch("c.transacoes", "t")
					.orderBy("c.dataAbertura")
					.desc()
					.contruirPaginado(getManager(), paginaCaixas, Caixa.class);
		} catch (Exception e) {
			log.error(e.toString());
		}

		return paginaCaixas;
	}
	
	public Caixa getCaixaAberto() {
		Caixa caixa = null;

		try {
			getManager().clear();
			caixa = new JPQLBuilder()
					.select("DISTINCT c")
					.from(Caixa.class, "c")
					.where("c.dataFechamento IS NULL")
					.contruir(getManager(), Caixa.class)
					.getSingleResult();

		} catch (Exception e) {
			log.error(e.toString());
			return null;
		}

		return caixa;
	}
	
	public Double getSomaTotal(){
		Double somaTotal = 0.0;
		try{
			somaTotal = new JPQLBuilder()
					.select("SUM(c.valorAtual - c.valorInicial)")
					.from(Caixa.class,"c")
					.contruir(getManager(), Double.class)
					.getSingleResult();
		} catch (Exception e) {
			log.error(e.toString());
			return null;
		}
		return somaTotal;
	}

}

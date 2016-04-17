package com.snf.dao;

import java.util.List;

import com.snf.builder.JPQLBuilder;
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

}

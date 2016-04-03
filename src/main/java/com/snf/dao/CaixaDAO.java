package com.snf.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import com.snf.builder.JPQLBuilder;
import com.snf.model.Caixa;

public class CaixaDAO extends GenericDAO<Caixa, Long> {

	private static final long serialVersionUID = 1L;

	public List<Caixa> getAllOrderByDataAbertura() {
		List<Caixa> caixas = null;

		try {
			getManager().clear();
			JPQLBuilder queryBuilder = new JPQLBuilder()
					.select("c")
					.from(Caixa.class, "c")
					.orderBy("c.dataAbertura")
					.desc();

			TypedQuery<Caixa> query = getManager().createQuery(queryBuilder.contruir(), Caixa.class);
			caixas = query.getResultList();
		} catch (Exception e) {
			log.error(e.toString());
		}

		return caixas;
	}
	
	public List<Caixa> getAllOrderByDataAberturaFetchTransacoes() {
		List<Caixa> caixas = null;

		try {
			getManager().clear();
			JPQLBuilder queryBuilder = new JPQLBuilder()
					.select("DISTINCT c")
					.from(Caixa.class, "c")
					.leftJoinFetch("c.transacoes", "t")
					.orderBy("c.dataAbertura")
					.desc();

			TypedQuery<Caixa> query = getManager().createQuery(queryBuilder.contruir(), Caixa.class);
			caixas = query.getResultList();
		} catch (Exception e) {
			log.error(e.toString());
		}

		return caixas;
	}

}

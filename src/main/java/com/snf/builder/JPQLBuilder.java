package com.snf.builder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.snf.dataModel.PaginaDataModel;

public class JPQLBuilder implements Serializable {

	private static final long serialVersionUID = 1L;

	static final Logger log = Logger.getLogger(JPQLBuilder.class);

	private static String SELECT = " SELECT ";
	private static String FROM = " FROM ";
	private static String WHERE = " WHERE ";
	private static String AND = " AND ";
	private static String OR = " OR ";
	private static String GROUP_BY = " GROUP BY ";
	private static String ORDER_BY = " ORDER BY ";
	private static String INNER_JOIN = " INNER JOIN ";
	private static String INNER_JOIN_FETCH = " INNER JOIN FETCH ";
	private static String LEFT_JOIN = " LEFT JOIN ";
	private static String LEFT_JOIN_FETCH = " LEFT JOIN FETCH ";
	private static String RIGHT_JOIN = " RIGHT JOIN ";
	private static String RIGHT_JOIN_FETCH = " RIGHT JOIN FETCH ";
	private String entityAlias;
	private StringBuilder queryString;
	private Map<String, Object> parametros;

	public JPQLBuilder() {
		queryString = new StringBuilder();
		parametros = new HashMap<>();

	}

	public JPQLBuilder select(String alias) {
		queryString.append(SELECT + alias);
		return this;
	}
	
	public JPQLBuilder contruirJPQL() {
		return this;
	}

	public String contruir() {
		return queryString.toString();
	}
	
	public Query contruir(EntityManager em) {
		Query query = em.createQuery(this.contruir());
		colocarValorDosParametros(query);
		return query;
	}

	public <T> TypedQuery<T> contruir(EntityManager em, Class<T> returnType) {
		TypedQuery<T> query = em.createQuery(this.contruir(), returnType);
		colocarValorDosParametros(query);
		return query;
	}
	
	public <T> PaginaDataModel<T> contruirPaginado(EntityManager em, PaginaDataModel<T> paginaRetorno, Class<T> registrosType) {
		if(paginaRetorno.getSortOrder().equals("ASC") && paginaRetorno.getSortField()!=null){
			this.orderBy(this.entityAlias+"."+paginaRetorno.getSortField());
			this.asc();
		}else if(paginaRetorno.getSortOrder().equals("DESC") && paginaRetorno.getSortField()!=null){
			this.orderBy(this.entityAlias+"."+paginaRetorno.getSortField());
			this.desc();
		}
		TypedQuery<T> query = em.createQuery(this.contruir(), registrosType);
		colocarValorDosParametros(query);
		query.setFirstResult(paginaRetorno.getFirstResult());
		query.setMaxResults(paginaRetorno.getPageSize());
		List<T> registros = new ArrayList<>();
		try {
			registros = query.getResultList();
			paginaRetorno.setRegistrosPagina(registros);
			paginaRetorno.setTotalRegistros(getTotalRegistros(em));
		} catch (Exception e) {
			log.error(e.toString());
		}
		return paginaRetorno;
	}
	
	public Integer getTotalRegistros(EntityManager em) {
		String queryString = this.contruir();
		String queryCount = queryString.replaceFirst(" "+entityAlias+" ", getCountAlias());
		TypedQuery<Long> query = em.createQuery(queryCount, Long.class);
		colocarValorDosParametros(query);
		return query.getSingleResult().intValue();
	}
	
	private String getCountAlias(){
		return String.format(" count(%s) ", entityAlias);
	}

	private void colocarValorDosParametros(Query query) {
		for (Map.Entry<String, Object> parametro : getParametros().entrySet()) {
			query.setParameter(parametro.getKey(), parametro.getValue());
		}
	}

	public JPQLBuilder from(Class<?> classe, String alias) {
		queryString.append(FROM);
		queryString.append(classe.getSimpleName());
		queryString.append(" " + alias + " ");
		entityAlias = alias;
		return this;
	}
	
	public JPQLBuilder from(String fromClause) {
		queryString.append(FROM);
		queryString.append(" " + fromClause + " ");
		return this;
	}

	public JPQLBuilder leftJoinFetch(String relacionamento, String alias) {
		queryString.append(LEFT_JOIN_FETCH);
		queryString.append(relacionamento);
		queryString.append(" " + alias + " ");
		return this;
	}

	public JPQLBuilder leftJoin(String relacionamento, String alias) {
		queryString.append(LEFT_JOIN);
		queryString.append(relacionamento);
		queryString.append(" " + alias + " ");
		return this;
	}

	public JPQLBuilder innerJoin(String relacionamento, String alias) {
		queryString.append(INNER_JOIN);
		queryString.append(relacionamento);
		queryString.append(" " + alias + " ");
		return this;
	}

	public JPQLBuilder innerJoinFetch(String relacionamento, String alias) {
		queryString.append(INNER_JOIN_FETCH);
		queryString.append(relacionamento);
		queryString.append(" " + alias + " ");
		return this;
	}

	public JPQLBuilder rightJoinFetch(String relacionamento, String alias) {
		queryString.append(RIGHT_JOIN_FETCH);
		queryString.append(relacionamento);
		queryString.append(" " + alias + " ");
		return this;
	}

	public JPQLBuilder rightJoin(String relacionamento, String alias) {
		queryString.append(RIGHT_JOIN);
		queryString.append(relacionamento);
		queryString.append(" " + alias + " ");
		return this;
	}

	public JPQLBuilder where(String clausulaWhere, Object valor) {
		queryString.append(WHERE);
		queryString.append(clausulaWhere);
		inserirParametro(" " + clausulaWhere + " ", valor);
		return this;
	}
	
	public JPQLBuilder where(String clausulaWhere) {
		queryString.append(WHERE);
		queryString.append(clausulaWhere);
		return this;
	}

	public JPQLBuilder and(String clausulaAnd, Object valor) {
		queryString.append(AND);
		queryString.append(clausulaAnd);
		inserirParametro(" " + clausulaAnd + " ", valor);
		return this;
	}
	
	public JPQLBuilder and(String clausulaAnd) {
		queryString.append(AND);
		queryString.append(clausulaAnd);
		return this;
	}

	public JPQLBuilder or(String clausulaOr, Object valor) {
		queryString.append(OR);
		queryString.append(clausulaOr);
		inserirParametro(" " + clausulaOr + " ", valor);
		return this;
	}

	public JPQLBuilder groupBy(String parametro) {
		if(parametro != null){
			queryString.append(GROUP_BY);
			queryString.append(" ( " + parametro + " ) ");
		}
		return this;
	}

	public JPQLBuilder orderBy(String parametro) {
		if(parametro != null){
			queryString.append(ORDER_BY);
			queryString.append(" ( " + parametro + " ) ");
		}
		return this;
	}

	public JPQLBuilder asc() {
		queryString.append(" ASC ");
		return this;
	}

	public JPQLBuilder desc() {
		queryString.append(" DESC ");
		return this;
	}

	private void inserirParametro(String clausulaWhere, Object valor) {
		parametros.put(getNomeParametro(clausulaWhere), valor);
	}

	public String getNomeParametro(String clausulaWhere) {
		String nomeParametro = "";

		for (int i = clausulaWhere.lastIndexOf(":") + 1; i < clausulaWhere.length(); i++) {
			if (clausulaWhere.charAt(i) == ' ')
				break;
			nomeParametro += clausulaWhere.charAt(i);
		}
		nomeParametro = nomeParametro.replaceAll("\\(", "");
		nomeParametro = nomeParametro.replaceAll("\\)", "");
		return nomeParametro.trim();
	}

	public Map<String, Object> getParametros() {
		return parametros;
	}

	public void setParametros(Map<String, Object> parametros) {
		this.parametros = parametros;
	}

}

package com.snf.builder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

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

	public String contruir() {
		return queryString.toString();
	}

	public JPQLBuilder from(Class<?> classe, String alias) {
		queryString.append(FROM);
		queryString.append(classe.getSimpleName());
		queryString.append(" " + alias + " ");
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

	public JPQLBuilder where(String restricao, Object valor) {
		queryString.append(WHERE);
		queryString.append(restricao);
		inserirParametro(" " + restricao + " ", valor);
		return this;
	}

	public JPQLBuilder and(String restricao, Object valor) {
		queryString.append(AND);
		queryString.append(restricao);
		inserirParametro(" " + restricao + " ", valor);
		return this;
	}

	public JPQLBuilder or(String restricao, Object valor) {
		queryString.append(OR);
		queryString.append(restricao);
		inserirParametro(" " + restricao + " ", valor);
		return this;
	}

	public JPQLBuilder groupBy(String parametro) {
		queryString.append(GROUP_BY);
		queryString.append(" ( " + parametro + " ) ");
		return this;
	}

	public JPQLBuilder orderBy(String parametro) {
		queryString.append(ORDER_BY);
		queryString.append(" ( " + parametro + " ) ");
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

	private void inserirParametro(String restricao, Object valor) {
		parametros.put(getNomeParametro(restricao), valor);
	}

	public String getNomeParametro(String restricao) {
		String nomeParametro = "";

		for (int i = restricao.lastIndexOf(":") + 1; i < restricao.length(); i++) {
			if (restricao.charAt(i) == ' ')
				break;
			nomeParametro += restricao.charAt(i);
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

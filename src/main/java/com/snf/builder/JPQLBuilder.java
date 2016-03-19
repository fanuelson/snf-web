package com.snf.builder;

import java.util.HashMap;
import java.util.Map;

public class JPQLBuilder {

	private static String SELECT = " SELECT ";
	private static String FROM = " FROM " ;
	private static String WHERE = " WHERE " ;
	private static String AND = " AND " ;
	private static String OR = " OR " ;
	private static String GROUP_BY = " GROUP BY " ;
	private static String ORDER_BY = " ORDER BY " ;
	private StringBuilder queryString;
	private Map<String, Object> parametros;
	
	public JPQLBuilder() {
		queryString = new StringBuilder();
		parametros = new HashMap<String, Object>();
		
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
	
	public JPQLBuilder where(String restricao, Object valor) {
		queryString.append(WHERE);
		queryString.append(restricao);
		inserirParametro(" "+restricao+" ", valor);
		return this;
	}
	
	public JPQLBuilder and(String restricao, Object valor) {
		queryString.append(AND);
		queryString.append(restricao);
		inserirParametro(" "+restricao+" ", valor);
		return this;
	}
	
	public JPQLBuilder or(String restricao, Object valor) {
		queryString.append(OR);
		queryString.append(restricao);
		inserirParametro(" "+restricao+" ", valor);
		return this;
	}
	
	public JPQLBuilder groupBy(String parametro) {
		queryString.append(GROUP_BY);
		queryString.append(" ( " +parametro + " ) ");
		return this;
	}

	public JPQLBuilder orderBy(String parametro) {
		queryString.append(ORDER_BY);
		queryString.append(" ( " +parametro + " ) ");
		return this;
	}
	
	public JPQLBuilder asc() {
		queryString.append(" ASC ");
		return this;
	}
	
	public JPQLBuilder dsc() {
		queryString.append(" DSC ");
		return this;
	}
	
	private void inserirParametro(String restricao, Object valor){
		parametros.put(getNomeParametro(restricao), valor);
	}
	
	public String getNomeParametro(String restricao) {
		String nomeParametro = "";
		
		for(int i = restricao.lastIndexOf(":")+1 ; i < restricao.length() ; i++ ){
			if(restricao.charAt(i) == ' ')
				break;
			nomeParametro += restricao.charAt(i);
		}
		
		return nomeParametro.trim();
	}

	public Map<String, Object> getParametros() {
		return parametros;
	}

	public void setParametros(Map<String, Object> parametros) {
		this.parametros = parametros;
	}
	
}

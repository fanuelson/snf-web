package com.snf.dataModel;

import java.util.List;

import org.primefaces.model.SortOrder;

public class PaginaDataModel<T> {

	private List<T> registrosPagina;
	private int totalRegistros;
	private int firstResult;
	private int pageSize;
	private String sortField;
	private String sortOrder;

	public List<T> getRegistrosPagina() {
		return registrosPagina;
	}

	public void setRegistrosPagina(List<T> registrosPagina) {
		this.registrosPagina = registrosPagina;
	}

	public int getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(int totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		switch (sortOrder) {
		case ASCENDING:
			this.sortOrder = "ASC" ;
			break;
		case DESCENDING:
			this.sortOrder = "DESC" ;
			break;
		default:
			this.sortOrder = "";
			break;
		}
	}
	
	

}

package com.snf.lazyModel;

import java.util.List;

public class PaginaDataModel<T> {

	private List<T> registrosPagina;
	private int totalRegistros;
	private int firstResult;
	private int pageSize;

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

}

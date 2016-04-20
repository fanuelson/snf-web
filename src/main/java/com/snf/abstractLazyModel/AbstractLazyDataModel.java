package com.snf.abstractLazyModel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.snf.dataModel.PaginaDataModel;

public abstract class AbstractLazyDataModel<T> extends LazyDataModel<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters){
		PaginaDataModel<T> paginaServicos = new PaginaDataModel<>();
		paginaServicos.setFirstResult(first);
		paginaServicos.setPageSize(pageSize);
		paginaServicos.setSortField(sortField);
		paginaServicos.setSortOrder(sortOrder);
		paginaServicos = buscarPaginado(paginaServicos);
		setRowCount(paginaServicos.getTotalRegistros());
		return paginaServicos.getRegistrosPagina();
	}

	public abstract PaginaDataModel<T> buscarPaginado(PaginaDataModel<T> pagina);

}

package com.snf.lazyModel;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.snf.abstractLazyModel.AbstractLazyDataModel;
import com.snf.model.Caixa;
import com.snf.service.CaixaService;

public class CaixaLazyDataModel extends AbstractLazyDataModel<Caixa> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CaixaService caixaService;
	
	@Override
	public PaginaDataModel<Caixa> buscarPaginado(PaginaDataModel<Caixa> pagina) {
		return caixaService.getAllOrderByDataAberturaFetchTransacoes(pagina);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Caixa getRowData(String rowKey){
		List<Caixa> caixas = (List<Caixa>) getWrappedData();
		for (Caixa caixa : caixas) {
			if(caixa.getId().equals(Long.valueOf(rowKey))){
				return caixa;
			}
		}
		return new Caixa();
	}

}

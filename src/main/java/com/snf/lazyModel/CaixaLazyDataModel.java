package com.snf.lazyModel;

import java.io.Serializable;

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
		return caixaService.getAllOrderByDataAbertura(pagina);
	}

}

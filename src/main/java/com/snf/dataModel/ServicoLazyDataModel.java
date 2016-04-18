package com.snf.dataModel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.snf.model.Servico;
import com.snf.service.ServicoService;
import com.snf.vo.FiltroConsultaServicoVO;

public class ServicoLazyDataModel extends LazyDataModel<Servico> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServicoService servicoService;
	
	@Inject
	private FiltroConsultaServicoVO filtro;
	
	@Override
    public Object getRowKey(Servico servico) {
        return servico.getId();
    }
	
	public List<Servico> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters){
		PaginaDataModel<Servico> paginaServicos = new PaginaDataModel<>();
		paginaServicos.setFirstResult(first);
		paginaServicos.setPageSize(pageSize);
		paginaServicos.setSortField(sortField);
		paginaServicos.setSortOrder(sortOrder);
		paginaServicos = servicoService.getServicosByPeriodoAndFuncionario(filtro, paginaServicos);
		setRowCount(paginaServicos.getTotalRegistros());
		return paginaServicos.getRegistrosPagina();
	}

	public FiltroConsultaServicoVO getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroConsultaServicoVO filtro) {
		this.filtro = filtro;
	}

}

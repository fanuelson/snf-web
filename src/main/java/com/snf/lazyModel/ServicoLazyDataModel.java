package com.snf.lazyModel;

import java.io.Serializable;

import javax.inject.Inject;

import com.snf.abstractLazyModel.AbstractLazyDataModel;
import com.snf.dto.ConsultaServicoDTO;
import com.snf.model.Servico;
import com.snf.rest.RestClient;
import com.snf.vo.FiltroConsultaServicoVO;

public class ServicoLazyDataModel extends AbstractLazyDataModel<Servico> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FiltroConsultaServicoVO filtro;
	
	public FiltroConsultaServicoVO getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroConsultaServicoVO filtro) {
		this.filtro = filtro;
	}

	@Override
	public PaginaDataModel<Servico> buscarPaginado(PaginaDataModel<Servico> pagina) {
		ConsultaServicoDTO dto = new ConsultaServicoDTO(pagina, filtro);
		dto = RestClient.httpPostJson("/servicos/consultarPaginado", ConsultaServicoDTO.class, dto);
		return dto.getPagina();
	}

}

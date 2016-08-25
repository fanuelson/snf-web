package com.snf.dto;

import com.snf.lazyModel.PaginaDataModel;
import com.snf.model.Servico;
import com.snf.vo.FiltroConsultaServicoVO;

public class ConsultaServicoDTO {

	private PaginaDataModel<Servico> pagina;
	private FiltroConsultaServicoVO filtro;
	
	public ConsultaServicoDTO() { };
	
	public ConsultaServicoDTO(PaginaDataModel<Servico> pagina, FiltroConsultaServicoVO filtro) {
		super();
		this.pagina = pagina;
		this.filtro = filtro;
	}

	public PaginaDataModel<Servico> getPagina() {
		return pagina;
	}
	public void setPagina(PaginaDataModel<Servico> pagina) {
		this.pagina = pagina;
	}
	public FiltroConsultaServicoVO getFiltro() {
		return filtro;
	}
	public void setFiltro(FiltroConsultaServicoVO filtro) {
		this.filtro = filtro;
	}
	
	
}

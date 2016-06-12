package com.snf.vm;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.snf.util.DataUtil;
import com.snf.vo.FiltroConsultaServicoVO;

public class RelatorioServicoVM implements Serializable {

	private static final long serialVersionUID = -836588887077362085L;

	@Inject
	private FiltroConsultaServicoVO filtro;
	
	@PostConstruct
	public void init() {
		filtro.setDataInicial(DataUtil.diminuirDias(DataUtil.getDataAtualHoraZerada(), 7));
		filtro.setDataFinal(DataUtil.getDataAtualHoraFinalDia());
	}

	public FiltroConsultaServicoVO getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroConsultaServicoVO filtro) {
		this.filtro = filtro;
	}

	

}

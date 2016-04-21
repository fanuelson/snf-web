package com.snf.lazyModel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.primefaces.model.LazyScheduleModel;

import com.snf.model.Servico;
import com.snf.service.ServicoService;
import com.snf.vo.FiltroConsultaServicoVO;

public class ServicoLazyScheduleModel extends LazyScheduleModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServicoService servicoService;
	
	@Inject
	private FiltroConsultaServicoVO filtro;
	
	@Override
	public void loadEvents(Date start, Date end) {
		filtro.setDataInicial(start);
		filtro.setDataFinal(end);
		List<Servico> servicos = servicoService.getServicosByPeriodoAndFuncionario(filtro);
		for (Servico servico : servicos) {
			addEvent(servico);
		}
	}

}

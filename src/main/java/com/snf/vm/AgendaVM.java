package com.snf.vm;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.primefaces.model.ScheduleModel;

import com.snf.model.Funcionario;
import com.snf.model.Servico;

public class AgendaVM implements Serializable {

	private static final long serialVersionUID = 1L;

	private ScheduleModel agendaModel;

	@Inject
	private Servico servicoSelected;

	private List<Funcionario> funcionarios;

	private boolean naoExisteCaixaAberto;

	public ScheduleModel getAgendaModel() {
		return agendaModel;
	}

	public void setAgendaModel(ScheduleModel agendaModel) {
		this.agendaModel = agendaModel;
	}

	public Servico getServicoSelected() {
		return servicoSelected;
	}

	public void setServicoSelected(Servico servicoSelected) {
		this.servicoSelected = servicoSelected;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public boolean isNaoExisteCaixaAberto() {
		return naoExisteCaixaAberto;
	}

	public void setNaoExisteCaixaAberto(boolean naoExisteCaixaAberto) {
		this.naoExisteCaixaAberto = naoExisteCaixaAberto;
	}

}

package com.snf.vm;

import java.io.Serializable;

import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;

import com.snf.model.Funcionario;
import com.snf.model.Servico;
import com.snf.vo.FiltroConsultaServicoVO;

public class ConsultaServicoVM implements Serializable {

	private static final long serialVersionUID = 340286260547316478L;

	private Funcionario funcionario;

	private boolean isTipoFuncionarioLogado;
	
	@Inject
	private LazyDataModel<Servico> servicos;

	@Inject
	private FiltroConsultaServicoVO filtro;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public boolean isTipoFuncionarioLogado() {
		return isTipoFuncionarioLogado;
	}

	public void setTipoFuncionarioLogado(boolean isTipoFuncionarioLogado) {
		this.isTipoFuncionarioLogado = isTipoFuncionarioLogado;
	}

	public FiltroConsultaServicoVO getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroConsultaServicoVO filtro) {
		this.filtro = filtro;
	}

	public LazyDataModel<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(LazyDataModel<Servico> servicos) {
		this.servicos = servicos;
	}
	
}

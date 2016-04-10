package com.snf.vm;

import java.io.Serializable;

import javax.inject.Inject;

import com.snf.model.Funcionario;
import com.snf.vo.ConsultaServicoVO;

public class ConsultaServicoVM implements Serializable {

	private static final long serialVersionUID = 340286260547316478L;

	private Funcionario funcionario;

	private boolean isTipoFuncionarioLogado;

	@Inject
	private ConsultaServicoVO filtro;

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

	public ConsultaServicoVO getFiltro() {
		return filtro;
	}

	public void setFiltro(ConsultaServicoVO filtro) {
		this.filtro = filtro;
	}
	
}

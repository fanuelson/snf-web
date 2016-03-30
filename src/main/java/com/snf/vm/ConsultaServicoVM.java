package com.snf.vm;

import java.io.Serializable;
import java.util.Date;

import com.snf.model.Funcionario;

public class ConsultaServicoVM implements Serializable {

	private static final long serialVersionUID = 340286260547316478L;

	private Funcionario funcionario;

	private boolean isTipoFuncionarioLogado;

	private Date dataInicio;

	private Date dataFim;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public boolean isTipoFuncionarioLogado() {
		return isTipoFuncionarioLogado;
	}

	public void setTipoFuncionarioLogado(boolean isTipoFuncionarioLogado) {
		this.isTipoFuncionarioLogado = isTipoFuncionarioLogado;
	}

}

package com.snf.vm;

import java.io.Serializable;

import javax.inject.Inject;

import com.snf.lazyModel.ServicoLazyDataModel;
import com.snf.model.Funcionario;

public class ConsultaServicoVM implements Serializable {

	private static final long serialVersionUID = 340286260547316478L;

	private Funcionario funcionario;

	private boolean isTipoFuncionarioLogado;
	
	@Inject
	private ServicoLazyDataModel servicos;

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

	public ServicoLazyDataModel getServicos() {
		return servicos;
	}

	public void setServicos(ServicoLazyDataModel servicos) {
		this.servicos = servicos;
	}
	
}

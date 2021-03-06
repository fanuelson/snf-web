package com.snf.vm;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.snf.model.Servico;

public class CadastroServicoVM implements Serializable {

	private static final long serialVersionUID = 4301551046874550315L;

	@Inject
	private Servico servico;

	private boolean naoExisteCaixaAberto;

	private boolean isTipoFuncionarioLogado;

	@PostConstruct
	public void init() {
		servico.setDataInicio(new Date());
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public boolean isNaoExisteCaixaAberto() {
		return naoExisteCaixaAberto;
	}

	public void setNaoExisteCaixaAberto(boolean naoExisteCaixaAberto) {
		this.naoExisteCaixaAberto = naoExisteCaixaAberto;
	}

	public boolean isTipoFuncionarioLogado() {
		return isTipoFuncionarioLogado;
	}

	public void setTipoFuncionarioLogado(boolean isTipoFuncionarioLogado) {
		this.isTipoFuncionarioLogado = isTipoFuncionarioLogado;
	}

}

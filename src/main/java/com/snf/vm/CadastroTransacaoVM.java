package com.snf.vm;

import java.io.Serializable;

import javax.inject.Inject;

import com.snf.model.Caixa;
import com.snf.model.Transacao;

public class CadastroTransacaoVM implements Serializable {

	private static final long serialVersionUID = 4301551046874550315L;

	@Inject
	private Transacao transacao;

	private Caixa caixaAberto;

	private boolean existeCaixaAberto;

	public Transacao getTransacao() {
		return transacao;
	}

	public void setTransacao(Transacao transacao) {
		this.transacao = transacao;
	}

	public Caixa getCaixaAberto() {
		return caixaAberto;
	}

	public void setCaixaAberto(Caixa caixaAberto) {
		this.caixaAberto = caixaAberto;
	}

	public boolean isExisteCaixaAberto() {
		return existeCaixaAberto;
	}

	public void setExisteCaixaAberto(boolean existeCaixaAberto) {
		this.existeCaixaAberto = existeCaixaAberto;
	}

}

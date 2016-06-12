package com.snf.controller;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.snf.enums.TipoTransacao;
import com.snf.model.Caixa;
import com.snf.model.Transacao;
import com.snf.service.CaixaService;
import com.snf.service.TransacaoService;
import com.snf.util.MessagesUtils;
import com.snf.vm.CadastroTransacaoVM;

@Named
@ViewScoped
public class CadastroTransacaoController implements Serializable {

	private static final long serialVersionUID = -5932008545595398278L;

	static final Logger log = Logger.getLogger(CadastroTransacaoController.class);

	@Inject
	private TransacaoService transacaoService;

	@Inject
	private CadastroTransacaoVM cadastroTransacaoVM;

	@Inject
	private CaixaService caixaService;

	@PostConstruct
	public void init() {
		if (caixaService.getCaixaAberto() != null) {
			cadastroTransacaoVM.setCaixaAberto(caixaService.getCaixaAberto());
			cadastroTransacaoVM.setExisteCaixaAberto(true);
		} else {
			cadastroTransacaoVM.setExisteCaixaAberto(false);
		}
	}

	public void salvar() {
		try {
			Caixa caixaAberto = cadastroTransacaoVM.getCaixaAberto();
			Double valorEntrada = cadastroTransacaoVM.getTransacao().getValor();
			Transacao transacao = cadastroTransacaoVM.getTransacao();
			transacao.setCaixa(caixaAberto);
			transacao.setData(new Date());
			if (transacao.isSaidaCaixa()) {
				transacao.setValor(Math.abs(valorEntrada) * -1);
			} else {
				transacao.setValor(Math.abs(valorEntrada));
			}
			caixaAberto.adicionarValor(transacao.getValor());
			caixaService.salvar(caixaAberto);
			transacaoService.salvar(transacao);
			limparTransacao();
			MessagesUtils.exibirMensagemSucesso("mensagem.sucesso.salvar.registro");
		} catch (Exception e) {
			log.error(e.toString());
			MessagesUtils.exibirMensagemErro("mensagem.erro.salvar.registro");
		}
	}

	private void limparTransacao() {
		cadastroTransacaoVM.setTransacao(new Transacao());
	}

	public void prepararRetirada() {
		cadastroTransacaoVM.getTransacao().setTipo(TipoTransacao.RETIRADA);
	}

	public void prepararDespesa() {
		cadastroTransacaoVM.getTransacao().setTipo(TipoTransacao.DESPESA);
	}

	public void prepararReceita() {
		cadastroTransacaoVM.getTransacao().setTipo(TipoTransacao.RECEITA);
	}

	public CadastroTransacaoVM getCadastroTransacaoVM() {
		return cadastroTransacaoVM;
	}

	public void setCadastroTransacaoVM(CadastroTransacaoVM cadastroTransacaoVM) {
		this.cadastroTransacaoVM = cadastroTransacaoVM;
	}

}

package com.snf.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.snf.enums.TipoTransacao;
import com.snf.enums.TipoUsuario;
import com.snf.model.Funcionario;
import com.snf.model.Servico;
import com.snf.model.Transacao;
import com.snf.model.Usuario;
import com.snf.service.CaixaService;
import com.snf.service.FuncionarioService;
import com.snf.service.ServicoService;
import com.snf.service.TransacaoService;
import com.snf.util.DataUtil;
import com.snf.util.MessagesUtils;
import com.snf.vm.CadastroServicoVM;

@Named
@ViewScoped
public class CadastroServicoController implements Serializable {

	private static final long serialVersionUID = -5932008545595398278L;

	static final Logger log = Logger.getLogger(CadastroServicoController.class);

	@Inject
	private FuncionarioService funcionarioService;

	@Inject
	private ServicoService servicoService;

	@Inject
	private TransacaoService transacaoService;

	@Inject
	private CaixaService caixaService;

	@Inject
	private CadastroServicoVM cadastroServicoVM;

	@Inject
	private CommonsController commons;

	private List<Funcionario> funcionarios = new ArrayList<>();

	@PostConstruct
	public void init() {
		Usuario usuarioLogado = commons.getUsuarioLogado();
		if (usuarioLogado.getTipo().equals(TipoUsuario.FUNCIONARIO)) {
			cadastroServicoVM.getServico().setFuncionario((Funcionario) usuarioLogado);
			cadastroServicoVM.setTipoFuncionarioLogado(true);
		} else {
			cadastroServicoVM.setTipoFuncionarioLogado(false);
			funcionarios = funcionarioService.getAll();
		}
		cadastroServicoVM.setNaoExisteCaixaAberto(!caixaService.existeCaixaAberto());
	}

	public void salvar() {
		try {
			Servico servicoSalvo = salvarServico();
			salvarTransacao(servicoSalvo);
			limparCampos();
			MessagesUtils.exibirMensagemSucesso("mensagem.sucesso.salvar.registro");
		} catch (Exception e) {
			log.error(e.toString());
			MessagesUtils.exibirMensagemErro("mensagem.erro.salvar.registro");
		}
	}

	private Servico salvarServico() {
		cadastroServicoVM.getServico().setData(DataUtil.dateToTimeStamp(cadastroServicoVM.getData()));
		return servicoService.salvar(cadastroServicoVM.getServico());
	}
	
	private void salvarTransacao(Servico servico) {
		Transacao transacao = cadastroServicoVM.getTransacao();
		transacao.setCaixa(caixaService.getCaixaAberto());
		transacao.setData(new Date());
		transacao.setTipo(TipoTransacao.RECEITA);
		transacao.setValor(servico.getValor());
		transacaoService.salvar(transacao);
	}

	private void limparCampos() {
		cadastroServicoVM.setServico(new Servico());
		cadastroServicoVM.setData(new Date());
		cadastroServicoVM.setTransacao(new Transacao());
	}

	public CadastroServicoVM getCadastroServicoVM() {
		return cadastroServicoVM;
	}

	public void setCadastroServicoVM(CadastroServicoVM cadastroServicoVM) {
		this.cadastroServicoVM = cadastroServicoVM;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

}

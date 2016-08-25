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
import org.primefaces.event.SelectEvent;

import com.snf.enums.Permissao;
import com.snf.model.Funcionario;
import com.snf.model.Servico;
import com.snf.model.Usuario;
import com.snf.service.CaixaService;
import com.snf.service.FuncionarioService;
import com.snf.service.ServicoService;
import com.snf.util.DataUtil;
import com.snf.util.MessagesUtils;
import com.snf.vm.CadastroServicoVM;

@Named
@ViewScoped
public class CadastroServicoController implements Serializable {

	private static final long serialVersionUID = -5932008545595398278L;

	static final Logger log = Logger.getLogger(CadastroServicoController.class);

	@Inject
	private ServicoService servicoService;

	@Inject
	private CaixaService caixaService;
	
	@Inject
	private FuncionarioService funcionarioService;

	@Inject
	private CadastroServicoVM cadastroServicoVM;

	@Inject
	private CommonsController commons;

	private List<Funcionario> funcionarios = new ArrayList<>();

	@PostConstruct
	public void init() {
		Usuario usuarioLogado = commons.getUsuarioLogado();
		if (usuarioLogado.possuiPermissao(Permissao.FUNCIONARIO)) {
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
			salvarServico();
			limparCampos();
			MessagesUtils.exibirMensagemSucesso("mensagem.sucesso.salvar.registro");
		} catch (Exception e) {
			log.error(e.toString());
			MessagesUtils.exibirMensagemErro("mensagem.erro.salvar.registro");
		}
	}

	private Servico salvarServico() {
		return servicoService.salvarServicoPago(cadastroServicoVM.getServico());
	}
	
	public void atualizarDataFim(SelectEvent event){
		Date data = (Date) event.getObject();
		Date dataFim = DataUtil.somarMinutos(data, 30);
		cadastroServicoVM.getServico().setDataFim(dataFim);
	}

	private void limparCampos() {
		cadastroServicoVM.setServico(new Servico());
		cadastroServicoVM.getServico().setDataInicio(new Date());
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

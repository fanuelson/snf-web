package com.snf.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.snf.enums.TipoUsuario;
import com.snf.model.Funcionario;
import com.snf.service.FuncionarioService;
import com.snf.service.TipoUsuarioService;
import com.snf.util.CollectionsUtils;
import com.snf.util.MessagesUtils;
import com.snf.vm.EdicaoUsuarioVM;

@Named
@ViewScoped
public class ConsultaFuncionarioController implements Serializable {

	private static final long serialVersionUID = -1288711489651556752L;

	static final Logger log = Logger.getLogger(ConsultaFuncionarioController.class);

	@Inject
	private FuncionarioService funcionarioService;
	
	@Inject
	private TipoUsuarioService tipoUsuarioService;

	@Inject
	private EdicaoUsuarioVM edicaoUsuarioVM;

	private List<Funcionario> funcionarios;

	private List<Funcionario> filteredFuncionarios;

	@PostConstruct
	public void init() {
		funcionarios = funcionarioService.getAllFetchRoles();
		edicaoUsuarioVM.setTiposUsuario(tipoUsuarioService.getAll());
	}

	public void remover(Funcionario funcionario) {
		try {
			funcionarioService.remover(funcionario);
			funcionarios.remove(funcionario);
			if (!CollectionsUtils.isNullOrEmpty(filteredFuncionarios))
				filteredFuncionarios.remove(funcionario);
			MessagesUtils.exibirMensagemSucesso("mensagem.sucesso.remover.registro");
		} catch (Exception e) {
			log.error(e.toString());
			MessagesUtils.exibirMensagemErro("mensagem.erro.remover.registro");
		}
	}

	public void prepararEdicao(Funcionario func) {
		edicaoUsuarioVM.setTipoFuncionarioEmEdicao(func.getRoles().get(0).getTipoUsuario());
		edicaoUsuarioVM.setFunc(func);
	}

	public void salvar() {
		try {
			Funcionario func = edicaoUsuarioVM.getFunc();
			preencherTipoUsuario(func);
			func = funcionarioService.salvar(func);
			MessagesUtils.exibirMensagemSucesso("mensagem.sucesso.salvar.registro");
		} catch (Exception e) {
			log.error(e.toString());
			MessagesUtils.exibirMensagemErro("mensagem.erro.salvar.registro");
		}
	}
	
	private void preencherTipoUsuario(Funcionario func) {
		func.getRoles().get(0).setTipoUsuario(edicaoUsuarioVM.getTipoFuncionarioEmEdicao());
		func.setTipo(TipoUsuario.getTipoByRoles(func.getRoles()));
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Funcionario> getFilteredFuncionarios() {
		return filteredFuncionarios;
	}

	public void setFilteredFuncionarios(List<Funcionario> filteredFuncionarios) {
		this.filteredFuncionarios = filteredFuncionarios;
	}

	public EdicaoUsuarioVM getEdicaoUsuarioVM() {
		return edicaoUsuarioVM;
	}

	public void setEdicaoUsuarioVM(EdicaoUsuarioVM edicaoUsuarioVM) {
		this.edicaoUsuarioVM = edicaoUsuarioVM;
	}

}

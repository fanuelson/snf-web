package com.snf.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.snf.library.Encripta;
import com.snf.model.Funcionario;
import com.snf.model.Role;
import com.snf.model.TipoUsuario;
import com.snf.service.FuncionarioService;
import com.snf.service.TipoUsuarioService;
import com.snf.util.MessagesUtils;
import com.snf.vm.CadastroFuncionarioVM;

@Named
@ViewScoped
public class CadastroFuncionarioController implements Serializable {

	private static final long serialVersionUID = -3202126812210206703L;
	
	static final Logger log = Logger.getLogger(CadastroFuncionarioController.class);

	@Inject
	private FuncionarioService funcionarioService;
	
	@Inject
	private CadastroFuncionarioVM cadastroFuncionarioVM;
	
	@Inject
	private TipoUsuarioService tipoUsuarioService;
	
	@Inject
	private Encripta encripta;
	
	private List<TipoUsuario> tiposUsuario;
	
	@PostConstruct
	public void init() {
		tiposUsuario = tipoUsuarioService.getAll();
	}
	
	public void salvar(){
		try{
			if(isSenhasIguais()){
				Funcionario func = cadastroFuncionarioVM.getFuncionario();
				Role role = cadastroFuncionarioVM.getRole();
				preencherDadosUsuario(func, role);
				funcionarioService.salvar(func);
				MessagesUtils.exibirMensagemSucesso("mensagem.sucesso.salvar.registro");
			}else{
				MessagesUtils.exibirMensagemErro("mensagem.senhas.nao.iguais");
			}
			limparCampos();
		} catch (Exception e) {
			log.error(e.toString());
			MessagesUtils.exibirMensagemErro("mensagem.erro.salvar.registro");
		}
	}

	private void preencherDadosUsuario(Funcionario func, Role role) {
		role.setUsuario(func);
		String senha = cadastroFuncionarioVM.getSenha();
		func.getRoles().add(role);
		func.setTipo(com.snf.enums.TipoUsuario.getTipoByRoles(func.getRoles()));
		func.setSenha(encripta.encripta(senha));
	}

	private void limparCampos() {
		cadastroFuncionarioVM.setFuncionario(new Funcionario());
	}

	public CadastroFuncionarioVM getCadastroFuncionarioVM() {
		return cadastroFuncionarioVM;
	}

	public void setCadastroFuncionarioVM(CadastroFuncionarioVM cadastroFuncionarioVM) {
		this.cadastroFuncionarioVM = cadastroFuncionarioVM;
	}
	
	public boolean isSenhasIguais() {
		String senha = cadastroFuncionarioVM.getSenha().trim();
		String senhaNovamente = cadastroFuncionarioVM.getSenhaNovamente().trim();
		return senha.equals(senhaNovamente);
	}

	public List<TipoUsuario> getTiposUsuario() {
		return tiposUsuario;
	}

	public void setTiposUsuario(List<TipoUsuario> tiposUsuario) {
		this.tiposUsuario = tiposUsuario;
	}
	
}

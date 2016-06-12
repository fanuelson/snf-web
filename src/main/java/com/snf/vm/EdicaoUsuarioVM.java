package com.snf.vm;

import java.io.Serializable;
import java.util.List;

import com.snf.model.Funcionario;
import com.snf.model.TipoUsuario;

public class EdicaoUsuarioVM implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Funcionario func;
	
	private List<TipoUsuario> tiposUsuario;
	
	private TipoUsuario tipoFuncionarioEmEdicao;

	public Funcionario getFunc() {
		return func;
	}

	public void setFunc(Funcionario func) {
		this.func = func;
	}

	public List<TipoUsuario> getTiposUsuario() {
		return tiposUsuario;
	}

	public void setTiposUsuario(List<TipoUsuario> tiposUsuario) {
		this.tiposUsuario = tiposUsuario;
	}

	public TipoUsuario getTipoFuncionarioEmEdicao() {
		return tipoFuncionarioEmEdicao;
	}

	public void setTipoFuncionarioEmEdicao(TipoUsuario tipoFuncionarioEmEdicao) {
		this.tipoFuncionarioEmEdicao = tipoFuncionarioEmEdicao;
	}
	
}

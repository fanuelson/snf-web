package com.snf.vm;

import java.io.Serializable;

import com.snf.enums.Permissao;
import com.snf.model.Funcionario;

public class EdicaoUsuarioVM implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Funcionario func;
	
	
	private Permissao tipoFuncionarioEmEdicao;
	
	public Permissao[] getPermissoes() {
		return Permissao.values();
	}

	public Funcionario getFunc() {
		return func;
	}

	public void setFunc(Funcionario func) {
		this.func = func;
	}

	public Permissao getTipoFuncionarioEmEdicao() {
		return tipoFuncionarioEmEdicao;
	}

	public void setTipoFuncionarioEmEdicao(Permissao tipoFuncionarioEmEdicao) {
		this.tipoFuncionarioEmEdicao = tipoFuncionarioEmEdicao;
	}
	
}

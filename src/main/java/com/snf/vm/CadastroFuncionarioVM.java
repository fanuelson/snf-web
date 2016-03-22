package com.snf.vm;

import java.io.Serializable;

import javax.inject.Inject;

import com.snf.model.Funcionario;

public class CadastroFuncionarioVM implements Serializable {

	private static final long serialVersionUID = 4251298147118858755L;

	@Inject
	private Funcionario funcionario;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
}

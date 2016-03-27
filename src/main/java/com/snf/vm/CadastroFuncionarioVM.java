package com.snf.vm;

import java.io.Serializable;

import javax.inject.Inject;

import com.snf.enums.Permissao;
import com.snf.model.Funcionario;
import com.snf.model.Role;

public class CadastroFuncionarioVM implements Serializable {

	private static final long serialVersionUID = 4251298147118858755L;

	@Inject
	private Funcionario funcionario;

	@Inject
	private Role role;
	
	private String senha;

	private String senhaNovamente;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public Permissao[] getPermissoes() {
		return Permissao.values();
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getSenhaNovamente() {
		return senhaNovamente;
	}

	public void setSenhaNovamente(String senhaNovamente) {
		this.senhaNovamente = senhaNovamente;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}

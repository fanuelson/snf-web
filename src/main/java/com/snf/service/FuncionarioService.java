package com.snf.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.snf.dao.FuncionarioDAO;
import com.snf.model.Funcionario;

public class FuncionarioService implements Serializable {

	private static final long serialVersionUID = 2470118587391107194L;

	@Inject
	private FuncionarioDAO funcionarioDAO;
	
	public void salvar(Funcionario funcionario){
		funcionarioDAO.save(funcionario);
	}
	
	public List<Funcionario> getAll(){
		return funcionarioDAO.getAll();
	}
	
	public void remover(Funcionario funcionario){
		funcionarioDAO.delete(funcionario.getId());
	}
	
	public Funcionario getFuncionario(Long id){
		return funcionarioDAO.getById(id);
	}

	public Funcionario getById(Long id) {
		return funcionarioDAO.getById(id);
	}
}

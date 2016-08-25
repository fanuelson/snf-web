package com.snf.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.snf.dao.FuncionarioDAO;
import com.snf.model.Funcionario;

@Stateless
public class FuncionarioService implements Serializable {

	private static final long serialVersionUID = 2470118587391107194L;

	static final Logger log = Logger.getLogger(FuncionarioService.class);

	@Inject
	private FuncionarioDAO funcionarioDAO;

	public Funcionario salvar(Funcionario funcionario) {
		return funcionarioDAO.save(funcionario);
	}

	public List<Funcionario> getAll() {
		return funcionarioDAO.getAll();
	}
	
	public List<Funcionario> getAllFetchRoles() {
		return funcionarioDAO.getAllFetchRoles();
	}

	public void remover(Funcionario funcionario) {
		funcionarioDAO.delete(funcionario.getIdUsuario());
	}

	public Funcionario getFuncionario(Long id) {
		return funcionarioDAO.getById(id);
	}

	public Funcionario getById(Long id) {
		return funcionarioDAO.getById(id);
	}
}

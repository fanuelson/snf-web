package com.snf.dao;

import java.util.ArrayList;
import java.util.List;

import com.snf.builder.JPQLBuilder;
import com.snf.model.Funcionario;

public class FuncionarioDAO extends GenericDAO<Funcionario, Long> {

	private static final long serialVersionUID = -409076693183413948L;
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> getAllFetchRoles() {
		List<Funcionario> funcionarios = new ArrayList<>();
		try{
			funcionarios = new JPQLBuilder()
					.select("f")
					.from(Funcionario.class, "f")
					.innerJoinFetch("f.roles", "r")
					.contruir(getManager())
					.getResultList();
		} catch (Exception e){
			log.error(e.toString());
		}
		return funcionarios;
	}

}

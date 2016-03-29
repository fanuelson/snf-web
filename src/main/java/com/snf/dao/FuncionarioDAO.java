package com.snf.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.snf.builder.JPQLBuilder;
import com.snf.model.Funcionario;

public class FuncionarioDAO extends GenericDAO<Funcionario, Long> {

	private static final long serialVersionUID = -409076693183413948L;
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> getAllFetchRoles() {
		List<Funcionario> funcionarios = new ArrayList<>();
		try{
			JPQLBuilder queryBuilder = new JPQLBuilder()
					.select("f")
					.from(Funcionario.class, "f")
					.innerJoinFetch("f.roles", "r");
			Query query = getManager().createQuery(queryBuilder.contruir());
			funcionarios = query.getResultList();	
		} catch (Exception e){
			log.error(e.toString());
			e.printStackTrace();
		}
		return funcionarios;
	}

}

package com.snf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "idFuncionario")
@Named("func")
public class Funcionario extends Usuario implements Serializable {

	private static final long serialVersionUID = 8422266960584099194L;

	@OneToMany(mappedBy = "funcionario", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = false)
	private List<Servico> servicos = new ArrayList<>();

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

}

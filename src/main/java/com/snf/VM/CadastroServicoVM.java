package com.snf.VM;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.snf.model.Servico;

public class CadastroServicoVM implements Serializable {

	private static final long serialVersionUID = 4301551046874550315L;

	@Inject
	private Servico servico;
	
	private Date data;
	
	@PostConstruct
	public void init(){
		data = new Date();
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
}

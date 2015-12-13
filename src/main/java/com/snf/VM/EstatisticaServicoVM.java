package com.snf.VM;

import java.io.Serializable;
import java.util.Date;

import com.snf.model.Funcionario;

public class EstatisticaServicoVM implements Serializable {

	private static final long serialVersionUID = -836588887077362085L;

	private Funcionario funcionario;
	
	private Date dataInicial;
	
	private Date dataFinal;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
}

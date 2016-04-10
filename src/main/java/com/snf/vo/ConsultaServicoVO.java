package com.snf.vo;

import java.io.Serializable;
import java.util.Date;

import com.snf.model.Funcionario;

public class ConsultaServicoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dataInicial;
	private Date dataFinal;

	private Funcionario funcionario;

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

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}

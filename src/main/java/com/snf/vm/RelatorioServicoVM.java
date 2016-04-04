package com.snf.vm;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;

import com.snf.model.Funcionario;
import com.snf.util.DataUtil;

public class RelatorioServicoVM implements Serializable {

	private static final long serialVersionUID = -836588887077362085L;

	private Funcionario funcionario;

	private Date dataInicial;

	private Date dataFinal;
	
	@PostConstruct
	public void init() {
		dataInicial = DataUtil.diminuirDias(DataUtil.getDataAtualHoraZerada(), 7);
		dataFinal = DataUtil.getDataAtualHoraFinalDia();
	}

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

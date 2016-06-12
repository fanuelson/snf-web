package com.snf.vo;

import java.util.Date;

public class RelatorioServicoVO {

	private Date data;
	private Double valor;
	
	public RelatorioServicoVO(Date data, Double valor) {
		super();
		this.data = data;
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}

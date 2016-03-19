package com.snf.vo;

import java.util.Date;

public class ServicoDataValorVO {

	private Date data;
	private Double valor;
	
	public ServicoDataValorVO(Date data, Double valor) {
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

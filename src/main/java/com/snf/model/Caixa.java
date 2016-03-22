package com.snf.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Caixa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "idCaixa")
	private Long id;

	@Column(name = "dataAbertura")
	private Date dataAbertura;

	@Column(name = "dataFechamento")
	private Date dataFechamento;

	@Column(name = "valorInicial")
	private Double valorInicial;

	@Column(name = "valorAtual")
	private Double valorAtual;
	
	public void abrir(Double valorInicial) {
		this.valorInicial = valorInicial;
		dataAbertura = new Date();
	}
	
	public void fechar() {
		this.dataFechamento = new Date();
	}
	
	public void adicionarValor(Double valor) {
		this.valorAtual += valor;
	}
	
	public boolean isAberto() {
		return dataFechamento==null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Double getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(Double valorInicial) {
		this.valorInicial = valorInicial;
	}

	public Double getValorAtual() {
		return valorAtual;
	}

	public void setValorAtual(Double valorAtual) {
		this.valorAtual = valorAtual;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Caixa other = (Caixa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

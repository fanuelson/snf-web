package com.snf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.snf.util.DataUtil;

@Entity
public class Caixa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "idCaixa")
	private Long idCaixa;

	@Column(name = "dataAbertura")
	private Date dataAbertura;

	@Column(name = "dataFechamento")
	private Date dataFechamento;

	@Column(name = "valorInicial")
	private Double valorInicial;

	@Column(name = "valorAtual")
	private Double valorAtual;

	@OneToMany(mappedBy = "caixa", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
	private List<Transacao> transacoes = new ArrayList<Transacao>();

	public void abrir(Double valorInicial) {
		this.valorInicial = valorInicial;
		dataAbertura = new Date();
	}

	public String getDataAberturaFormatada() {
		return DataUtil.getDataFormatada(dataAbertura, "dd/MM/yyyy HH:mm");
	}

	public String getDataFechamentoFormatado() {
		if (dataFechamento != null)
			return DataUtil.getDataFormatada(dataFechamento, "dd/MM/yyyy HH:mm");
		else
			return "";
	}

	public void fechar() {
		this.dataFechamento = new Date();
	}

	public void adicionarValor(Double valor) {
		this.valorAtual += valor;
	}

	public boolean isAberto() {
		return dataFechamento == null;
	}

	public Long getId() {
		return idCaixa;
	}

	public void setId(Long id) {
		this.idCaixa = id;
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

	public List<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCaixa == null) ? 0 : idCaixa.hashCode());
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
		if (idCaixa == null) {
			if (other.idCaixa != null)
				return false;
		} else if (!idCaixa.equals(other.idCaixa))
			return false;
		return true;
	}

}

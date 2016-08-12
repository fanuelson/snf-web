package com.snf.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.primefaces.model.ScheduleEvent;

import com.snf.enums.StatusServico;

@Entity
public class Servico implements Serializable, ScheduleEvent {

	private static final long serialVersionUID = 8436701701647338073L;

	@Id
	@GeneratedValue
	@Column(name = "idServico")
	private Long idServico;

	@Column(name = "nome")
	private String nome;

	@Column(name = "descricao")
	private String descricao;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "idFuncionario", nullable = true)
	private Funcionario funcionario;

	@Column(name = "valor")
	private Double valor;

	@Column(name = "nomeCliente")
	private String nomeCliente;

	@Column(name = "dataInicio")
	private Date dataInicio;

	@Column(name = "dataFim")
	private Date dataFim;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusServico status;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date data) {
		this.dataInicio = data;
	}

	public Long getIdServico() {
		return idServico;
	}

	public void setIdServico(Long idServico) {
		this.idServico = idServico;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public StatusServico getStatus() {
		return status;
	}

	public void setStatus(StatusServico status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idServico == null) ? 0 : idServico.hashCode());
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
		Servico other = (Servico) obj;
		if (idServico == null) {
			if (other.idServico != null)
				return false;
		} else if (!idServico.equals(other.idServico))
			return false;
		return true;
	}

	public void cancelar() {
		status = StatusServico.CANCELADO;
	}

	public void pagar(Double valor) {
		this.valor = valor;
		status = StatusServico.PAGO;
	}

	public boolean isNaoFoiAgendado() {
		return idServico == null;
	}

	public boolean isPodeSalvar() {
		return idServico != null;
	}

	public boolean isPodePagar() {
		return status != null && status.equals(StatusServico.AGENDADO);
	}

	public boolean isPodeCancelar() {
		return status != null && status.equals(StatusServico.AGENDADO);
	}

	
	@Override
	public String getId() {
		if (this.idServico != null)
			return this.idServico.toString();
		return null;
	}

	
	public void setId(String id) {
		//MÉTODO VAZIO USADO APENAS PARA IMPLEMENTAR SCHEDULE EVENT
	}

	
	@Override
	public Object getData() {
		return null;
	}

	
	@Override
	public String getTitle() {
		return this.nome + "-" + funcionario.getNome();
	}

	
	@Override
	public Date getStartDate() {
		return this.dataInicio;
	}

	
	@Override
	public Date getEndDate() {
		return this.dataFim;
	}

	
	@Override
	public boolean isAllDay() {
		return false;
	}

	
	@Override
	public String getStyleClass() {
		if (status != null){
			return status.getEstiloCssSchedule();
		}
		return null;
	}
	
	
	public String getStyleClassRow() {
		if(status!=null) {
			return status.getEstiloCssRow();
		}
		return null;
	}

	
	@Override
	public boolean isEditable() {
		return true;
	}

	
	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public String toString() {
		return "Servico [idServico=" + idServico + ", nome=" + nome + ", descricao=" + descricao + ", funcionario="
				+ funcionario + ", valor=" + valor + ", nomeCliente=" + nomeCliente + ", dataInicio=" + dataInicio
				+ ", dataFim=" + dataFim + ", status=" + status + "]";
	}

	
}

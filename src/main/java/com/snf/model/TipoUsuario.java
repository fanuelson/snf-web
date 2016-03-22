package com.snf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.snf.enums.Permissao;

@Entity
public class TipoUsuario {
	
	@Id
	@GeneratedValue
	@Column(name="idTipoUsuario")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="nome")
	private Permissao tipo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Permissao getTipo() {
		return tipo;
	}

	public void setTipo(Permissao tipo) {
		this.tipo = tipo;
	}
	
}

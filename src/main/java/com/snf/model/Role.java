package com.snf.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "idRole")
	private Long id;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "idUsuario", nullable = true)
	private Usuario usuario;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "idTipoUsuario", nullable = true)
	private TipoUsuario tipoUsuario;

	public Role() {
	}

	public Role(TipoUsuario permissao) {
		this.tipoUsuario = permissao;
	}

	@Override
	public String getAuthority() {
		return this.tipoUsuario.getTipo().name();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
}

package com.snf.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.snf.enums.Permissao;

@Entity
public class TipoUsuario implements Serializable {
	
	private static final long serialVersionUID = 5217028584900063454L;

	@Id
	@GeneratedValue
	@Column(name="idTipoUsuario")
	private Long idTipoUsuario;
	
	@Enumerated(EnumType.STRING)
	@Column(name="nome")
	private Permissao permissao;

	public Long getId() {
		return idTipoUsuario;
	}

	public void setId(Long id) {
		this.idTipoUsuario = id;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTipoUsuario == null) ? 0 : idTipoUsuario.hashCode());
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
		TipoUsuario other = (TipoUsuario) obj;
		if (idTipoUsuario == null) {
			if (other.idTipoUsuario != null)
				return false;
		} else if (!idTipoUsuario.equals(other.idTipoUsuario))
			return false;
		return true;
	}
	
}

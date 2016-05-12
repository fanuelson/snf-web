package com.snf.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.snf.dao.TransacaoDAO;
import com.snf.model.Transacao;

@Stateless
public class TransacaoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TransacaoDAO transacaoDAO;
	
	public List<Transacao> getAll() {
		return transacaoDAO.getAll();
	}
	
	public Transacao salvar(Transacao transacao) {
		return transacaoDAO.save(transacao);
	}
	
	public boolean remover(Transacao transacao) {
		return transacaoDAO.delete(transacao);
	}
}

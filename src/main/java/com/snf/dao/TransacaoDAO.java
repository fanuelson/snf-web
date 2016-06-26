package com.snf.dao;

import org.apache.log4j.Logger;

import com.snf.genericDao.GenericDAO;
import com.snf.model.Transacao;

public class TransacaoDAO extends GenericDAO<Transacao, Long> {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(TransacaoDAO.class);

}

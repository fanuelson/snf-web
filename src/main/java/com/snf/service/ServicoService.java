package com.snf.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.snf.dao.ServicoDAO;
import com.snf.model.Funcionario;
import com.snf.model.Servico;

public class ServicoService implements Serializable {

	private static final long serialVersionUID = -3854194992056453807L;

	@Inject
	private ServicoDAO servicoDAO;
	
	public void salvar(Servico servico){
		servicoDAO.save(servico);
	}
	
	public List<Servico> getAll(){
		return servicoDAO.getAll();
	}
	
	public void remover(Servico servico){
		servicoDAO.delete(servico.getId());
	}
	
	public Servico getServico(Long id){
		return servicoDAO.getById(id);
	}
	
	public List<Servico> getServicosByPeriodoAndFuncionario(Date dataInicio, Date dataFim, Funcionario funcionario){
		return servicoDAO.getServicosByPeriodoAndFuncionario(dataInicio, dataFim, funcionario);
	}
	
	public List<Object[]> servicosByPeriodoAndFuncionario(Date dataInicial, Date dataFinal, Funcionario funcionario){
		return servicoDAO.servicosByPeriodoAndFuncionario(dataInicial, dataFinal, funcionario);
	}
}

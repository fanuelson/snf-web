package com.snf.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.snf.dao.ServicoDAO;
import com.snf.model.Caixa;
import com.snf.model.Funcionario;
import com.snf.model.Servico;
import com.snf.vo.ServicoDataValorVO;

public class ServicoService implements Serializable {

	private static final long serialVersionUID = -3854194992056453807L;

	final static Logger log = Logger.getLogger(ServicoService.class);

	@Inject
	private ServicoDAO servicoDAO;

	@Inject
	private CaixaService caixaService;

	public void salvar(Servico servico) {
		Caixa caixa = caixaService.getCaixaAberto();
		caixa.adicionarValor(servico.getValor());
		caixaService.salvar(caixa);
		servicoDAO.save(servico);
	}

	public List<Servico> getAll() {
		return servicoDAO.getAll();
	}

	public void remover(Servico servico) {
		servicoDAO.delete(servico.getId());
	}

	public Servico getServico(Long id) {
		return servicoDAO.getById(id);
	}

	public List<Servico> getServicosByPeriodoAndFuncionario(Date dataInicio, Date dataFim, Funcionario funcionario) {
		return servicoDAO.getServicosByPeriodoAndFuncionario(dataInicio, dataFim, funcionario);
	}

	public List<ServicoDataValorVO> servicosByPeriodoAndFuncionario(Date dataInicial, Date dataFinal,
			Funcionario funcionario) {
		return servicoDAO.servicosByPeriodoAndFuncionario(dataInicial, dataFinal, funcionario);
	}
}

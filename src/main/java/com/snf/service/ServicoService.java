package com.snf.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.snf.dao.ServicoDAO;
import com.snf.enums.StatusServico;
import com.snf.enums.TipoTransacao;
import com.snf.lazyModel.PaginaDataModel;
import com.snf.model.Caixa;
import com.snf.model.Servico;
import com.snf.model.Transacao;
import com.snf.vo.FiltroConsultaServicoVO;
import com.snf.vo.RelatorioServicoVO;

public class ServicoService implements Serializable {

	private static final long serialVersionUID = -3854194992056453807L;

	static final Logger log = Logger.getLogger(ServicoService.class);

	@Inject
	private ServicoDAO servicoDAO;

	@Inject
	private CaixaService caixaService;

	@Inject
	private TransacaoService transacaoService;

	public Servico salvarServicoPago(Servico servico) {
		Caixa caixaAberto = caixaService.getCaixaAberto();
		servico.setStatus(StatusServico.PAGO);
		caixaAberto.adicionarValor(servico.getValor());
		Transacao transacao = criarTransacao(servico, caixaAberto);
		transacaoService.salvar(transacao);
		caixaService.salvar(caixaAberto);
		return servicoDAO.save(servico);
	}
	
	public Servico salvar(Servico servico){
		return servicoDAO.save(servico);
	}
	
	public Servico agendar(Servico servico) {
		servico.setStatus(StatusServico.AGENDADO);
		servico.setValor(null);
		return servicoDAO.save(servico);
	}

	public void cancelar(Servico servico) {
		servico.cancelar();
		servicoDAO.save(servico);
	}

	private Transacao criarTransacao(Servico servico, Caixa caixaAberto) {
		Transacao transacao = new Transacao();
		transacao.setCaixa(caixaAberto);
		transacao.setData(new Date());
		transacao.setTipo(TipoTransacao.RECEITA);
		transacao.setValor(servico.getValor());
		return transacao;
	}
	
	public Double getSomaTotalServicos(FiltroConsultaServicoVO filtro){
		return servicoDAO.getSomaTotalServicos(filtro);
	}

	public List<Servico> getAll() {
		return servicoDAO.getAll();
	}

	public void remover(Servico servico) {
		servicoDAO.delete(servico.getIdServico());
	}

	public Servico getServico(Long id) {
		return servicoDAO.getById(id);
	}

	public List<Servico> getServicosByPeriodoAndFuncionario(FiltroConsultaServicoVO filtro) {
		return servicoDAO.getServicosByPeriodoAndFuncionario(filtro);
	}

	public List<RelatorioServicoVO> servicosByPeriodoAndFuncionario(FiltroConsultaServicoVO filtro) {
		return servicoDAO.servicosByPeriodoAndFuncionario(filtro);
	}
	
	public PaginaDataModel<Servico> getServicosByPeriodoAndFuncionario(FiltroConsultaServicoVO filtro, PaginaDataModel<Servico> pagina) {
		return servicoDAO.getServicosByPeriodoAndFuncionario(filtro, pagina);
	}
}

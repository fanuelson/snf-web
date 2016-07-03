package com.snf.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.snf.enums.Permissao;
import com.snf.lazyModel.ServicoLazyDataModel;
import com.snf.model.Funcionario;
import com.snf.model.Servico;
import com.snf.model.Usuario;
import com.snf.service.FuncionarioService;
import com.snf.service.ServicoService;
import com.snf.util.DataUtil;
import com.snf.util.MessagesUtils;
import com.snf.vm.ConsultaServicoVM;
import com.snf.vo.FiltroConsultaServicoVO;

@Named
@ViewScoped
public class ConsultaServicoController implements Serializable {

	private static final long serialVersionUID = -8784362664957105320L;

	static final Logger log = Logger.getLogger(ConsultaServicoController.class);

	@Inject
	private ServicoService servicoService;

	@Inject
	private FuncionarioService funcionarioService;

	@Inject
	private ConsultaServicoVM consultaServicoVM;

	@Inject
	private CommonsController commonsController;

	private List<Funcionario> funcionarios;

	private double valorTotalPesquisa;

	@PostConstruct
	public void init() {
		valorTotalPesquisa = 0;
		Usuario userLogado = commonsController.getUsuarioLogado();
		if (userLogado.possuiPermissao(Permissao.FUNCIONARIO)) {
			consultaServicoVM.setTipoFuncionarioLogado(true);
			consultaServicoVM.getServicos().getFiltro().setFuncionario((Funcionario) userLogado);
			consultaServicoVM.setFuncionario((Funcionario) userLogado);
		} else {
			consultaServicoVM.setTipoFuncionarioLogado(false);
			funcionarios = funcionarioService.getAll();
		}
		calcularSomaTotalPesquisa();
	}

	public void calcularSomaTotalPesquisa() {
		if (periodoPesquisaValido()) {
			FiltroConsultaServicoVO filtro = ((ServicoLazyDataModel) consultaServicoVM.getServicos()).getFiltro();
			valorTotalPesquisa = servicoService.getSomaTotalServicos(filtro);
		} else
			MessagesUtils.exibirMensagemErro("mensagem.erro.pesquisa.periodo");
	}

	public void remover(Servico servico) {
		try {
			servicoService.remover(servico);
			MessagesUtils.exibirMensagemSucesso("mensagem.sucesso.remover.registro");
		} catch (Exception e) {
			log.error(e.toString());
			MessagesUtils.exibirMensagemErro("mensagem.erro.remover.registro");
		}
	}

	private boolean periodoPesquisaValido() {
		Date dataInicialPesquisada = consultaServicoVM.getServicos().getFiltro().getDataInicial();
		Date dataFinalPesquisada = consultaServicoVM.getServicos().getFiltro().getDataFinal();
		if (DataUtil.getDataHoraZerada(dataInicialPesquisada) != null
				&& DataUtil.getDataHoraFinalDia(dataFinalPesquisada) != null) {
			return DataUtil.getDataHoraZerada(dataInicialPesquisada)
					.before(DataUtil.getDataHoraFinalDia(dataFinalPesquisada));
		}
		
		return true;
	}

	public ConsultaServicoVM getConsultaServicoVM() {
		return consultaServicoVM;
	}

	public void setConsultaServicoVM(ConsultaServicoVM consultaServicoVM) {
		this.consultaServicoVM = consultaServicoVM;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public double getValorTotalPesquisa() {
		return valorTotalPesquisa;
	}

	public void setValorTotalPesquisa(double valorTotalPesquisa) {
		this.valorTotalPesquisa = valorTotalPesquisa;
	}

}

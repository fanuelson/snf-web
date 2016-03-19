package com.snf.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.LinearAxis;

import com.snf.VM.EstatisticaServicoVM;
import com.snf.builder.LinearChartModelBuilder;
import com.snf.enums.PosicaoLegenda;
import com.snf.model.Funcionario;
import com.snf.service.FuncionarioService;
import com.snf.service.ServicoService;
import com.snf.util.CollectionsUtils;
import com.snf.util.DataUtil;
import com.snf.util.MessagesUtils;
import com.snf.vo.ServicoDataValorVO;

@Named
@ViewScoped
public class EstatisticaServicoController implements Serializable {

	private static final long serialVersionUID = 8284251730157488128L;

	@Inject
	private ServicoService servicoService;

	@Inject
	private FuncionarioService funcionarioService;

	private LineChartModel animatedModel1;

	@Inject
	private EstatisticaServicoVM estatisticaServicoVM;

	private List<Funcionario> funcionarios;

	private List<ServicoDataValorVO> servicos;

	private double valorTotalPesquisa = 0;

	@PostConstruct
	public void init() {
		funcionarios = funcionarioService.getAll();
		pesquisar();
		calcularValorTotalPesquisa();
	}

	public void pesquisar() {
		if (periodoPesquisaValido()) {
			Date dataInicialPesquisada = estatisticaServicoVM.getDataInicial();
			Date dataFinalPesquisada = estatisticaServicoVM.getDataFinal();
			Funcionario funcionarioPesquisado = estatisticaServicoVM.getFuncionario();
			servicos = servicoService.servicosByPeriodoAndFuncionario(dataInicialPesquisada, dataFinalPesquisada,
					funcionarioPesquisado);
			if (CollectionsUtils.isNullOrEmpty(servicos))
				MessagesUtils.exibirMensagemErro("mensagem.nenhum.registro.encontrado");

			createAnimatedModels();
		} else {
			MessagesUtils.exibirMensagemErro("mensagem.erro.pesquisa.periodo");
		}

	}

	public void calcularValorTotalPesquisa() {
		valorTotalPesquisa = 0;
		for (ServicoDataValorVO servicoVO : servicos) {
			valorTotalPesquisa += servicoVO.getValor();
		}
	}

	private boolean periodoPesquisaValido() {

		if (estatisticaServicoVM.getDataInicial() != null && estatisticaServicoVM.getDataFinal() != null) {
			if (estatisticaServicoVM.getDataInicial().before(estatisticaServicoVM.getDataFinal()))
				return true;
			else
				return false;
		}
		return true;
	}

	private void createAnimatedModels() {
		animatedModel1 = initLinearModel();
	}

	private LineChartModel initLinearModel() {

		LineChartSeries series1 = criarSerie();
		DateAxis xAxis = criarEixoX();
		Axis yAxis = criarEixoY();

		LineChartModel linearChartModel = new LinearChartModelBuilder().comTitulo("Soma Total de Servicos por Dia")
				.comLegendaNaPosicao(PosicaoLegenda.NORTE).animado().comZoom().comEixoX(xAxis).comEixoY(yAxis)
				.adicionarSerie(series1).contruir();

		return linearChartModel;

	}

	private LineChartSeries criarSerie() {
		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Servicos");

		if (servicos == null || servicos.size() <= 0) {
			series1.set(DataUtil.getDataFormatada(DataUtil.diminuirDias(new Date(), 2), "dd/MM/yyyy"), 0);
			series1.set(DataUtil.diminuirDias(new Date(), 0).toString().substring(0, 10), 0);
		} else {
			for (ServicoDataValorVO servicoVO : servicos) {
				series1.set(DataUtil.getDataFormatada(servicoVO.getData(), "yyyy-MM-dd"), servicoVO.getValor());
			}
		}
		return series1;
	}

	private DateAxis criarEixoX() {
		DateAxis xAxis = new DateAxis("Datas");
		xAxis.setTickAngle(-30);
		xAxis.setTickFormat("%#d/%b/%y");
		return xAxis;
	}

	private Axis criarEixoY() {
		Axis yAxis = new LinearAxis();
		yAxis.setTickFormat("%.2f");
		yAxis.setLabel("Valores");
		return yAxis;
	}

	public LineChartModel getAnimatedModel1() {
		return animatedModel1;
	}

	public void setAnimatedModel1(LineChartModel animatedModel1) {
		this.animatedModel1 = animatedModel1;
	}

	public EstatisticaServicoVM getEstatisticaServicoVM() {
		return estatisticaServicoVM;
	}

	public void setEstatisticaServicoVM(EstatisticaServicoVM estatisticaServicoVM) {
		this.estatisticaServicoVM = estatisticaServicoVM;
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

}

package com.snf.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.LinearAxis;

import com.snf.builder.LinearChartModelBuilder;
import com.snf.enums.PosicaoLegenda;
import com.snf.model.Funcionario;
import com.snf.service.FuncionarioService;
import com.snf.service.ServicoService;
import com.snf.util.CollectionsUtils;
import com.snf.util.DataUtil;
import com.snf.util.MessagesUtils;
import com.snf.vm.RelatorioServicoVM;
import com.snf.vo.ServicoDataValorVO;

@Named
@ViewScoped
public class RelatorioServicoController implements Serializable {

	private static final long serialVersionUID = 8284251730157488128L;

	static final Logger log = Logger.getLogger(RelatorioServicoController.class);

	private static final String formato_data_americano = "yyyy-MM-dd";

	@Inject
	private ServicoService servicoService;

	@Inject
	private FuncionarioService funcionarioService;

	private LineChartModel animatedModel1;

	@Inject
	private RelatorioServicoVM relatorioServicoVM;

	private List<Funcionario> funcionarios;

	private List<ServicoDataValorVO> servicos;

	private double valorTotalPesquisa = 0;

	private double valorMaxEixoY = 0;

	@PostConstruct
	public void init() {
		funcionarios = funcionarioService.getAll();
		pesquisar();
		calcularValorTotalPesquisa();
		calcularValorMaxEixoY();
	}

	public void pesquisar() {
		Date dataInicialPesquisada = relatorioServicoVM.getDataInicial();
		Date dataFinalPesquisada = relatorioServicoVM.getDataFinal();
		relatorioServicoVM.setDataInicial(DataUtil.getDataHoraZerada(dataInicialPesquisada));
		relatorioServicoVM.setDataFinal(DataUtil.getDataHoraFinalDia(dataFinalPesquisada));
		if (periodoPesquisaValido()) {
			Funcionario funcionarioPesquisado = relatorioServicoVM.getFuncionario();
			servicos = servicoService.servicosByPeriodoAndFuncionario(dataInicialPesquisada, dataFinalPesquisada,
					funcionarioPesquisado);
			if (CollectionsUtils.isNullOrEmpty(servicos))
				MessagesUtils.exibirMensagemErro("mensagem.nenhum.registro.encontrado");
			calcularValorTotalPesquisa();
			calcularValorMaxEixoY();
		} else {
			servicos = null;
			MessagesUtils.exibirMensagemErro("mensagem.erro.pesquisa.periodo");
		}
		createAnimatedModels();

	}

	public void calcularValorTotalPesquisa() {
		valorTotalPesquisa = 0;
		for (ServicoDataValorVO servicoVO : servicos) {
			valorTotalPesquisa += servicoVO.getValor();
		}
	}

	public void calcularValorMaxEixoY() {
		Double maiorValor = 0.0;
		for (ServicoDataValorVO servicoVO : servicos) {
			if (servicoVO.getValor() > maiorValor) {
				maiorValor = servicoVO.getValor();
			}
		}
		valorMaxEixoY = maiorValor + 100;
	}

	private boolean periodoPesquisaValido() {
		if (relatorioServicoVM.getDataInicial() != null && relatorioServicoVM.getDataFinal() != null) {
			return relatorioServicoVM.getDataInicial().before(relatorioServicoVM.getDataFinal());
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

		return new LinearChartModelBuilder().comTitulo("Soma Total de Servicos por Dia")
				.comLegendaNaPosicao(PosicaoLegenda.NORTE).animado().comZoom().comEixoX(xAxis).comEixoY(yAxis)
				.adicionarSerie(series1).contruir();
	}

	private LineChartSeries criarSerie() {
		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Servicos");

		if (CollectionsUtils.isNullOrEmpty(servicos)) {
			series1.set(DataUtil.getDataFormatada(DataUtil.diminuirDias(new Date(), 2), formato_data_americano), 0);
			series1.set(DataUtil.getDataFormatada(new Date(), formato_data_americano), 0);
		} else {
			for (ServicoDataValorVO servicoVO : servicos) {
				series1.set(DataUtil.getDataFormatada(servicoVO.getData(), formato_data_americano),
						servicoVO.getValor());
			}
		}
		return series1;
	}

	private DateAxis criarEixoX() {
		DateAxis xAxis = new DateAxis("Datas");
		xAxis.setTickAngle(-30);
		xAxis.setTickFormat("%#d/%m/%y");
		return xAxis;
	}

	private Axis criarEixoY() {
		Axis yAxis = new LinearAxis();
		yAxis.setTickFormat("%.2f");
		yAxis.setLabel("Valores");
		yAxis.setMax(valorMaxEixoY);
		yAxis.setMin(0);
		return yAxis;
	}

	public LineChartModel getAnimatedModel1() {
		return animatedModel1;
	}

	public void setAnimatedModel1(LineChartModel animatedModel1) {
		this.animatedModel1 = animatedModel1;
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

	public RelatorioServicoVM getRelatorioServicoVM() {
		return relatorioServicoVM;
	}

	public void setRelatorioServicoVM(RelatorioServicoVM relatorioServicoVM) {
		this.relatorioServicoVM = relatorioServicoVM;
	}

}

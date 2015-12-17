package com.snf.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import com.snf.VM.EstatisticaServicoVM;
import com.snf.model.Funcionario;
import com.snf.service.FuncionarioService;
import com.snf.service.ServicoService;
import com.snf.util.MessagesUtils;

@Named
@ViewScoped
public class EstatisticaServicoController implements Serializable {

	private static final long serialVersionUID = 8284251730157488128L;
	
	private static final int INDEX_VALOR_CONSULTA = 7;
	private static final int INDEX_DATA_CONSULTA = 6;

	@Inject
	private ServicoService servicoService;
	
	@Inject
	private FuncionarioService funcionarioService;

	private LineChartModel animatedModel1;
	
	@Inject
	private EstatisticaServicoVM estatisticaServicoVM;
	
	private List<Funcionario> funcionarios;

	private List<Object[]> servicos;
	
	private double valorTotalPesquisa = 0;
	
	@PostConstruct
	public void init() {
		funcionarios = funcionarioService.getAll();
		servicos = new ArrayList<Object[]>();
		pesquisar();
		calcularValorTotalPesquisa();
	}
	
	public void pesquisar(){
		if(periodoPesquisaValido()){
			Date dataInicial = estatisticaServicoVM.getDataInicial();
			Date dataFinal = estatisticaServicoVM.getDataFinal();
			Funcionario funcionario = estatisticaServicoVM.getFuncionario();
			servicos = servicoService.servicosByPeriodoAndFuncionario(dataInicial, dataFinal, funcionario);
			if(servicos.isEmpty())
				MessagesUtils.exibirMensagemErro("mensagem.nenhum.registro.encontrado");
			else
				createAnimatedModels();
		}else{
			MessagesUtils.exibirMensagemErro("mensagem.erro.pesquisa.periodo");
		}
		
	}
	
	public void calcularValorTotalPesquisa(){
		valorTotalPesquisa = 0;
		for (Object[] servico : servicos) {
			valorTotalPesquisa+=Double.parseDouble(servico[INDEX_VALOR_CONSULTA].toString());
		}
	}
	
	private boolean periodoPesquisaValido(){
		 if(estatisticaServicoVM.getDataInicial()!=null && estatisticaServicoVM.getDataFinal()!=null){
			 if(estatisticaServicoVM.getDataInicial().before(estatisticaServicoVM.getDataFinal()))
				return true;
			 else
				return false; 
		 }
		 return true;
	}
	

	private void createAnimatedModels() {
		animatedModel1 = initLinearModel();
		animatedModel1.setTitle("Soma Total de Servicos por Dia");
		animatedModel1.setAnimate(true);
		animatedModel1.setLegendPosition("n");

		Axis yAxis = animatedModel1.getAxis(AxisType.Y);
		yAxis.setTickFormat("%.2f");

	}

	private LineChartModel initLinearModel() {
		LineChartModel model = new LineChartModel();

		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Servicos");
	       
		if (servicos.size() <= 0)
			return model;

		for (Object[] ob : servicos) {
			series1.set(ob[INDEX_DATA_CONSULTA].toString().substring(0, 10), Double.parseDouble(ob[INDEX_VALOR_CONSULTA].toString()));
		}

		model.setZoom(true);
		model.getAxis(AxisType.Y).setLabel("Valores");

		DateAxis axis = new DateAxis("Datas");
		axis.setTickAngle(-30);
		axis.setTickFormat("%#d/%#m/%y");

		model.getAxes().put(AxisType.X, axis);

		model.addSeries(series1);

		return model;

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
	
	public double getValorTotalPesquisa(){
		return valorTotalPesquisa;
	}
	
}

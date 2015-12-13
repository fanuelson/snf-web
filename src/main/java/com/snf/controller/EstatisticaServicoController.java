package com.snf.controller;

import java.io.Serializable;
import java.sql.Timestamp;
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
import com.snf.util.DataUtil;

@Named
@ViewScoped
public class EstatisticaServicoController implements Serializable {

	private static final long serialVersionUID = 8284251730157488128L;
	
	private static final int INDEX_VALOR_CONSULTA = 4;
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

	@PostConstruct
	public void init() {
		funcionarios = funcionarioService.getAll();
		servicos = new ArrayList<Object[]>();
		createAnimatedModels();
	}
	
	public void pesquisar(){
		Date dataInicial = estatisticaServicoVM.getDataInicial();
		Date dataFinal = estatisticaServicoVM.getDataFinal();
		Funcionario funcionario = estatisticaServicoVM.getFuncionario();
		servicos = servicoService.servicosByPeriodoAndFuncionario(dataInicial, dataFinal, funcionario);
		createAnimatedModels();
		
	}
	

	private void createAnimatedModels() {
		animatedModel1 = initLinearModel();
		animatedModel1.setTitle("Soma Total de Servicos por Dia");
		animatedModel1.setAnimate(true);
		animatedModel1.setLegendPosition("se");

		Axis yAxis = animatedModel1.getAxis(AxisType.Y);
		yAxis.setMin(0.0);
		yAxis.setMax(getMaior() + 50);

	}

	private LineChartModel initLinearModel() {
		LineChartModel model = new LineChartModel();

		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Servicos");

		if (servicos!=null && servicos.size() <= 0)
			return model;

		for (Object[] ob : servicos) {
			series1.set(ob[INDEX_DATA_CONSULTA].toString().substring(0, 10), Double.parseDouble(ob[INDEX_VALOR_CONSULTA].toString()));
		}

		model.setZoom(true);
		model.getAxis(AxisType.Y).setLabel("Valores");

		DateAxis axis = new DateAxis("Datas");
		axis.setTickAngle(-30);
		Date d = DataUtil.somarDias(new Date(), 1);
		Timestamp ts = new Timestamp(d.getTime());
		axis.setMax(ts.toString().substring(0, 10));
		axis.setTickFormat("%#d/%#m/%y");

		model.getAxes().put(AxisType.X, axis);

		model.addSeries(series1);

		return model;

	}

	private double getMaior() {
		if (servicos.size() <= 0)
			return 0;
		double maior = Double.parseDouble(servicos.get(0)[INDEX_VALOR_CONSULTA].toString());
		for (Object[] ob : servicos) {
			if (Double.parseDouble(ob[INDEX_VALOR_CONSULTA].toString()) > maior) {
				maior = Double.parseDouble(ob[INDEX_VALOR_CONSULTA].toString());
			}
		}

		return maior;
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
	
}

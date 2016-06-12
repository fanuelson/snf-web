package com.snf.builder;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import com.snf.enums.PosicaoLegenda;

public class LinearChartModelBuilder {

	private LineChartModel animatedModel;
	private List<LineChartSeries> series;

	public LinearChartModelBuilder() {
		this.animatedModel = new LineChartModel();
		this.series = new ArrayList<>();
	}

	public LinearChartModelBuilder comTitulo(String titulo) {
		this.animatedModel.setTitle(titulo);
		return this;
	}

	public LinearChartModelBuilder animado() {
		this.animatedModel.setAnimate(true);
		return this;
	}

	public LinearChartModelBuilder comZoom() {
		this.animatedModel.setZoom(true);
		return this;
	}

	public LinearChartModelBuilder comLegendaNaPosicao(PosicaoLegenda posicaoLegenda) {
		this.animatedModel.setLegendPosition(posicaoLegenda.getCodigo());
		return this;
	}

	public LinearChartModelBuilder comEixoX(Axis xAxis) {
		this.animatedModel.getAxes().put(AxisType.X, xAxis);
		return this;
	}

	public LinearChartModelBuilder comEixoY(Axis yAxis) {
		this.animatedModel.getAxes().put(AxisType.Y, yAxis);
		return this;
	}

	public LinearChartModelBuilder adicionarSerie(LineChartSeries serie) {
		this.series.add(serie);
		return this;
	}
	
	public LineChartModel contruir() {
		for(LineChartSeries serie : series) {
			this.animatedModel.addSeries(serie);
		}
		return this.animatedModel;
	}
}

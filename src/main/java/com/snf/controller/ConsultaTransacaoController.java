package com.snf.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;

import com.snf.model.Caixa;
import com.snf.model.Servico;
import com.snf.model.Transacao;
import com.snf.service.CaixaService;
import com.snf.vm.ConsultaTransacaoVM;

@Named
@ViewScoped
public class ConsultaTransacaoController implements Serializable {

	private static final long serialVersionUID = -8784362664957105320L;

	static final Logger log = Logger.getLogger(ConsultaTransacaoController.class);

	@Inject
	private CaixaService caixaService;

	@Inject
	private ConsultaTransacaoVM consultaTransacaoVM;

	@PostConstruct
	public void init() {
		consultaTransacaoVM.setCaixas(caixaService.getAllOrderByDataAberturaFetchTransacoes());
	}

	public void selecionar(SelectEvent event) {
		Caixa caixaSelecionado = (Caixa) event.getObject();
		consultaTransacaoVM.setCaixaSelecionado(caixaSelecionado);
	}

	public void pesquisar() {

	}

	public void remover(Servico servico) {

	}
	
	public String getValorTotalCaixas() {
		Double valorTotal = 0.0;
		for (Caixa caixa : consultaTransacaoVM.getCaixas()) {
			valorTotal += (caixa.getValorAtual() - caixa.getValorInicial());
		}
		return formatarDouble(valorTotal);
	}
	
	public String getValorTotalTransacoes() {
		Double valorTotal = 0.0;
		for (Transacao transacao : consultaTransacaoVM.getCaixaSelecionado().getTransacoes()) {
			valorTotal += transacao.getValor();
		}
		return formatarDouble(valorTotal);
	}
	
	private String formatarDouble(Double numero) {
		String valorFormatado = String.valueOf(numero) ;
		String naoDecimal = valorFormatado.split("\\.")[0] ;
		String naoDecimalFormatado = "" ;
		String decimal = valorFormatado.split("\\.")[1];
		naoDecimal = naoDecimal.replace(",", ".");
		int aux = 0;
		for(int i = naoDecimal.length() - 1; i >= 0 ; i--){
			if(aux==3){
				naoDecimalFormatado += ".";
				aux=-1;
			}
			naoDecimalFormatado += naoDecimal.charAt(i);
			aux++;
		}
		naoDecimal = new StringBuffer(naoDecimalFormatado).reverse().toString();
		if(decimal.length()>1)
			decimal = decimal.substring(0, 2);
		else
			decimal = decimal.substring(0, 1) + "0";
		return "R$ "+naoDecimal+","+decimal;
	}

	public ConsultaTransacaoVM getConsultaTransacaoVM() {
		return consultaTransacaoVM;
	}

	public void setConsultaTransacaoVM(ConsultaTransacaoVM consultaTransacaoVM) {
		this.consultaTransacaoVM = consultaTransacaoVM;
	}

}

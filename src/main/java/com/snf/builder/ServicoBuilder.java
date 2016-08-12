package com.snf.builder;

import java.util.Date;

import com.snf.model.Servico;

public class ServicoBuilder {
	
	private Servico instancia;
	
	public ServicoBuilder(Servico servico) {
		instancia = servico;
	}
	
	public ServicoBuilder() {
		instancia = new Servico();
	}
	
	public ServicoBuilder comDataInicio(Date dataInicio) {
		instancia.setDataInicio(dataInicio);
		return this;
	}
	
	public ServicoBuilder comDataFim(Date dataFim) {
		instancia.setDataFim(dataFim);
		return this;
	}
	
	public Servico contruir(){
		return this.instancia;
	}

}

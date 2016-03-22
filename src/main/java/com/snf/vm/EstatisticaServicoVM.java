package com.snf.vm;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;

import com.snf.model.Funcionario;
import com.snf.util.DataUtil;

public class EstatisticaServicoVM implements Serializable {

	//private static final int UM_DIA = 1;

	private static final int QT_DIAS_SEMANA = 7;

	private static final long serialVersionUID = -836588887077362085L;

	private Funcionario funcionario;
	
	private Date dataInicial;
	
	private Date dataFinal;
	
	@PostConstruct
	public void init(){
		//dataFinal = DataUtil.getDateTimeHoraZerada().plusDays(UM_DIA).toDate();
		dataFinal = DataUtil.getDataAtualHoraFinalDia();
		//dataInicial = DataUtil.getDateTimeHoraZerada().minusDays(QT_DIAS_SEMANA).toDate();
		dataInicial = DataUtil.diminuirDias(new Date(), QT_DIAS_SEMANA);
		System.out.println(dataInicial.toString());
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
}

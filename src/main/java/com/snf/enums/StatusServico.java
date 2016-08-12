package com.snf.enums;

public enum StatusServico {
	CANCELADO("servicoCanceladoSchedule","servicoCanceladoRow"),
	AGENDADO("servicoAgendadoSchedule","servicoAgendadoRow"),
	PAGO("servicoPagoSchedule","servicoPagoRow");
	
	private String estiloCssSchedule;
	private String estiloCssRow;
	
	private StatusServico(String estiloCssSchedule, String estiloCssRow) {
		this.estiloCssSchedule = estiloCssSchedule;
		this.estiloCssRow = estiloCssRow;
		
	}

	public String getEstiloCssSchedule() {
		return estiloCssSchedule;
	}

	public void setEstiloCssSchedule(String estiloCss) {
		this.estiloCssSchedule = estiloCss;
	}

	public String getEstiloCssRow() {
		return estiloCssRow;
	}

	public void setEstiloCssRow(String estiloCssRow) {
		this.estiloCssRow = estiloCssRow;
	}
 
}

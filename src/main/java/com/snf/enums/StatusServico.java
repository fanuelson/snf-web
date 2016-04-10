package com.snf.enums;

public enum StatusServico {
	CANCELADO("servicoCancelado"),
	AGENDADO("servicoAgendado"),
	PAGO("servicoPago");
	
	private String estiloCss;
	
	private StatusServico(String estilo) {
		estiloCss = estilo;
	}

	public String getEstiloCss() {
		return estiloCss;
	}

	public void setEstiloCss(String estiloCss) {
		this.estiloCss = estiloCss;
	}
 
}

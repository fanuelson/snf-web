package com.snf.enums;

public enum PosicaoLegenda {

	NORTE("n"), 
	SUL("s"),
	LESTE("e"),
	OESTE("w"),
	NORTE_OESTE("nw"),
	NORTE_LESTE("ne"), 
	SUL_OESTE("sw"),
	SUL_LESTE("se");
	
	private String codigo;

	private PosicaoLegenda(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}

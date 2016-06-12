package com.snf.library;

public interface Encriptador {
	
	String encriptar(String senha);
	boolean checkPassword(String plainPass, String encryptPass);

}

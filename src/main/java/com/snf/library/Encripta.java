package com.snf.library;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.springframework.stereotype.Component;

@Component
public class Encripta {
	
	private static final String ALGORITHM_ENCRYPTOR = "SHA-1";

	public String encripta(String senha) {
		try {
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm(ALGORITHM_ENCRYPTOR);
		passwordEncryptor.setPlainDigest(true);
		return passwordEncryptor.encryptPassword(senha);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

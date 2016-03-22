package com.snf.library;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class Encripta {
	
	public String encripta(String senha, String crypt) {
//		try {
//			AbstractChecksum checksum = null;
//			checksum = JacksumAPI.getChecksumInstance(crypt);
//			checksum.update(senha.getBytes());
//			return checksum.getFormattedValue();
			return senha;
//		} catch (NoSuchAlgorithmException ns) {
//			ns.printStackTrace();
//			return null;
//		}
	}

	public String encripta(String senha) {
		return encripta(senha, "whirlpool-1");
	}

}

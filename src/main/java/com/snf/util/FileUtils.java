package com.snf.util;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;

public class FileUtils implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String getMediaType(File file) {
		try{
			Tika tika = new Tika();
			return tika.detect(file);
		}catch(IOException io) {
			return null;
		}
	}
	
	public static String removerExtensao(String fileName) {
		if(fileName==null)
			return null;
		return FilenameUtils.removeExtension(fileName);
	}
	
	public static String getExtensao(String fileName) {
		if(fileName==null) {
			return null;
		}
		
		return FilenameUtils.getExtension(fileName);
	}
	
	public static boolean isNullOrDontExist(File file) {
		return file==null ? true : !file.exists();
	}
}

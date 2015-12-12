package com.restaurante.util;

import java.io.IOException;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class FacesUtils {

	public static ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	public static FacesContext getContext() {
		return FacesContext.getCurrentInstance();
	}

	public static Map<String, Object> getSessionMap() {
		return getExternalContext().getSessionMap();
	}

	public static void addSessionMap(String key, Object value) {
		getSessionMap().put(key, value);
	}

	public static void removeSessionMap(String key) {
		getSessionMap().remove(key);
	}
	
	public static Object getValueSessionMap(String key) {
    	return getSessionMap().get(key);
    }
	
	public static void redirect(String retorno) throws IOException {
        getExternalContext().redirect(retorno);
    }
	
	public static void invalidateSession(){
		getExternalContext().invalidateSession();
	}
}

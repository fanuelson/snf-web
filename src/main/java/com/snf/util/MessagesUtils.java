package com.snf.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessagesUtils {

	private static final String MESSAGES_FILENAME = "messages";
	private static ResourceBundle resourceBundle;

	private MessagesUtils() {
	}

	private static ResourceBundle getResourceBundle() {
		if (resourceBundle == null) {
			resourceBundle = ResourceBundle.getBundle(MESSAGES_FILENAME, new Locale("pt-br"));
		}

		return resourceBundle;
	}

	public static String getMessage(String key, Object... parametros) {
		return MessageFormat.format(getResourceBundle().getString(key), parametros);
	}

	public static void exibirMensagemSucesso(String keyMessage, Object... parametros) {
		exibirMensagem(FacesMessage.SEVERITY_INFO, keyMessage, parametros);
	}

	public static void exibirMensagemAlerta(String keyMessage, Object... parametros) {
		exibirMensagem(FacesMessage.SEVERITY_WARN, keyMessage, parametros);
	}

	public static void exibirMensagemErro(String keyMessage, Object... parametros) {
		exibirMensagem(FacesMessage.SEVERITY_ERROR, keyMessage, parametros);
	}
	
	public static FacesMessage createMensagemSucesso(String keyMessage, Object... parametros) {
		return createMensagem(FacesMessage.SEVERITY_INFO, keyMessage, parametros);
	}
	
	public static FacesMessage createMensagemAlerta(String keyMessage, Object... parametros) {
		return createMensagem(FacesMessage.SEVERITY_WARN, keyMessage, parametros);
	}
	
	public static FacesMessage createMensagemErro(String keyMessage, Object... parametros) {
		return createMensagem(FacesMessage.SEVERITY_ERROR, keyMessage, parametros);
	}

	public static void exibirMensagem(FacesMessage.Severity severity, String mensagem1, String mensagem2) {
		FacesMessage facesMessage = new FacesMessage(severity, mensagem1, mensagem2);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public static FacesMessage createMensagem(FacesMessage.Severity severity, String mensagem1, String mensagem2) {
		FacesMessage facesMessage = new FacesMessage(severity, mensagem1, mensagem2);
		return facesMessage;
	}

	private static void exibirMensagem(FacesMessage.Severity severity, String keyMessage, Object... parametros) {
		String mensagem = getMessage(keyMessage, parametros);
		exibirMensagem(severity, mensagem, "");
	}
	
	private static FacesMessage createMensagem(FacesMessage.Severity severity, String keyMessage, Object... parametros) {
		String mensagem = getMessage(keyMessage, parametros);
		return createMensagem(severity, mensagem, "");
	}

}

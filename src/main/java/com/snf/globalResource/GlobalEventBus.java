package com.snf.globalResource;

import java.io.Serializable;

import javax.faces.application.FacesMessage;

import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

public class GlobalEventBus implements Serializable {

	private static final long serialVersionUID = 1L;

	public static void notificarMensagem(String path, FacesMessage mensagem) {
		EventBus eventBus = EventBusFactory.getDefault().eventBus();
		eventBus.publish(path, mensagem);
	}

}

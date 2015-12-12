package com.restaurante.util;

import javax.faces.application.FacesMessage;

import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

public class EventoGlobalUtils {
	
	public static void exibirMensagemGlobal(String key,Object... parametros){
		EventBus eventBus = EventBusFactory.getDefault().eventBus();
		eventBus.publish("/counter", new FacesMessage(MessagesUtils.getMessage(key, parametros)));
	}
}

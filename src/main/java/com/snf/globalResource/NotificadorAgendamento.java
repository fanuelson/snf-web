package com.snf.globalResource;

import javax.faces.application.FacesMessage;

import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;

@PushEndpoint("/notificacaoAgendamento")
public class NotificadorAgendamento {

	@OnMessage(encoders = {JSONEncoder.class})
    public FacesMessage onMessage(FacesMessage message) {
        return message;
    }
}

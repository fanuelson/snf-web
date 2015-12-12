package com.snf.resources;

import java.io.Serializable;

import javax.faces.application.FacesMessage;

import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;
 
@PushEndpoint("/counter2")
public class UpdateResource implements Serializable {
 
	private static final long serialVersionUID = -3907706144091103838L;

	@OnMessage(encoders = {JSONEncoder.class})
    public FacesMessage onMessage(FacesMessage count) {
        return count;
    }
}

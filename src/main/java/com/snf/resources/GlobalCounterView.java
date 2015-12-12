package com.snf.resources;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;

import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
 
@Named
@ApplicationScoped
public class GlobalCounterView implements Serializable{
 
	private static final long serialVersionUID = 1L;
	
	private int count;
 
    public int getCount() {
        return count;
    }
 
    public void setCount(int count) {
        this.count = count;
    }
     
    public void increment() {
        count++;
         
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
		eventBus.publish("/counter2", new FacesMessage("oi","oi"));
    }
    
    public void teste(){
    	System.out.println("oioi");
    	count++;
    }
}

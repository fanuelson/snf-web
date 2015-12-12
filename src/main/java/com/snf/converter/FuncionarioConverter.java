package com.snf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.snf.model.Funcionario;
import com.snf.service.FuncionarioService;

@FacesConverter(forClass=Funcionario.class, value="funcionarioConverter")
public class FuncionarioConverter implements Converter{
	
	@Inject
	private FuncionarioService funcionarioService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {                

        if(value == null || value.isEmpty()){
            return null;
        }else{
        	try{
        		Long id = Long.parseLong(value);
                Funcionario unidade = funcionarioService.getById(id);
                return unidade;
        	}catch(Exception e){
        		return null;
        	}
            
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        Funcionario unidade = (Funcionario)value;
        if(unidade != null){
            return String.valueOf(unidade.getId());
        }else{
            return null;
        }
    }

}
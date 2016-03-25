package com.snf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.snf.model.Funcionario;
import com.snf.service.FuncionarioService;

@FacesConverter(forClass = Funcionario.class, value = "funcionarioConverter")
public class FuncionarioConverter implements Converter {

	static final Logger log = Logger.getLogger(FuncionarioConverter.class);

	@Inject
	private FuncionarioService funcionarioService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		if (value == null || value.isEmpty()) {
			return null;
		} else {
			try {
				Long id = Long.parseLong(value);
				return funcionarioService.getById(id);
			} catch (Exception e) {
				return null;
			}

		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Funcionario unidade = (Funcionario) value;
		if (unidade != null) {
			return String.valueOf(unidade.getId());
		} else {
			return null;
		}
	}

}
package com.snf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.snf.model.TipoUsuario;
import com.snf.service.TipoUsuarioService;

@FacesConverter(forClass = TipoUsuario.class, value = "tipoUsuarioConverter")
public class TipoUsuarioConverter implements Converter {

	static final Logger log = Logger.getLogger(TipoUsuarioConverter.class);

	@Inject
	private TipoUsuarioService tipoUsuarioService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		if (value == null || value.isEmpty()) {
			return null;
		} else {
			try {
				Long id = Long.parseLong(value);
				return tipoUsuarioService.getById(id);
			} catch (Exception e) {
				return null;
			}

		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		TipoUsuario unidade = (TipoUsuario) value;
		if (unidade != null) {
			return String.valueOf(unidade.getId());
		} else {
			return null;
		}
	}

}
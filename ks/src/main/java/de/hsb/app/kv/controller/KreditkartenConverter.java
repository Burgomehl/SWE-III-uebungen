package de.hsb.app.kv.controller;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "kreditkartenConverter")
public class KreditkartenConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value == null)
			return null;
		String s = value;
		if (value.contains(" ")) {
			s = value.replace(" ", "");
		}
		if (value.contains("-")) {
			s = value.replace("-", "");
		}
		return s;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return (String)arg2;
	}

}

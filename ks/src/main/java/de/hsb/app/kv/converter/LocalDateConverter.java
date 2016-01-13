package de.hsb.app.kv.converter;

import java.time.LocalDate;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("de.hsb.app.kv.converter.LocalDateConverter")
public class LocalDateConverter implements Converter{

	public LocalDateConverter() {
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		int jahr = Integer.valueOf(value.substring(0, 4));
		int monat = Integer.valueOf(value.substring(5, 7));
		int tag = Integer.valueOf(value.substring(8, 10));
		
		return LocalDate.of(jahr, monat, tag);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value.toString();
	}

}

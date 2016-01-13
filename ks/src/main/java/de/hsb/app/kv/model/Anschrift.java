package de.hsb.app.kv.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Anschrift implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	@Size(min=3, max=30)
	private String strasse;
	private String plz;
	@Size(min=3, max=30)
	private String ort;

	public Anschrift(String strasse, String plz, String ort) {
		this.strasse = strasse;
		this.plz = plz;
		this.ort = ort;
	}

	public Anschrift() {
	}
	
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	public String getPlz() {
		return plz;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
}

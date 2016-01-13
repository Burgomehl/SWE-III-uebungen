package de.hsb.app.kv.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQuery(name="SelectKunden", query="Select k from Kunde k")
@Entity
public class Kunde implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Anrede anrede;
	private String vorname;
	private String nachname;
	@Temporal(TemporalType.DATE)
	private Date geburtsdatum;
	@OneToOne(cascade = CascadeType.ALL)
	private Kreditkarte kreditkarte;
	@OneToOne(cascade = CascadeType.ALL)
	private Anschrift anschrift;

	public Anschrift getAnschrift() {
		return anschrift;
	}

	public void setAnschrift(Anschrift anschrift) {
		this.anschrift = anschrift;
	}

	public Kunde() {
	}

	public Kunde(Anrede anrede, String vorname, String nachname, Date geburtsdatum, Anschrift anschrift) {
		super();
		this.anrede = anrede;
		this.vorname = vorname;
		this.nachname = nachname;
		this.geburtsdatum = geburtsdatum;
		this.anschrift = anschrift;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public Date getGeburtsdatum() {
		return geburtsdatum;
	}

	public void setGeburtsdatum(Date geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	public Anrede getAnrede() {
		return anrede;
	}

	public void setAnrede(Anrede anrede) {
		this.anrede = anrede;
	}

	public Kreditkarte getKreditkarte() {
		return kreditkarte;
	}

	public void setKreditkarte(Kreditkarte kreditkarte) {
		this.kreditkarte = kreditkarte;
	}

}

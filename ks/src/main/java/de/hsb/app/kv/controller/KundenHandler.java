package de.hsb.app.kv.controller;

import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import de.hsb.app.kv.model.Anrede;
import de.hsb.app.kv.model.Anschrift;
import de.hsb.app.kv.model.Kreditkarte;
import de.hsb.app.kv.model.Kreditkartentyp;
import de.hsb.app.kv.model.Kunde;

@ManagedBean(name = "kundenHandler")
@SessionScoped
public class KundenHandler {
	
	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private UserTransaction utx;
	
	private DataModel<Kunde> kunden;
	private Kunde merkeKunde = new Kunde();
	private Kreditkarte merkeKreditkarte;
	private Anschrift merkeAnschrift;


	@PostConstruct
	public void init() {
		merkeKreditkarte = new Kreditkarte();
		try {
			utx.begin();
			em.persist(new Kunde(Anrede.HERR, "Hugo", "Hermann", new GregorianCalendar(1970, 0, 1).getTime(),new Anschrift("Rutenbergstr","27568","Bremerhaven")));
			em.persist(new Kunde(Anrede.HERR, "Willi", "Meier", new GregorianCalendar(1960, 1, 2).getTime(),new Anschrift("Rutenbergstr","27568","Bremerhaven")));
			em.persist(new Kunde(Anrede.HERR, "Alan", "Turing", new GregorianCalendar(1912, 5, 23).getTime(),new Anschrift("Rutenbergstr","27568","Bremerhaven")));
			em.persist(new Kunde(Anrede.FRAU, "Donald", "Knuth", new GregorianCalendar(1938, 0, 10).getTime(),new Anschrift("Rutenbergstr","27568","Bremerhaven")));
			em.persist(new Kunde(Anrede.HERR, "Edsger W.", "Dijkstra", new GregorianCalendar(1930, 4, 30).getTime(),new Anschrift("Rutenbergstr","27568","Bremerhaven")));
			
			kunden = new ListDataModel<>();
			kunden.setWrappedData(em.createNamedQuery("SelectKunden").getResultList());
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public KundenHandler() {
	}
	
	public DataModel<Kunde> getKunden() {
		return kunden;
	}

	public void setKunden(DataModel<Kunde> kunden) {
		this.kunden = kunden;
	}

	public Kunde getMerkeKunde() {
		return merkeKunde;
	}

	public void setMerkeKunde(Kunde merkeKunde) {
		this.merkeKunde = merkeKunde;
	}
	
	public Kreditkarte getMerkeKreditkarte() {
		return merkeKreditkarte;
	}

	public void setMerkeKreditkarte(Kreditkarte merkeKreditkarte) {
		this.merkeKreditkarte = merkeKreditkarte;
	}

	public String neu(){
		merkeKunde = new Kunde();
		return "neuerKunde?faces-redirect=true";
	}
	
	public String neueAnschrift(){
		merkeKunde = kunden.getRowData();
		merkeAnschrift = merkeKunde.getAnschrift()==null? new Anschrift():merkeKunde.getAnschrift();
		return "neueAdresse?faces-redirect=true";
	}
	
	public String speichern() {
		try {
			utx.begin();
			merkeKunde = em.merge(merkeKunde);
			em.persist(merkeKunde);
			kunden.setWrappedData(em.createNamedQuery("SelectKunden").getResultList());
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "alleKunden?faces-redirect=true";
	}
	
	public String kreditkarteSpeichern() {
		merkeKunde.setKreditkarte(merkeKreditkarte);
		try {
			utx.begin();
			merkeKunde = em.merge(merkeKunde);
			merkeKreditkarte = em.merge(merkeKreditkarte);
			em.persist(merkeKunde);
			em.persist(merkeKreditkarte);
			kunden.setWrappedData(em.createNamedQuery("SelectKunden").getResultList());
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "alleKunden?faces-redirect=true";
	}
	
	public String adresseSpeichern(){
		merkeKunde.setAnschrift(merkeAnschrift);
		try{
			utx.begin();
			merkeKunde = em.merge(merkeKunde);
			merkeAnschrift = em.merge(merkeAnschrift);
			em.persist(merkeKunde);
			em.persist(merkeAnschrift);
			kunden.setWrappedData(em.createNamedQuery("SelectKunden").getResultList());
			utx.commit();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "alleKunden?faces-redirect=true";
	}
	
	public String delete(){
		try {
			utx.begin();
			merkeKunde = kunden.getRowData();
			merkeKunde = em.merge(merkeKunde);
			em.remove(merkeKunde);
			kunden.setWrappedData(em.createNamedQuery("SelectKunden").getResultList());
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "alleKunden?faces-redirect=true";
	}
	
	public String edit(){
		merkeKunde = kunden.getRowData();
		return "neuerKunde?faces-redirect=true";
	}
	
	public String kreditkarte(){
		merkeKunde = kunden.getRowData();
		merkeKreditkarte = merkeKunde.getKreditkarte() != null ?
							merkeKunde.getKreditkarte() :
							new Kreditkarte();
		return "kreditkarte?faces-redirect=true";
	}
	
	public String abbrechen(){
		return "alleKunden?faces-redirect=true";
	}

	public Anrede[] getAnredeValues(){
		return Anrede.values();
	}
	
	public Kreditkartentyp[] getKreditkartentypValues(){
		return Kreditkartentyp.values();
	}

	public Anschrift getMerkeAnschrift() {
		return merkeAnschrift;
	}

	public void setMerkeAnschrift(Anschrift merkeAnschrift) {
		this.merkeAnschrift = merkeAnschrift;
	}
}

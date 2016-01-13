package de.hsb.app.kv.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Kunde.class)
public abstract class Kunde_ {

	public static volatile SingularAttribute<Kunde, Date> geburtsdatum;
	public static volatile SingularAttribute<Kunde, String> vorname;
	public static volatile SingularAttribute<Kunde, Anrede> anrede;
	public static volatile SingularAttribute<Kunde, String> nachname;
	public static volatile SingularAttribute<Kunde, Anschrift> anschrift;
	public static volatile SingularAttribute<Kunde, Integer> id;
	public static volatile SingularAttribute<Kunde, Kreditkarte> kreditkarte;

}


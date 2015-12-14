package Entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Personel.class)
public abstract class Personel_ {

	public static volatile SingularAttribute<Personel, String> pTelefon;
	public static volatile SingularAttribute<Personel, String> pAdi;
	public static volatile SingularAttribute<Personel, String> pSoyadi;
	public static volatile SingularAttribute<Personel, String> pMail;
	public static volatile SingularAttribute<Personel, Integer> personelID;
	public static volatile SingularAttribute<Personel, String> pKulAdi;
	public static volatile SingularAttribute<Personel, String> pAdres;
	public static volatile SingularAttribute<Personel, String> pCep;
	public static volatile SingularAttribute<Personel, Integer> mudurID;
	public static volatile SingularAttribute<Personel, String> pSifre;
	public static volatile SingularAttribute<Personel, Date> dogumTarihi;
	public static volatile SingularAttribute<Personel, Date> tarih;
	public static volatile SingularAttribute<Personel, Short> konum;
	public static volatile SingularAttribute<Personel, Date> baslamaTarihi;

}


package Entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Sepet.class)
public abstract class Sepet_ {

	public static volatile SingularAttribute<Sepet, Boolean> durum;
	public static volatile SingularAttribute<Sepet, Date> tarih;
	public static volatile SingularAttribute<Sepet, Integer> urunID;
	public static volatile SingularAttribute<Sepet, BigDecimal> urunFiyat;
	public static volatile SingularAttribute<Sepet, Integer> sepetID;
	public static volatile SingularAttribute<Sepet, String> refKodu;
	public static volatile SingularAttribute<Sepet, Integer> personelID;
	public static volatile SingularAttribute<Sepet, Short> adet;

}


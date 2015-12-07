package urunGiris;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Urunler.class)
public abstract class Urunler_ {

	public static volatile SingularAttribute<Urunler, Integer> renkID;
	public static volatile SingularAttribute<Urunler, String> barkodNo;
	public static volatile SingularAttribute<Urunler, String> uKisaAciklama;
	public static volatile SingularAttribute<Urunler, Integer> sezonID;
	public static volatile SingularAttribute<Urunler, Integer> urunID;
	public static volatile SingularAttribute<Urunler, String> uResim;
	public static volatile SingularAttribute<Urunler, BigDecimal> uFiyat;
	public static volatile SingularAttribute<Urunler, String> uAdi;
	public static volatile SingularAttribute<Urunler, Integer> bedenID;
	public static volatile SingularAttribute<Urunler, Integer> superID;
	public static volatile SingularAttribute<Urunler, Date> uTarih;

}


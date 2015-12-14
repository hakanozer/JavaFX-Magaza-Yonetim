package Entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Satis.class)
public abstract class Satis_ {

	public static volatile SingularAttribute<Satis, String> sepetRefKodu;
	public static volatile SingularAttribute<Satis, BigDecimal> fiyat;
	public static volatile SingularAttribute<Satis, Short> odemeTipi;
	public static volatile SingularAttribute<Satis, Date> sTarih;
	public static volatile SingularAttribute<Satis, Integer> personelID;
	public static volatile SingularAttribute<Satis, Integer> satisID;

}


package stokblue;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Stoklar.class)
public abstract class Stoklar_ {

	public static volatile SingularAttribute<Stoklar, Date> tarih;
	public static volatile SingularAttribute<Stoklar, Integer> urunID;
	public static volatile SingularAttribute<Stoklar, Short> kalanAdet;
	public static volatile SingularAttribute<Stoklar, Integer> superID;
	public static volatile SingularAttribute<Stoklar, Integer> stokID;
	public static volatile SingularAttribute<Stoklar, Short> gelenAdet;

}


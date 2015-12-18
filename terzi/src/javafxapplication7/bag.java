package javafxapplication7;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USer
 */
public class bag {
    
    SessionFactory ssf;
    Session s;
    Transaction ts;
    
    public void baglantiac(){
        ssf=new Configuration().configure().buildSessionFactory();
        s=ssf.openSession();
        ts=s.beginTransaction();
        
        
    }
    public void ekle(Object entity){
        baglantiac();
        try {
            s.save(entity);
            ts.commit();
        } catch (Exception e) {
            ts.rollback();
        }
        s.close();
        
    }
    public void güncelle(Object entity){
        baglantiac();
        try {
            s.update(entity);
            ts.commit();
        } catch (Exception e) {
            ts.rollback();
        }
        s.close();
    }
    
}

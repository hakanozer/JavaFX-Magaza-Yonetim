 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oldu;

import java.util.List;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import net.sf.ehcache.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class bag {
    
    

 SessionFactory ssf ;
 Session s ;
    Transaction ts ;

    public void baglantiAc(){
        
        ssf = new Configuration().configure().buildSessionFactory();
        s=ssf.openSession();
        ts=s.beginTransaction();
        
        
        
    }
    
    public void ekle(Object entity) {
        
        
        baglantiAc();
 
    try {
        s.save(entity);
        ts.commit();
 
    } catch (Exception ex) {
        ts.rollback();
    }
    s.close();
}
    public void sil(Object entity) {
      baglantiAc();
      try {
          s.delete(entity);
          ts.commit();
      } catch (Exception ex) {
          ts.rollback();
      } 
          s.close();     
    }
          public void guncelle(Object entity) {
        baglantiAc();
        try {
            s.update(entity);
            ts.commit();
        } catch (Exception ex) {
            ts.rollback();
        } 
            s.close();
}
 
          public void topla (){
              
              
        
          }
      


 
  
}
    
           
  
    
    
        
    
         
        


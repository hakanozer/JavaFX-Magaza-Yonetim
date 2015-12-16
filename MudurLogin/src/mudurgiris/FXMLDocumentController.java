/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mudurgiris;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.security.auth.login.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Ahmet KAZĞIN
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private TextField kuladi;
    @FXML
    private PasswordField sifre; 
    @FXML
    private ComboBox durumCmbBox;
    
    @FXML
    private void giris(ActionEvent event) throws IOException{
        Boolean durum=false;
        try {
            String muduradi=kuladi.getText();
            String mudursifre=sifreleme(sifre.getText().toString());
            if (muduradi.equals("")) {
                
                Alert alert = new Alert(Alert.AlertType.WARNING, "Lütfen kullanıcı adını giriniz.", ButtonType.OK );
                alert.setHeaderText(null);
                alert.setTitle("Uyarı");
                alert.showAndWait();
                kuladi.requestFocus();
                durum=true;
                
            }
            else if(sifre.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.WARNING, "Lütfen sifreyi giriniz.", ButtonType.OK );
                alert.setHeaderText(null);
                alert.setTitle("Uyarı");
                alert.showAndWait();
                sifre.requestFocus();
                durum=true;
            }
            else{
            SessionFactory sf = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            Session sess = sf.openSession();
            Transaction ts = sess.beginTransaction();

            Query qee = sess.createQuery("from Mudurler");
            Iterator ite = qee.iterate();
            
            while (ite.hasNext()) {                
            Mudurler obj = (Mudurler) ite.next();
            String kuladi=obj.getMKuladi();
            String sifre=obj.getMSifre();
                
            if(muduradi.equals(kuladi)&mudursifre.equals(sifre) ){
                               Stage stage=new Stage();
               Parent root = FXMLLoader.load(getClass().getResource("yonlendir.fxml"));
               stage.setScene(new Scene(root));
               stage.show();
                
//                Integer mID = obj.getMID();
//
//                Parent root = null;
//                Stage stage = new Stage();
//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("yonlendir.fxml"));
//                root = fxmlLoader.load();
//                yonlendirController cont = fxmlLoader.getController();
//                cont.getRefCode(mID);
//                stage.setScene(new Scene(root));
//                stage.show();

                

               durum=true;
//             System.out.println("kuladi: "+kuladi);
//                System.out.println("sifre : "+sifre);
                
            }
                                     
            }
            if(durum == false){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Lütfen kullanıcı adı ve şifreyi dogru giriniz", ButtonType.OK );
                alert.setHeaderText(null);
                alert.setTitle("Uyarı");
                alert.showAndWait();
                kuladi.setText("");
                sifre.setText("");
                kuladi.requestFocus();
            }
            ts.commit();
            sess.close();
        }
        }catch (HibernateException he) {
            System.out.println(he);
        }
        
    
    
    
    }
    public String sifreleme(String sifre){
    
    String md5sifre = "";
    try{
        MessageDigest messageDigestNesnesi = MessageDigest.getInstance("MD5");
        messageDigestNesnesi.update(sifre.getBytes());
        byte messageDigestDizisi[] = messageDigestNesnesi.digest();
        StringBuffer sb16 = new StringBuffer();
        
        for (int i = 0; i < messageDigestDizisi.length; i++) {
        sb16.append(Integer.toString((messageDigestDizisi[i] & 0xff) + 0x100, 16).substring(1));
        
 }
     md5sifre=sb16.toString();
 System.out.println("Parolanın Şifrelenmiş Hali:(16) " + md5sifre);
 
    }catch(Exception ex){
        System.err.println(ex);
    }
        
    return md5sifre;
    
    }        
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

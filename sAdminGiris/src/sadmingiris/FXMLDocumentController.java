
package sadmingiris;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class FXMLDocumentController implements Initializable {
    
    
    
    @FXML
    private TextField kuladi;
    @FXML
    private PasswordField sifre; 
    @FXML
    private Label label;
    
    
    @FXML
    private void giris(ActionEvent event) throws IOException, NoSuchAlgorithmException{
            Boolean durum = false;

        try {
            String sAdminAdi = kuladi.getText();
            String sAdminSifre = sifre.getText();
            
            if (sAdminAdi.equals("")) {
                
                Alert alert = new Alert(Alert.AlertType.WARNING, "Lütfen Kullanıcı Adını Giriniz.", ButtonType.OK );
                alert.setHeaderText(null);
                alert.setTitle("Uyarı");
                alert.showAndWait();
                kuladi.requestFocus();
                durum = true;
                
            }
            else if(sAdminSifre.equals("")){
                
                Alert alert = new Alert(Alert.AlertType.WARNING, "Lütfen Şifreyi Giriniz.", ButtonType.OK );
                alert.setHeaderText(null);
                alert.setTitle("Uyarı");
                alert.showAndWait();
                sifre.requestFocus();
                durum = true;
                
            }
            
            SessionFactory sf = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            Session sess = sf.openSession();
            Transaction ts = sess.beginTransaction();

            Query qee = sess.createQuery("from SuperAdmin");
            Iterator ite = qee.iterate();
            
            while (ite.hasNext()) {         
                
            SuperAdmin obj = (SuperAdmin) ite.next();
            String kuladi = obj.getKulAdi();
            String sifre = obj.getSifre();
            
                
            if(sAdminAdi.equals(kuladi)&sAdminSifre.equals(sifre)) {
                
                 
                
                Integer sID = obj.getSuperID();
//                label.setText(sID.toString());
                
                
               Parent root = null;
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("yonlendir.fxml"));
        root = fxmlLoader.load();
        yonlendirController cont = fxmlLoader.getController();
        cont.getRefCode(sID);
        stage.setScene(new Scene(root));
        stage.show();
               durum = true;
            }
                                     
            }
            
            if(durum == false){

            Alert alert = new Alert(Alert.AlertType.WARNING, "Hatalı Kullanıcı Adı veya Şifre", ButtonType.OK );
                alert.setHeaderText(null);
                alert.setTitle("Uyarı");
                alert.showAndWait();
                kuladi.setText("");
                sifre.setText("");
                kuladi.requestFocus();
            }
            
            ts.commit();
            sess.close();
            
        } catch (HibernateException he) {
            System.out.println(he);
        }
        
       

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        
}
    


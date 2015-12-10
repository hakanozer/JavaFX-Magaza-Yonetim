
package SatisEkrani;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class SatisGirisController implements Initializable {

    
    @FXML
    private TextField txtKulAdi;
    @FXML
    private TextField txtSifre;
    @FXML private Button btnClick;
    
    @FXML
    private void giris(ActionEvent event) throws IOException{
    Boolean durum = false;
       
        try {
            String personelAdi = txtKulAdi.getText();
            String personelSifre = txtSifre.getText();
            checkTexts(personelAdi, personelSifre);

            SessionFactory sf = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            Session sess = sf.openSession();
            Transaction ts = sess.beginTransaction();

            Query qee = sess.createQuery("from Personel");
            Iterator ite = qee.iterate();

            while (ite.hasNext()) {
                Personel obj = (Personel) ite.next();
                String kuladi = obj.getPKulAdi();
                String sifre = obj.getPSifre();
                int id = obj.getPersonelID();
                String adi = obj.getPAdi();
                String soyadi = obj.getPSoyadi();
                
                if (personelAdi.equals(kuladi) && personelSifre.equals(sifre)) {
//                    Stage stage = new Stage();
//                    Parent root = null;
//                    FXMLLoader fxmlLoader  = FXMLLoader.load(getClass().getResource("satisEkrani.fxml"));     
//                    root = fxmlLoader.load();
//                    SatisController sc = fxmlLoader.getController();
//                    sc.setPer(obj);
//                      stage.setScene(new Scene(root));
//                 stage.show();
//                    closeWindow();
//                   
                          Parent root = null;
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("satisEkrani.fxml"));
        root = fxmlLoader.load();
        SatisController cont = fxmlLoader.getController();
        cont.setPer(obj);
        stage.setScene(new Scene(root));
        stage.show();
                    durum = true;

                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Kullanıcı Adı veya Şifre Hatalı", ButtonType.OK);
                    alert.setHeaderText(null);
                    alert.setTitle("Uyarı");
                    alert.showAndWait();
                }
            }
            sf.close();

        } catch (Exception e) {
            System.out.println("Giriş Hatası : " + e);
        }
        
    }    

    private void closeWindow() {
        Stage st = (Stage) btnClick.getScene().getWindow();
        st.close();
    }

    private void checkTexts(String personelAdi, String personelSifre) {
        Boolean durum;
        if (personelAdi.equals("")) {
            
            Alert alert = new Alert(Alert.AlertType.WARNING, "Lütfen kullanıcı adını giriniz.", ButtonType.OK);
            alert.setHeaderText(null);
            alert.setTitle("Uyarı");
            alert.showAndWait();
            txtKulAdi.requestFocus();
            durum = true;
            
        } else if (personelSifre.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Lütfen sifreyi giriniz.", ButtonType.OK);
            alert.setHeaderText(null);
            alert.setTitle("Uyarı");
            alert.showAndWait();
            txtSifre.requestFocus();
            durum = true;
        }
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
}

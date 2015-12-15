
package SatisEkrani;

import Entities.Personel;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class SatisGirisController implements Initializable {

    @FXML
    private TextField txtKulAdi;
    @FXML
    private TextField txtSifre;
    @FXML
    private Button btnClick;
    @FXML
    private Label lblBackground;
    @FXML
    private Label lblPersonel;



    @FXML
    private void giris(ActionEvent event) throws IOException {


        
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

                    Parent root = null;
                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("satisEkrani.fxml"));
                    root = fxmlLoader.load();
                    SatisController cont = fxmlLoader.getController();
                    cont.setPer(obj);
                    stage.setScene(new Scene(root));

                    closeWindow();
                    stage.show();
                    durum = true;

                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Kullanıcı Adı veya Şifre Hatalı", ButtonType.OK);
                    alert.setHeaderText(null);
                    alert.setTitle("Uyarı");
                    alert.showAndWait();
                }
            }
            sf.close();
//            stages.close();
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
        lblBackground.setGraphic(new ImageView("images/MAVI.png"));
   
    }


}

package subelergiris;

import hibernetVeritabani.NewHibernateUtil;
import hibernetVeritabani.Subeler;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import static subelergiris.YeniSubeController.isValidEmailAddress;

public class SubeDuzenleController implements Initializable {

    private Integer subeid = null;
    private Integer superid = null;
    protected static boolean maildurumu = false;
    protected static boolean icerikDegisimi = false;
    protected Integer getSubeid() {
        return subeid;
    }

    protected void setSubeid(Integer subeid) {
        this.subeid = subeid;
    }

    protected Integer getSuperid() {
        return superid;
    }
private static boolean btDurumu =false;
    protected void setSuperid(Integer superid) {
        this.superid = superid;
    }
    @FXML
    private Button bt_duzSubeEkle;
    @FXML
    private Button bt_duzVazgec;
    @FXML
    protected TextField tf_duzBaslik;
    @FXML
    protected TextField tf_duzTelefon;
    @FXML
    protected TextField tf_duzMail;
    @FXML
    protected DatePicker dp_duzTarih;
    @FXML
    protected TextArea ta_duzAdres;

    @FXML
    public void mailKontrol(KeyEvent evt) {
        if (isValidEmailAddress(tf_duzMail.getText())|| tf_duzMail.getText().equals("")) {
            maildurumu = true;
            System.out.println("mail "+maildurumu);
        } else {
            maildurumu = false;
            System.out.println("mail "+maildurumu);
        }
        kontrol(evt);
          btDurumuChanged();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        girisDegerleri();
    }

    @FXML
    private void kaydetBasildi(ActionEvent evt) throws IOException {
        if (maildurumu  && icerikDegisimi &&gecerliTel(tf_duzTelefon.getText().trim())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("KAYIT İŞLEMİ");
            alert.setHeaderText("KAYDEDİLSİN Mİ?");
            alert.setContentText("Şube adı: " + tf_duzBaslik.getText() + "\n Adres :" + ta_duzAdres.getText() + " \n Mail : " + tf_duzMail.getText() + "\n Telefon : " + tf_duzTelefon.getText());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                SessionFactory sf = NewHibernateUtil.getSessionFactory();
                Session session = sf.openSession();
                Subeler sube = new Subeler();
                sube.setSubeID(getSubeid());
                sube.setSuperID(getSuperid());
                sube.setSAdres(ta_duzAdres.getText());
                sube.setSBaslik(tf_duzBaslik.getText());
                sube.setSMail(tf_duzMail.getText());
                sube.setSTelefon(tf_duzTelefon.getText());
                sube.setSTarih(Date.from(dp_duzTarih.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                Transaction ts = session.beginTransaction();
                session.update(sube);
                ts.commit();
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("KAYIT İŞLEMİ");
                alert2.setHeaderText("KAYIT BAŞARILI !!!");
                alert2.showAndWait();
                Parent root = FXMLLoader.load(getClass().getResource("SubelerGiris.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                ((Node) evt.getSource()).getScene().getWindow().hide();
            }
        } else {
            evt.consume();
        }
    }

    private void subeyeDon() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SubelerGiris.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void vazgecBasildi(ActionEvent evt) throws IOException {
        if (icerikDegisimi) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("GÜNCELLEME UYARISI");
            alert.setHeaderText("KAYDEDİLMEYEN DEĞİŞİKLİKLER VAR.YİNEDE ÇIKILSIN MI ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                subeyeDon();
                ((Node) evt.getSource()).getScene().getWindow().hide();
            } else {
                evt.consume();
            }
        } else {
            subeyeDon();
            ((Node) evt.getSource()).getScene().getWindow().hide();
        }
    }
private void btDurumuChanged(){
    if (maildurumu==true&&icerikDegisimi==true&&gecerliTel(SubeDegisim.sonTelefon)==true){
        bt_duzSubeEkle.setDisable(false);
    }
    else{
        bt_duzSubeEkle.setDisable(true);
    }
}
@FXML 
private void dpDegisim(Event evt){
     if (icerikDegistimi(SubeDegisim.ilkBaslik, SubeDegisim.ilkAdres, SubeDegisim.ilkTelefon, SubeDegisim.ilkMail, SubeDegisim.ilkTarih)) {
            icerikDegisimi = true;
        } else {
            icerikDegisimi = false;
        }
       btDurumuChanged();
}
    @FXML
    private void kontrol(KeyEvent evt) {
        if (icerikDegistimi(SubeDegisim.ilkBaslik, SubeDegisim.ilkAdres, SubeDegisim.ilkTelefon, SubeDegisim.ilkMail, SubeDegisim.ilkTarih)) {
            icerikDegisimi = true;
        } else {
            icerikDegisimi = false;
        }
       
        btDurumuChanged();
    }
     private static boolean gecerliTel(String telNo) {
    if (telNo == null)
        return false;
    for (char c : telNo.toCharArray())
        if (c < '0' || c > '9')
            return false;
      return true;
     }
    protected boolean icerikDegistimi(String baslik, String adres, String telefon, String mail, Date tarih) {
        SubeDegisim.sonBaslik = (tf_duzBaslik.getText());
        SubeDegisim.sonAdres = (ta_duzAdres.getText());
        SubeDegisim.sonTelefon = (tf_duzTelefon.getText());
        SubeDegisim.sonMail = (tf_duzMail.getText());
        SubeDegisim.sonTarih=java.sql.Date.valueOf(dp_duzTarih.getValue());
        if (!(baslik.equals(SubeDegisim.sonBaslik)) || !(adres.equals(SubeDegisim.sonAdres)) || !(telefon.equals(SubeDegisim.sonTelefon)) || !(mail.equals(SubeDegisim.sonMail))||!tarih.equals(SubeDegisim.sonTarih)) {    
            return true;
        } else {
             
            return false;
        }
    }

    private void girisDegerleri() {
        setSubeid(SubelerGirisController.getSecilenID());
        setSuperid(SubelerGirisController.getsID());
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query query = session.createQuery("from Subeler where subeID =" + getSubeid());
        List<Subeler> list = query.list();
        SubeDegisim.ilkBaslik = (list.get(0).getSBaslik());
        SubeDegisim.ilkAdres = (list.get(0).getSAdres());
        SubeDegisim.ilkTelefon = (list.get(0).getSTelefon());
        SubeDegisim.ilkMail = (list.get(0).getSMail());
        SubeDegisim.ilkTarih = list.get(0).getSTarih();
        tf_duzBaslik.setText(SubeDegisim.ilkBaslik);
        tf_duzMail.setText(SubeDegisim.ilkMail);
        tf_duzTelefon.setText(SubeDegisim.ilkTelefon);
        ta_duzAdres.setText(SubeDegisim.ilkAdres);
    GregorianCalendar gregorianCalendar = (GregorianCalendar) Calendar.getInstance();
    gregorianCalendar.setTime(SubeDegisim.ilkTarih);
    ZonedDateTime zonedDateTime = gregorianCalendar.toZonedDateTime();
    dp_duzTarih.setValue(zonedDateTime.toLocalDate());
     SubeDegisim.ilkLTarih=dp_duzTarih.getValue();
     maildurumu=isValidEmailAddress(SubeDegisim.ilkMail);
bt_duzSubeEkle.setDisable(true);
    }

}

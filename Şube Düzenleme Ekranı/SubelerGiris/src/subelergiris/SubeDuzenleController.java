package subelergiris;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import static subelergiris.YeniSubeController.isValidEmailAddress;

public class SubeDuzenleController implements Initializable {
    
    private Integer subeid = null;
    private Integer superid = null;
    protected static boolean maildurumu = isValidEmailAddress(SubeDegisim.ilkMail);
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

    protected void setSuperid(Integer superid) {
        this.superid = superid;
    }
    @FXML
    Button bt_deneme;
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
        if (isValidEmailAddress(tf_duzMail.getText())) {
            maildurumu = true;
            System.out.println(maildurumu);
        } else {
            maildurumu = false;
            System.out.println(maildurumu);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        girisDegerleri();
        System.out.println("mail durumu" + maildurumu);
        System.out.println("içerik durumu" + icerikDegisimi);
    }
    @FXML
    private void kaydetBasildi(ActionEvent evt) throws IOException {
        if(maildurumu||tf_duzMail.getText().equals("")||icerikDegisimi){
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
            System.out.println("başlık " + tf_duzBaslik.getText());
            System.out.println("adres" + ta_duzAdres.getText());
            // sube.setSTarih(new Date());
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
private void subeyeDon() throws IOException{
    Parent root = FXMLLoader.load(getClass().getResource("SubelerGiris.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
       stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            SessionFactory sf = NewHibernateUtil.getSessionFactory();
            Session session = sf.openSession();
            session.close();
            sf.close();
        }
    });
}
    @FXML
    private void vazgecBasildi(ActionEvent evt) throws IOException {
        if(icerikDegisimi){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("GÜNCELLEME UYARISI");
        alert.setHeaderText("KAYDEDİLMEYEN DEĞİŞİKLİKLER VAR.YİNEDE ÇIKILSIN MI ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        subeyeDon();
        ((Node) evt.getSource()).getScene().getWindow().hide();
        }
        else{
            evt.consume();
        }
        }else{
            subeyeDon();
        ((Node) evt.getSource()).getScene().getWindow().hide();
        }
    }
    @FXML
    private void deneme(ActionEvent evt) {
        System.out.println("butona tıklandı");
        System.out.println(icerikDegistimi(SubeDegisim.ilkBaslik, SubeDegisim.ilkAdres, SubeDegisim.ilkTelefon, SubeDegisim.ilkMail));
    }
    @FXML
    private void kontrol(KeyEvent evt) {
        if (icerikDegistimi(SubeDegisim.ilkBaslik, SubeDegisim.ilkAdres, SubeDegisim.ilkTelefon, SubeDegisim.ilkMail)) {
            icerikDegisimi = true;           
            System.out.println(icerikDegisimi);
        } else {
            icerikDegisimi = false;
            System.out.println(icerikDegisimi);
                      
        }
                     bt_duzSubeEkle.setDisable(!icerikDegisimi);
    }

    protected boolean icerikDegistimi(String baslik, String adres, String telefon, String mail) {
        SubeDegisim.sonBaslik = (tf_duzBaslik.getText());
        SubeDegisim.sonAdres = (ta_duzAdres.getText());
        SubeDegisim.sonTelefon = (tf_duzTelefon.getText());
        SubeDegisim.sonMail = (tf_duzMail.getText());
        //setSonTarih(dp_duzTarih.getAccessibleText());
        if (!(baslik.equals(SubeDegisim.sonBaslik)) || !(adres.equals(SubeDegisim.sonAdres)) || !(telefon.equals(SubeDegisim.sonTelefon)) || !(mail.equals(SubeDegisim.sonMail))) {
            return true;
        } else {
            return false;
        }
    }

    private void girisDegerleri() {
        setSubeid(SubelerGirisController.getSecilenID());
        setSuperid(SubelerGirisController.getsID());
        System.out.println("tıklanan elemanın id si " + getSubeid());
        System.out.println("tıklanan elemanın süper id si" + getSuperid());
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query query = session.createQuery("from Subeler where subeID =" + getSubeid());
        List<Subeler> list = query.list();
        SubeDegisim.ilkBaslik = (list.get(0).getSBaslik());
        SubeDegisim.ilkAdres = (list.get(0).getSAdres());
        SubeDegisim.ilkTelefon = (list.get(0).getSTelefon());
        SubeDegisim.ilkMail = (list.get(0).getSMail());
        //ilkTarih=list.get(0).getSTarih().toString();
        tf_duzBaslik.setText(SubeDegisim.ilkBaslik);
        tf_duzMail.setText(SubeDegisim.ilkMail);
        tf_duzTelefon.setText(SubeDegisim.ilkTelefon);
        ta_duzAdres.setText(SubeDegisim.ilkAdres);
            bt_duzSubeEkle.setDisable(true);
    }

}

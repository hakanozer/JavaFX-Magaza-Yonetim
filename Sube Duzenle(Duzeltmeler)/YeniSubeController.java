package subelergiris;

import hibernetVeritabani.NewHibernateUtil;
import hibernetVeritabani.Subeler;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class YeniSubeController implements Initializable {
    @FXML
    private Button bt_subeEkle;
    @FXML
    private Button bt_vazgec;
    @FXML
    private TextField tf_baslik;
    @FXML
    private TextField tf_telefon;
    @FXML
    private TextField tf_mail;
    @FXML
    private DatePicker dp_tarih;
    @FXML
    private TextArea ta_adres;
    protected static boolean uygunMail = false;
    private boolean mailGecerlimi = true;
    protected static boolean icerikVarmi = false;

    private void subeyeDon() throws IOException {
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
    public void mailKontrol(KeyEvent evt) {
        if (isValidEmailAddress(tf_mail.getText()) || tf_mail.getText().equals("")) {
            mailGecerlimi = true;
            uygunMail = mailGecerlimi;
        } else {
            mailGecerlimi = false;
            uygunMail = mailGecerlimi;
        }
    }

    public static boolean isValidEmailAddress(String email) {
        boolean stricterFilter = true;
        String stricterFilterString = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
        String laxString = ".+@.+\\.[A-Za-z]{2}[A-Za-z]*";
        String emailRegex = stricterFilter ? stricterFilterString : laxString;
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(emailRegex);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    @FXML
    private void vazgec(ActionEvent evt) throws IOException {
        if (!tf_baslik.getText().equals("") || !tf_mail.getText().equals("") || !tf_telefon.getText().equals("") || !ta_adres.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("UYARI");
            alert.setHeaderText("Yapılan değişiklikler var. Kaydetmeden çıkmak istedğinize emin misiniz ?");

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

    private void subeyiEkle() {
        SessionFactory sf1 = NewHibernateUtil.getSessionFactory();
        Session session = sf1.openSession();
        Subeler sb = new Subeler();
        sb.setSubeID(Integer.SIZE);
        sb.setSuperID(1);
        sb.setSBaslik(tf_baslik.getText().trim());
        sb.setSAdres(ta_adres.getText());
        sb.setSTelefon(tf_telefon.getText());
        sb.setSMail(tf_mail.getText());
        sb.setSTarih(Date.from(dp_tarih.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        Transaction tr = session.beginTransaction();
        session.save(sb);
        tr.commit();
        tf_baslik.setText("");
        tf_mail.setText("");
        tf_telefon.setText("");
        ta_adres.setText("");
    }

    @FXML
    private void icerikKontrol(KeyEvent evt) {

        if (!tf_mail.getText().equals("") || !tf_baslik.getText().equals("") || !tf_telefon.getText().equals("") || !ta_adres.getText().equals("")) {
            icerikVarmi = true;
            System.out.println("içerik var mı :" + icerikVarmi);
        } else {
            icerikVarmi = false;
            System.out.println("içerik var mı :" + icerikVarmi);
        }

    }

    private static boolean gecerliTel(String telNo) {
    if (telNo == null)
        return false;
    for (char c : telNo.toCharArray())
        if (c < '0' || c > '9')
            return false;
      return true;
     }
    
    @FXML
    private void ekle(ActionEvent evt) throws IOException {
        if ((tf_mail.getText().equals("")) || (tf_baslik.getText().equals("") || tf_mail.getText().equals("") || tf_telefon.getText().equals("") || ta_adres.getText().equals(""))) {
            if (!tf_mail.getText().equals("") && !mailGecerlimi) {
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("UYARI");
                alert2.setHeaderText("Geçersiz mail !!!");
                alert2.showAndWait();
                evt.consume();
            }
           
            else {
             
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("UYARI");
                alert.setHeaderText("Boş alanlar var. Böylece kaydedilsin mi " + "\n Şube adı :" + tf_baslik.getText() + " \n Şube mail :" + tf_mail.getText() + " \nŞube telefon :" + tf_telefon.getText() + " \nŞube adres :" + ta_adres.getText()+"\n Tarih :"+dp_tarih.getValue());
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    subeyiEkle();
                } else {
                    evt.consume();
                }
            }
        } else {
            if (mailGecerlimi) {
                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setTitle("KAYDEDİLSİN Mİ ?");
                alert2.setHeaderText("Şube adı :" + tf_baslik.getText() + " \nŞube mail :" + tf_mail.getText() + " \nŞube telefon :" + tf_telefon.getText() + " \nŞube adres :" + ta_adres.getText()+"\n Tarih :"+dp_tarih.getValue());
                Optional<ButtonType> result = alert2.showAndWait();
                if (result.get() == ButtonType.OK) {
                    subeyiEkle();
                } else {
                    evt.consume();
                }
            } else {
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("UYARI !!!");
                alert2.setHeaderText("Geçersiz mail girdiniz");
                alert2.showAndWait();
                evt.consume();
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}

package superAdmin;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class superAdminController implements Initializable {

    @FXML
    private TextField txtSuperAdminKulAdi;
    @FXML
    private PasswordField txtSuperAdminSifre;

    String ad = "";
    public static String adi = "";
    boolean durum = false;

    @FXML
    public String superAdminGiris(ActionEvent event) throws IOException {

        for (SuperAdmin_1 item : listeleSuperAdmin()) {
            ad = item.getKulAdi() + " " + item.getSifre();
            adi = item.getSAdi() + " " + item.getSSoyadi();
            if (ad.equals(txtSuperAdminKulAdi.getText() + " " + txtSuperAdminSifre.getText())) {
                ((Node) event.getSource()).getScene().getWindow().hide();
                Parent parent = FXMLLoader.load(getClass().getResource("/Anasayfa/Anasayfa.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.setTitle("Anasayfa");
                stage.show();
                durum = true;
                break;
            }
        }
        if (!durum) {
            new Alert(Alert.AlertType.WARNING, "Kullanıcı Adı yada Şifre Eşleşmiyor", ButtonType.YES).show();
        }
        return ad;
    }

    public List<SuperAdmin_1> listeleSuperAdmin() {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        List<SuperAdmin_1> liste = session.createQuery("from SuperAdmin_1").list();

        return liste;
    }

    @FXML
    public void textSifreRequest(ActionEvent evt) {
        txtSuperAdminKulAdi.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                if (!txtSuperAdminKulAdi.getText().equals("")) {
                    txtSuperAdminSifre.requestFocus();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Kullanıcı Adınızı Giriniz!!!", ButtonType.YES).show();
                }
            }
        });
    }

    @FXML
    public String textAdminGiris(ActionEvent evt) {
        txtSuperAdminSifre.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                if (!txtSuperAdminSifre.getText().equals("")) {
                    if (!txtSuperAdminKulAdi.getText().equals("") && !txtSuperAdminSifre.getText().equals("")) {
                        for (SuperAdmin_1 item : listeleSuperAdmin()) {
                            ad = item.getKulAdi() + " " + item.getSifre();
                            adi = item.getSAdi() + " " + item.getSSoyadi();
                            if (ad.equals(txtSuperAdminKulAdi.getText() + " " + txtSuperAdminSifre.getText())) {
                                try {
                                    ((Node) evt.getSource()).getScene().getWindow().hide();
                                    Parent parent = FXMLLoader.load(getClass().getResource("/Anasayfa/Anasayfa.fxml"));
                                    Stage stage = new Stage();
                                    Scene scene = new Scene(parent);
                                    stage.setScene(scene);
                                    stage.setTitle("Anasayfa");
                                    stage.show();
                                    durum = true;
                                    break;
                                } catch (IOException ex) {
                                    Logger.getLogger(superAdminController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        if (!durum) {
                            new Alert(Alert.AlertType.WARNING, "Kullanıcı Adı yada Şifre Eşleşmiyor", ButtonType.YES).show();
                        }
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Boslukları Doldurunuz!!!", ButtonType.YES).show();
                    }
                } else {
                    new Alert(Alert.AlertType.WARNING, "Sifre Giriniz!!!", ButtonType.YES).show();
                }
            }
        });
        return ad;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

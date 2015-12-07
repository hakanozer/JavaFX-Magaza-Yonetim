package mudurGiris;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import superAdmin.NewHibernateUtil;

public class MudurGirisController implements Initializable {

    @FXML
    private TextField txtSubeID;
    @FXML
    private TextField txtKullaniciAdi;
    @FXML
    private TextField txtSifre;

    @FXML
    private void musteriEkle() {
        Mudurler mudur = new Mudurler(null, Integer.valueOf(txtSubeID.getText()), txtKullaniciAdi.getText(), txtSifre.getText());
        ekle(mudur);
    }

    @FXML
    public void ekle(Mudurler mudur) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();

        int id = (int) session.save(mudur);
        session.getTransaction().commit();
    }
    
    @FXML
    public void geri(ActionEvent event) throws IOException{
         ((Node) event.getSource()).getScene().getWindow().hide();
                Parent parent = FXMLLoader.load(getClass().getResource("/Anasayfa/Anasayfa.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.setTitle("Anasayfa");
                stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

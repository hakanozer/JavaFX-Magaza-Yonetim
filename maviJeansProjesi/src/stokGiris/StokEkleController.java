package stokGiris;

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
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import superAdmin.NewHibernateUtil;

public class StokEkleController implements Initializable {

    @FXML
    private void stokEkle(ActionEvent event) {

    }

    @FXML
    private void ekle(Stoklar stok) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();

        int id = (int) session.save(stok);
        stok.setStokID(id);
        session.getTransaction().commit();
    }

    @FXML
    private void geri(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/stokGiris/stoklar.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Stoklar");
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

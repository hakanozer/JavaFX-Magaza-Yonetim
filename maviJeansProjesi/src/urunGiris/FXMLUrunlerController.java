/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urunGiris;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import superAdmin.NewHibernateUtil;

public class FXMLUrunlerController implements Initializable {

    @FXML
    private Label lblUrunRenk;

    @FXML
    private Button btnResimSec;

    @FXML
    private Label lblUrunTarih2;

    @FXML
    private Label lblUrunBeden;

    @FXML
    private TableColumn<?, ?> tblColumnUrunSezon;

    @FXML
    private Label lblUrunRenk2;

    @FXML
    private Label lblResim;

    @FXML
    private Label lblUrunRenk1;

    @FXML
    private Label lblUrunSezon2;

    @FXML
    private TextField txtUrunBarkod;

    @FXML
    private ComboBox<?> cmbRenk;

    @FXML
    private Label lblUrunFiyat2;

    @FXML
    private TableColumn<?, ?> tblColumnUrunRenk;

    @FXML
    private Label lblUrunBeden2;

    @FXML
    private TableColumn<Urunler, BigDecimal> tblColumnUrunFiyati;

    @FXML
    private TableColumn<?,?> tblColumnUrunBeden;

    @FXML
    private Label lblUrunBarkod2;

    @FXML
    private Label lblUrunTarih;

    @FXML
    private Label lblUrunAdi2;

    @FXML
    private Label lblUrunAdi;

    @FXML
    private Label lblUrunSezon;

    @FXML
    private TableColumn<Urunler, String> tblColumnUrunAdi;

    @FXML
    private ComboBox<?> cmbBeden;

    @FXML
    private TableColumn<Urunler, String> tblColumnUrunBarkod;

    @FXML
    private ComboBox<?> cmbSezon;

    @FXML
    private TableColumn<Urunler, Date> tblColumnUrunTarih;

    @FXML
    private Label lblUrunAciklama;

    @FXML
    private Label lblUrunAciklama2;

    @FXML
    private TableColumn<Urunler, String> tblColumnUrunAciklama;

    @FXML
    private Label lblurunFiyat;

    @FXML
    private TableView<Urunler> tblurunler;

    ObservableList<Urunler> kliste = FXCollections.observableArrayList();
    Session session;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        tabloyuDoldur();

    }

    public void resimSec() {

        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(btnResimSec.getScene().getWindow());

        try {
            String c = f.toURI().toURL().toExternalForm();
            ImageView livePerformIcon = new ImageView(c);
            livePerformIcon.setFitHeight(lblResim.getHeight());
            livePerformIcon.setFitWidth(lblResim.getWidth());
            lblResim.setGraphic(livePerformIcon);

        } catch (IOException ex) {
            Logger.getLogger(FXMLUrunlerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void tabloyuDoldur() {

        tblColumnUrunAdi.setCellValueFactory(new PropertyValueFactory<Urunler, String>("uAdi"));
        tblColumnUrunAciklama.setCellValueFactory(new PropertyValueFactory<Urunler, String>("uKisaAciklama"));
       // tblColumnUrunFiyati.setCellFactory(new PropertyValueFactory<Urunler, BigDecimal>("uFiyat"));
        
        Transaction tx;
        session = NewHibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("from Urunler");
            List ls = query.list();

            for (int i = 0; i < ls.size(); i++) {

                Urunler e = (Urunler) ls.get(i);
                kliste.add(e);
                System.out.println("asd");
            }
            
            tblurunler.setItems(kliste);

        } catch (Exception e) {
        } finally {
        }

    }

}

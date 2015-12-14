/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urunGiris;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.swing.text.MaskFormatter;
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
    private TableColumn<Urunler, Integer> tblColumnUrunSezon;

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
    private TextField txtUrunAdi;

    @FXML
    private TextField txtUrunAciklama;

    @FXML
    private TextField txtUrunFiyat;

    @FXML
    private ComboBox<String> cmbRenk;

    @FXML
    private Label lblUrunFiyat2;

    @FXML
    private TableColumn<Urunler, Integer> tblColumnUrunRenk;

    @FXML
    private Label lblUrunBeden2;

    @FXML
    private TableColumn<Urunler, BigDecimal> tblColumnUrunFiyati;

    @FXML
    private TableColumn<Urunler, Integer> tblColumnUrunBeden;

    @FXML
    private Label lblUrunBarkod2;

    @FXML
    private Label lblUrunAdi2;

    @FXML
    private Label lblUrunAdi;

    @FXML
    private Label lblUrunSezon;

    @FXML
    private TableColumn<Urunler, String> tblColumnUrunAdi;

    @FXML
    private ComboBox<String> cmbBeden;

    @FXML
    private TableColumn<Urunler, Integer> tblColumnUrunBarkod;

    @FXML
    private ComboBox<String> cmbSezon;

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

    @FXML
    private Button btnUrunuEkle;

    @FXML
    private Button btnBackup;

    @FXML
    private Label lblResim2;

    @FXML
    private TableColumn<Urunler, Integer> tblColumnUrunId;

    String filePath;
    Transaction tx;
    ObservableList<String> bedenListe = FXCollections.observableArrayList();
    ObservableList<String> sezonListe = FXCollections.observableArrayList();
    ObservableList<String> renkListe = FXCollections.observableArrayList();
    ObservableList<Urunler> kliste = FXCollections.observableArrayList();
    Session session;
    HashMap idResim = new HashMap();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        txtUrunFiyat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                String sb = "";
                char[] c = newValue.toCharArray();
                for (int i = 0; i < c.length; i++) {
                    if (Character.isDigit(c[i])) {
                        sb += c[i];
                    }
                }

                if (sb.length() > 2) {
                    Integer i = Integer.valueOf(sb);
                    Integer d = i / 100;

                    Integer d2 = i % 100;

                    if (d2 < 10) {
                        txtUrunFiyat.setText(d + "." + "0" + d2);
                    } else {
                        txtUrunFiyat.setText(d + "." + d2);
                    }

                }

            }
        });

        //addTextLimiter(txtUrunFiyat);
        tblurunler.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                String bedenAdi = "";
                String renkAdi = "";
                String sezonAdi = "";

                try {
                    session = NewHibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    List ls;
                    Query query = session.createQuery("from Sezonlar where sezonID = :sad");
                    query.setParameter("sad", tblurunler.getSelectionModel().getSelectedItem().getSezonID());
                    ls = query.list();
                    Sezonlar s = (Sezonlar) ls.get(0);
                    sezonAdi = s.getSBaslik();

                    query = session.createQuery("from Renkler where renkID = :sad");
                    query.setParameter("sad", tblurunler.getSelectionModel().getSelectedItem().getRenkID());
                    ls = query.list();
                    Renkler r = (Renkler) ls.get(0);
                    renkAdi = r.getRAdi();

                    query = session.createQuery("from Bedenler where bedenID = :sad");
                    query.setParameter("sad", tblurunler.getSelectionModel().getSelectedItem().getBedenID());
                    ls = query.list();
                    Bedenler b = (Bedenler) ls.get(0);
                    bedenAdi = b.getBBaslik();

                    tx.commit();

                } catch (Exception e) {

                } finally {
                    session.close();
                }

                int i = tblurunler.getSelectionModel().getSelectedItem().getUrunID();
                String c = idResim.get(i).toString();
                System.out.println(c);
                File f = new File(c);
                URI u = f.toURI();
                ImageView livePerformIcon = new ImageView(u.toString());
                livePerformIcon.setFitHeight(lblResim2.getHeight());
                livePerformIcon.setFitWidth(lblResim2.getWidth());
                lblResim2.setGraphic(livePerformIcon);
                lblUrunAdi2.setText(tblurunler.getSelectionModel().getSelectedItem().getUAdi());
                lblUrunAciklama2.setText(tblurunler.getSelectionModel().getSelectedItem().getUKisaAciklama());
                lblUrunFiyat2.setText(tblurunler.getSelectionModel().getSelectedItem().getUFiyat().toString());
                lblUrunBeden2.setText(bedenAdi);
                lblUrunRenk2.setText(renkAdi);
                lblUrunSezon2.setText(sezonAdi);
                lblUrunTarih2.setText(tblurunler.getSelectionModel().getSelectedItem().getUTarih().toString());
                lblUrunBarkod2.setText(tblurunler.getSelectionModel().getSelectedItem().getBarkodNo());

            }
        });

        tabloyuDoldur();
        renkleriGetir();
        sezonlariGetir();
        bedenleriGetir();

    }

    public void renkleriGetir() {

        session = NewHibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("from Renkler");
        List ls = query.list();

        for (int i = 0; i < ls.size(); i++) {

            Renkler r = (Renkler) ls.get(i);
            renkListe.add(r.getRAdi());
        }

        cmbRenk.setItems(renkListe);

    }

    public void sezonlariGetir() {

        session = NewHibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("from Sezonlar");
        List ls = query.list();

        for (int i = 0; i < ls.size(); i++) {

            Sezonlar s = (Sezonlar) ls.get(i);
            sezonListe.add(s.getSBaslik());
        }

        cmbSezon.setItems(sezonListe);

    }

    public void bedenleriGetir() {

        session = NewHibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("from Bedenler");
        List ls = query.list();

        for (int i = 0; i < ls.size(); i++) {

            Bedenler b = (Bedenler) ls.get(i);
            bedenListe.add(b.getBBaslik());
        }

        cmbBeden.setItems(bedenListe);

    }

    public void resimSec() {

        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(btnResimSec.getScene().getWindow());

        try {

            if (f != null) {
                filePath = f.getCanonicalPath();
                String c = f.toURI().toURL().toExternalForm();
                ImageView livePerformIcon = new ImageView(c);
                livePerformIcon.setFitHeight(lblResim.getHeight());
                livePerformIcon.setFitWidth(lblResim.getWidth());
                lblResim.setGraphic(livePerformIcon);
            }

        } catch (IOException ex) {
            Logger.getLogger(FXMLUrunlerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void tabloyuDoldur() {

        tblColumnUrunAdi.setCellValueFactory(new PropertyValueFactory<Urunler, String>("uAdi"));
        tblColumnUrunAciklama.setCellValueFactory(new PropertyValueFactory<Urunler, String>("uKisaAciklama"));
        tblColumnUrunFiyati.setCellValueFactory(new PropertyValueFactory<Urunler, BigDecimal>("uFiyat"));
        tblColumnUrunSezon.setCellValueFactory(new PropertyValueFactory<Urunler, Integer>("sezonID"));
        tblColumnUrunBeden.setCellValueFactory(new PropertyValueFactory<Urunler, Integer>("bedenID"));
        tblColumnUrunRenk.setCellValueFactory(new PropertyValueFactory<Urunler, Integer>("renkID"));
        tblColumnUrunBarkod.setCellValueFactory(new PropertyValueFactory<Urunler, Integer>("barkodNo"));
        tblColumnUrunTarih.setCellValueFactory(new PropertyValueFactory<Urunler, Date>("uTarih"));
        tblColumnUrunId.setCellValueFactory(new PropertyValueFactory<Urunler, Integer>("urunID"));

        idResim.clear();

        session = NewHibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("from Urunler");
            List ls = query.list();

            for (int i = 0; i < ls.size(); i++) {

                Urunler e = (Urunler) ls.get(i);
                kliste.add(e);
                idResim.put(e.getUrunID(), e.getUResim());

            }

            tblurunler.setItems(kliste);
            tx.commit();
        } catch (Exception e) {
        } finally {
            session.close();
        }

    }

    public void urunEkle() {

        session = NewHibernateUtil.getSessionFactory().openSession();
        try {

            if (cmbBeden.getSelectionModel().getSelectedItem() == null || cmbSezon.getSelectionModel().getSelectedItem() == null
                    || cmbRenk.getSelectionModel().getSelectedItem() == null || txtUrunBarkod.getText().equals("")
                    || txtUrunAdi.getText().equals("") || txtUrunAciklama.getText().equals("")
                    || txtUrunFiyat.getText().equals("") || filePath.equals("")) {

                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Giriş Hatası");
                alert.setHeaderText("");
                alert.setContentText("Tüm alanları doldurmadınız!");
                alert.showAndWait();

            } else {
                tx = session.beginTransaction();

                Integer renkId, bedenId, sezonId;
                List ls;
                Query query = session.createQuery("from Sezonlar where sBaslik = :sad");
                query.setParameter("sad", cmbSezon.getSelectionModel().getSelectedItem().trim());
                ls = query.list();
                Sezonlar s = (Sezonlar) ls.get(0);
                sezonId = s.getSezonID();

                query = session.createQuery("from Renkler where rAdi = :sad");
                query.setParameter("sad", cmbRenk.getSelectionModel().getSelectedItem().trim());
                ls = query.list();
                Renkler r = (Renkler) ls.get(0);
                renkId = r.getRenkID();

                query = session.createQuery("from Bedenler where bBaslik = :sad");
                query.setParameter("sad", cmbBeden.getSelectionModel().getSelectedItem().trim());
                ls = query.list();
                Bedenler b = (Bedenler) ls.get(0);
                bedenId = b.getBedenID();

                Urunler yeniUrun = new Urunler();
                yeniUrun.setUAdi(txtUrunAdi.getText().trim());
                yeniUrun.setUKisaAciklama(txtUrunAciklama.getText().trim());
                yeniUrun.setUTarih(new Date());
                yeniUrun.setUFiyat(new BigDecimal(txtUrunFiyat.getText().trim().replaceAll(",", "")));
                yeniUrun.setBarkodNo(new String(txtUrunBarkod.getText().trim()));
                yeniUrun.setBedenID(bedenId);
                yeniUrun.setSezonID(sezonId);
                yeniUrun.setRenkID(renkId);
                yeniUrun.setUResim(filePath);

                session.save(yeniUrun);
                idResim.put(yeniUrun.getUrunID(), yeniUrun.getUResim());
                tx.commit();
                tblurunler.getItems().add(yeniUrun);

            }

        } catch (Exception e) {
            System.out.println("Transaction sırasında hata: " + e);
        } finally {
            session.close();
        }

    }

    public void backup() {

        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();

        String date = day + "-" + month + "-" + year + "_" + hour + minute + second;

        String userName = "sa";
        String password = "12345";

        String url = "jdbc:sqlserver://ERDEL-PC\\SQLLL;databaseName=";
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        Connection conn = null;
        Statement st = null;

        try {
            if (conn == null) {
                
                Class.forName(driver);
                conn = DriverManager.getConnection(url + "mavi;", userName, password);
                st = conn.createStatement();
                String strSelect = "BACKUP DATABASE mavi TO DISK = 'E:\\JAVA\\" + date + "__mavi.bak'";
                st.execute(strSelect);
                System.out.println("Backup alındı.");

            }
        } catch (ClassNotFoundException | SQLException e) {

            System.err.println("Bağlantı Hatası : " + e);

        }
    }

}

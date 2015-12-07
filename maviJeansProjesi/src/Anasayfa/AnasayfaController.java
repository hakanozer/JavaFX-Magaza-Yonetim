package Anasayfa;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mudurGiris.Mudurler;
import mudurGiris.mudurProperty;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import superAdmin.NewHibernateUtil;
import superAdmin.SuperAdmin_1;
import superAdmin.superAdminController;

public class AnasayfaController implements Initializable {

    @FXML
    private Label lblAdSoyad;
    @FXML
    private TableView<mudurProperty> tableMudur;
    @FXML
    private TableColumn<Mudurler, Integer> mID;
    @FXML
    private TableColumn<Mudurler, Integer> subeID;
    @FXML
    private TableColumn<Mudurler, String> mAdi;
    @FXML
    private TableColumn<Mudurler, String> mSoyadi;
    @FXML
    private TableColumn<Mudurler, String> mTelefon;
    @FXML
    private TableColumn<Mudurler, String> mCep;
    @FXML
    private TableColumn<Mudurler, String> mMail;
    @FXML
    private TableColumn<Mudurler, String> mAdres;
    @FXML
    private TableColumn<Mudurler, String> mKulAdi;
    @FXML
    private TableColumn<Mudurler, String> mSifre;
    @FXML
    private TableColumn<Mudurler, String> mTarih;

    ObservableList<mudurProperty> listeMudur = FXCollections.observableArrayList();

    Scene scene;
    Stage stage;
    Parent parent;

    private List<Mudurler> mudurListele() {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        List<Mudurler> liste = session.createQuery("from Mudurler").list();

        return liste;
    }

    @FXML
    private void mudurTablosu(ActionEvent event) {
        listeMudur.clear();
        mID.setCellValueFactory(new PropertyValueFactory<>("mID"));
        subeID.setCellValueFactory(new PropertyValueFactory<>("subeID"));
        mAdi.setCellValueFactory(new PropertyValueFactory<>("mAdi"));
        mSoyadi.setCellValueFactory(new PropertyValueFactory<>("mSoyadi"));
        mTelefon.setCellValueFactory(new PropertyValueFactory<>("mTelefon"));
        mCep.setCellValueFactory(new PropertyValueFactory<>("mCep"));
        mMail.setCellValueFactory(new PropertyValueFactory<>("mMail"));
        mAdres.setCellValueFactory(new PropertyValueFactory<>("mAdres"));
        mKulAdi.setCellValueFactory(new PropertyValueFactory<>("mKulAdi"));
        mSifre.setCellValueFactory(new PropertyValueFactory<>("mSifre"));
        mTarih.setCellValueFactory(new PropertyValueFactory<>("mTarih"));

        for (Mudurler item : mudurListele()) {
            listeMudur.add(new mudurProperty(item.getMID(), item.getSubeID(), item.getMAdi(), item.getMSoyadi(),
                    item.getMTelefon(), item.getMCep(), item.getMMail(), item.getMAdres(), item.getMKuladi(),
                    item.getMSifre(), item.getMTarih().toString()));
            System.out.println("ad: " + item.getMAdi());
            System.out.println("soyad: " + item.getMSoyadi());
            System.out.println("mID: " + item.getMID());
            System.out.println("subeID: " + item.getSubeID());
        }
        tableMudur.setItems(listeMudur);
    }

    @FXML
    private void musteriEkle(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        parent = FXMLLoader.load(getClass().getResource("/mudurGiris/mudurGiris.fxml"));
        stage = new Stage();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Müdür Ekle");
        stage.show();
    }

    @FXML
    private void pdfRaporla(ActionEvent event) throws DocumentException {
        try {

            final Document PDFLogReport = new Document();
            PdfWriter.getInstance(PDFLogReport, new FileOutputStream(
                    "C:\\Users\\java-sabah\\Desktop\\Super Admin Tablosu.pdf"));
            PDFLogReport.open();
            // we have two columns in our table
            final PdfPTable LogTable = new PdfPTable(10);
            PdfPCell table_cell2;
            table_cell2 = new PdfPCell(new Phrase("Super ID"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("Kullanici Ad"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("Sifre"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("Ad"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("Soyad"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("Telefon"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("Cep Telefonu"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("Adres"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("Mail"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("Tarih"));
            LogTable.addCell(table_cell2);
            PdfPCell table_cell;

            for (SuperAdmin_1 item : listeleSuperAdmin()) {

                table_cell = new PdfPCell(new Phrase(item.getSuperID().toString()));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(item.getKulAdi()));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(item.getSifre()));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(item.getSAdi()));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(item.getSSoyadi()));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(item.getSTelefon()));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(item.getSCep()));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(item.getSAdres()));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(item.getSMail()));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(item.getSTarih().toString()));
                LogTable.addCell(table_cell);
            }
            PDFLogReport.add(LogTable);
            PDFLogReport.close();
        } catch (final FileNotFoundException ex) {
            System.out.println("pdf raporla hatası: " + ex.getMessage());
        }
    }

    public List<SuperAdmin_1> listeleSuperAdmin() {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        List<SuperAdmin_1> liste = session.createQuery("from SuperAdmin_1").list();

        return liste;
    }

    @FXML
    public void oturumKapat(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        parent = FXMLLoader.load(getClass().getResource("/superAdmin/superAdmin.fxml"));
        stage = new Stage();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Super Admin Girişi");
        stage.show();
    }

    @FXML
    public void sezonAc(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        parent = FXMLLoader.load(getClass().getResource("/sezonGiris/sezonGiris.fxml"));
        stage = new Stage();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Sezon Ekle");
        stage.show();
    }

    @FXML
    public void urunEkle(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        parent = FXMLLoader.load(getClass().getResource("/urunGiris/urunlerGiris.fxml"));
        stage = new Stage();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Ürün Ekle");
        stage.show();
    }

    @FXML
    public void stoklar(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        parent = FXMLLoader.load(getClass().getResource("/stokGiris/stoklar.fxml"));
        stage = new Stage();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Stoklar");
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblAdSoyad.setText("Hoşgeldiniz " + superAdminController.adi);
        System.out.println("ad: " + superAdminController.adi);
        //mudurTablosu(null);
    }

}

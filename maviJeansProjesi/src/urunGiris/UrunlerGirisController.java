package urunGiris;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import superAdmin.NewHibernateUtil;

public class UrunlerGirisController implements Initializable {

    @FXML
    private TableView<urunProperty> tableUrun;
    @FXML
    private TableColumn<Urunler, Integer> urunID;
    @FXML
    private TableColumn<Urunler, Integer> superID;
    @FXML
    private TableColumn<Urunler, Integer> sezonID;
    @FXML
    private TableColumn<Urunler, Integer> bedenID;
    @FXML
    private TableColumn<Urunler, Integer> renkID;
    @FXML
    private TableColumn<Urunler, String> urunAd;
    @FXML
    private TableColumn<Urunler, String> urunKisaAciklama;
    @FXML
    private TableColumn<Urunler, String> urunFiyat;
    @FXML
    private TableColumn<Urunler, String> urunResim;
    @FXML
    private TableColumn<Urunler, String> urunTarih;

    ObservableList<urunProperty> listeTable = FXCollections.observableArrayList();

    @FXML
    private void geri(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/Anasayfa/Anasayfa.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Anasayfa");
        stage.show();
    }

    @FXML
    private List<Urunler> urunListele() {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        List<Urunler> liste = session.createQuery("from Urunler").list();

        return liste;
    }

    @FXML
    private void listem(ActionEvent event) {
        listeTable.clear();
        urunID.setCellValueFactory(new PropertyValueFactory<>("urunID"));
        superID.setCellValueFactory(new PropertyValueFactory<>("superID"));
        sezonID.setCellValueFactory(new PropertyValueFactory<>("sezonID"));
        bedenID.setCellValueFactory(new PropertyValueFactory<>("bedenID"));
        renkID.setCellValueFactory(new PropertyValueFactory<>("renkID"));
        urunAd.setCellValueFactory(new PropertyValueFactory<>("urunAd"));
        urunKisaAciklama.setCellValueFactory(new PropertyValueFactory<>("urunKisaAciklama"));
        urunFiyat.setCellValueFactory(new PropertyValueFactory<>("urunFiyat"));
        urunResim.setCellValueFactory(new PropertyValueFactory<>("urunResim"));
        urunTarih.setCellValueFactory(new PropertyValueFactory<>("urunTarih"));

        for (Urunler item : urunListele()) {

            listeTable.add(new urunProperty(item.getUrunID(), item.getSuperID(), item.getSezonID(), item.getBedenID(),
                    item.getRenkID(), item.getUAdi(), item.getUKisaAciklama(), item.getUFiyat().toString(),
                    item.getUResim(), item.getUTarih().toString()));
        }
        tableUrun.setItems(listeTable);
    }

    @FXML
    private void raporla(ActionEvent event) throws DocumentException {
        try {

            final Document PDFLogReport = new Document();
            PdfWriter.getInstance(PDFLogReport, new FileOutputStream(
                    "C:\\Users\\ERTAN\\Desktop\\Urunler Tablosu.pdf"));
            PDFLogReport.open();
            // we have two columns in our table
            final PdfPTable LogTable = new PdfPTable(10);
            PdfPCell table_cell2;
            table_cell2 = new PdfPCell(new Phrase("Urun ID"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("Super ID"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("Sezon ID"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("Beden ID"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("Renk ID"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("Ad"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("Kisa Aciklama"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("Fiyat"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("Resim"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("Tarih"));
            LogTable.addCell(table_cell2);
            PdfPCell table_cell;

            for (Urunler item : urunListele()) {

                table_cell = new PdfPCell(new Phrase(item.getUrunID().toString()));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(item.getSuperID().toString()));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(item.getSezonID().toString()));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(item.getBedenID().toString()));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(item.getRenkID().toString()));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(item.getUAdi()));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(item.getUKisaAciklama()));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(item.getUFiyat().toString()));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(item.getUResim()));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(item.getUTarih().toString()));
                LogTable.addCell(table_cell);
            }
            PDFLogReport.add(LogTable);
            PDFLogReport.close();
        } catch (final FileNotFoundException ex) {
            System.out.println("pdf raporla hatası: " + ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listem(null);
    }

}

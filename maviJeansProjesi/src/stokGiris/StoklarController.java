package stokGiris;

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
import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;
import superAdmin.NewHibernateUtil;

public class StoklarController implements Initializable {

    @FXML
    private TableView<stokProperty> tableStok;
    @FXML
    private TableColumn<Stoklar, Integer> stokID;
    @FXML
    private TableColumn<Stoklar, Integer> superID;
    @FXML
    private TableColumn<Stoklar, Integer> urunID;
    @FXML
    private TableColumn<Stoklar, TinyIntTypeDescriptor> gelenAdet;
    @FXML
    private TableColumn<Stoklar, TinyIntTypeDescriptor> kalanAdet;
    @FXML
    private TableColumn<Stoklar, String> Tarih;

    ObservableList<stokProperty> listeTable = FXCollections.observableArrayList();

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
    private void stokEkle(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/stokGiris/stokEkle.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Stok Ekle");
        stage.show();
    }

    @FXML
    private List<Stoklar> stokListele() {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        List<Stoklar> liste = session.createQuery("from Stoklar").list();

        return liste;
    }

    @FXML
    private void listem(ActionEvent event) {
        listeTable.clear();
        stokID.setCellValueFactory(new PropertyValueFactory<>("stokID"));
        superID.setCellValueFactory(new PropertyValueFactory<>("superID"));
        urunID.setCellValueFactory(new PropertyValueFactory<>("urunID"));
        gelenAdet.setCellValueFactory(new PropertyValueFactory<>("gelenAdet"));
        kalanAdet.setCellValueFactory(new PropertyValueFactory<>("kalanAdet"));
        Tarih.setCellValueFactory(new PropertyValueFactory<>("Tarih"));

        for (Stoklar item : stokListele()) {

            listeTable.add(new stokProperty(item.getStokID(), item.getSuperID(), item.getUrunID(), item.getGelenAdet(),
                    item.getKalanAdet().intValue(), item.getTarih().toString()));
        }
        tableStok.setItems(listeTable);
    }

    @FXML
    private void raporla(ActionEvent event) throws DocumentException {
        try {

            final Document PDFLogReport = new Document();
            PdfWriter.getInstance(PDFLogReport, new FileOutputStream(
                    "C:\\Users\\ERTAN\\Desktop\\Stoklar Tablosu.pdf"));
            PDFLogReport.open();
            // we have two columns in our table
            final PdfPTable LogTable = new PdfPTable(6);
            PdfPCell table_cell2;
            table_cell2 = new PdfPCell(new Phrase("StokID"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("SuperID"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("UrunID"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("GelenAdet"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("KalanAdet"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("Tarih"));
            LogTable.addCell(table_cell2);
            PdfPCell table_cell;

            for (Stoklar item : stokListele()) {
                table_cell = new PdfPCell(new Phrase(item.getStokID().toString()));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(item.getSuperID().toString()));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(item.getUrunID().toString()));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(item.getGelenAdet().toString()));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(item.getKalanAdet().toString()));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(item.getTarih().toString()));
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

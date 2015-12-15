package maviproje;

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AnasayfaController implements Initializable {

    @FXML
    private Label lblResim;
    MSSQLClass db;
    ResultSet rs;

    @FXML
    private void sezonRaporla(ActionEvent event) throws SQLException {
        try {

            Document PDFLogReport = new Document();
            PdfWriter.getInstance(PDFLogReport, new FileOutputStream(
                    "Sezon Raporu.pdf"));
            PDFLogReport.open();
            // we have two columns in our table
            PdfPTable LogTable = new PdfPTable(5);
            PdfPCell table_cell2;
            table_cell2 = new PdfPCell(new Phrase("sezonID"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("AdminAd"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("Baslik"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("Durum"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("Tarih"));
            LogTable.addCell(table_cell2);
            PdfPCell table_cell;

            rs = db.preProGetir("sezonRapor");
            while (rs.next()) {

                table_cell = new PdfPCell(new Phrase(rs.getString("sezonID")));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(rs.getString("sAdi")));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(rs.getString("sBaslik")));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(rs.getString("sDurum")));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(rs.getString("sTarih")));
                LogTable.addCell(table_cell);
            }
            PDFLogReport.add(LogTable);
            PDFLogReport.close();
        } catch (FileNotFoundException ex) {
            System.out.println("pdf raporla hatası: " + ex.getMessage());

        } catch (DocumentException ex) {
            Logger.getLogger(AnasayfaController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void stokRaporla(ActionEvent event) {
        try {

            Document PDFLogReport = new Document();
            PdfWriter.getInstance(PDFLogReport, new FileOutputStream(
                    "Stok Raporu.pdf"));
            PDFLogReport.open();
            // we have two columns in our table
            PdfPTable LogTable = new PdfPTable(7);
            PdfPCell table_cell2;
            table_cell2 = new PdfPCell(new Phrase("stokID"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("AdminAd"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("subeAd"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("urunAD"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("gelenAdet"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("kalanAdet"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("Tarih"));
            LogTable.addCell(table_cell2);
            PdfPCell table_cell;

            rs = db.preProGetir("stokRapor");
            while (rs.next()) {

                table_cell = new PdfPCell(new Phrase(rs.getString("stokID")));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(rs.getString("sAdi")));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(rs.getString("sBaslik")));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(rs.getString("uAdi")));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(rs.getString("gelenAdet")));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(rs.getString("kalanAdet")));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(rs.getString("tarih")));
                LogTable.addCell(table_cell);
            }
            PDFLogReport.add(LogTable);
            PDFLogReport.close();
        } catch (FileNotFoundException ex) {
            System.out.println("pdf raporla hatası: " + ex.getMessage());

        } catch (DocumentException | SQLException ex) {
            Logger.getLogger(AnasayfaController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void urunRaporla(ActionEvent event) throws SQLException {
        try {

            Document PDFLogReport = new Document();
            PdfWriter.getInstance(PDFLogReport, new FileOutputStream(
                    "Urun Raporu.pdf"));
            PDFLogReport.open();
            // we have two columns in our table
            PdfPTable LogTable = new PdfPTable(11);
            PdfPCell table_cell2;
            table_cell2 = new PdfPCell(new Phrase("Urun ID"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("AdminAd"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("SezonAd"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("bedenAd"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("RenkAd"));
            LogTable.addCell(table_cell2);
            table_cell2 = new PdfPCell(new Phrase("Barkod No"));
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

            rs = db.preProGetir("urunRapor");
            while (rs.next()) {

                table_cell = new PdfPCell(new Phrase(rs.getString("urunID")));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(rs.getString("sAdi")));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(rs.getString("sBaslik")));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(rs.getString("bBaslik")));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(rs.getString("rAdi")));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(rs.getString("barkodNo")));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(rs.getString("uAdi")));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(rs.getString("uKisaAciklama")));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(rs.getString("uFiyat")));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(rs.getString("uResim")));
                LogTable.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(rs.getString("uTarih")));
                LogTable.addCell(table_cell);
            }
            PDFLogReport.add(LogTable);
            PDFLogReport.close();
        } catch (FileNotFoundException ex) {
            System.out.println("pdf raporla hatası: " + ex.getMessage());

        } catch (DocumentException ex) {
            Logger.getLogger(AnasayfaController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void tarihUrunRaporlaMenuItem() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tarihUrunRapor/tarihUrunRapor.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Tarihe Göre Urun Raporlama");
        stage.show();
    }

    @FXML
    private void tarihStokRaporlaMenuItem() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tarihStokRapor/tarihStokRapor.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Tarihe Göre Stok Raporlama");
        stage.show();
    }

    @FXML
    private void tarihSezonRaporlaMenuItem() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tarihSezonRapor/tarihSezonRapor.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Tarihe Göre Sezon Raporlama");
        stage.show();
    }
    
     @FXML
    private void urunSatisGrafikMenuItem() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/grafikUrunSatis/grafikUrunSatis.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Ürün Satıs Grafik");
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblResim.setGraphic(new ImageView("Mavi.jpg"));
        db = new MSSQLClass();
    }
}

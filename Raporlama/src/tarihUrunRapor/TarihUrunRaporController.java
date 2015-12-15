package tarihUrunRapor;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressBar;
import maviproje.AnasayfaController;
import maviproje.MSSQLClass;

public class TarihUrunRaporController implements Initializable {

    @FXML
    private DatePicker dateBefore;
    @FXML
    private DatePicker dateAfter;
    @FXML
    private ProgressBar progresZaman;

    MSSQLClass db;
    ResultSet rs;
    LocalDate date1;
    LocalDate date2;
    private final String pattern = "yyyy-MM-dd";
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
    Timer timer;
    TimerTask task;
    int progres = 1;
    int total = 1;

    @FXML
    private void urunRaporla(ActionEvent event) throws SQLException, ParseException {
        if (dateBefore.getValue() != null && dateAfter.getValue() != null) {
            progresZaman.setProgress(0);
            try {
                Document PDFLogReport = new Document();
                PdfWriter.getInstance(PDFLogReport, new FileOutputStream(
                        "Tarihe Göre Urun Raporu - " + dateBefore.getValue() + " - " + dateAfter.getValue() + ".pdf"));
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

                rs = db.preTarihGetir("urunRaporTarih", date1.toString(), date2.toString());
                //rs = db.preProGetir("urunRapor");
                while (rs.next()) {

                    // if (rs.getDate("uTarih").before(d1) && rs.getDate("uTarih").after(d2)) {
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
                    // }
                }
                PDFLogReport.add(LogTable);
                PDFLogReport.close();
            } catch (FileNotFoundException ex) {
                System.out.println("pdf raporla hatası: " + ex.getMessage());

            } catch (DocumentException ex) {
                Logger.getLogger(AnasayfaController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            timer = new Timer();
            task = new TimerTask() {

                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        progres++;
                        progresZaman.setProgress(progres / total);
                    }

                }
            };
            timer.schedule(task, 1000, 1000);
            progres = 1;
        } else {
            new Alert(Alert.AlertType.WARNING, "Tarih Aralıklarını Belirtiniz!!!", ButtonType.OK).show();
        }
    }

    @FXML
    private void pdfOpen(ActionEvent event) {
        String dosya = "Tarihe Göre Urun Raporu - " + dateBefore.getValue() + " - " + dateAfter.getValue() + ".pdf";

        try {

            if ((new File(dosya)).exists()) {

                Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + dosya);

                p.waitFor();

            } else {
                new Alert(Alert.AlertType.ERROR, "Dosya Mevcut Değil!!!", ButtonType.OK).show();
                System.out.println("Dosya Yok!!!");
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println("pdfOpen() hatası: " + ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new MSSQLClass();
        System.out.println("datebefore: " + dateBefore.getValue());
        System.out.println("dateafter: " + dateAfter.getValue());

        dateBefore.setOnAction(event -> {
            date1 = dateBefore.getValue();
            System.out.println("Before: " + date1);
        });

        dateAfter.setOnAction(event -> {
            date2 = dateAfter.getValue();
            System.out.println("After: " + date2);
        });
    }
}

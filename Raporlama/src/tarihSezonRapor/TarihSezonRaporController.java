package tarihSezonRapor;

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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

public class TarihSezonRaporController implements Initializable {

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
    Timer timer;
    TimerTask task;
    private final String pattern = "yyyy-MM-dd";
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
    int progres = 1;
    int total = 1;

    @FXML
    private void sezonRaporla(ActionEvent event) throws SQLException {
        if (dateBefore.getValue() != null && dateAfter.getValue() != null) {
            progresZaman.setProgress(0);
            try {
                Document PDFLogReport = new Document();
                PdfWriter.getInstance(PDFLogReport, new FileOutputStream(
                        "Tarihe Göre Sezon Raporu - " + dateBefore.getValue() + " - " + dateAfter.getValue() + ".pdf"));
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

                rs = db.preTarihGetir("sezonRaporTarih", date1.toString(), date2.toString());
                //  rs = db.preProGetir("sezonRapor");
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
            } catch (FileNotFoundException ex) {
                System.out.println("pdf raporla hatası: " + ex.getMessage());

            } catch (DocumentException ex) {
                Logger.getLogger(AnasayfaController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Tarih Aralıklarını Belirtiniz!!!", ButtonType.OK).show();
        }
    }

    @FXML
    private void pdfOpen(ActionEvent event) {
        String dosya = "Tarihe Göre Sezon Raporu - " + dateBefore.getValue() + " - " + dateAfter.getValue() + ".pdf";

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

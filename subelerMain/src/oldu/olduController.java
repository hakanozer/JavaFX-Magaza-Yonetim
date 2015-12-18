//package oldu;
//
//import domain.Satis;
//import util.NewHibernateUtil;
//import java.math.BigDecimal;
//import java.net.URL;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Optional;
//import java.util.ResourceBundle;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.print.PageLayout;
//import javafx.print.PageOrientation;
//import javafx.print.Paper;
//import javafx.print.Printer;
//import javafx.print.PrinterJob;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Button;
//import javafx.scene.control.ButtonType;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.DatePicker;
//import javafx.scene.control.Label;
//import javafx.scene.control.ListView;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.transform.Scale;
//import org.hibernate.Query;
//import org.hibernate.SQLQuery;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.engine.query.spi.sql.NativeSQLQueryReturn;
//
///**
// *
// * @author idris
// */
//public class olduController implements Initializable {
//    @FXML
//    private TextField txt2;
//    @FXML
//    private TextField txt1;
//    @FXML
//    private TableView satist;
//    @FXML
//    private TableColumn<Satis, String> satisID;
//    @FXML
//    private TableColumn<Satis, String> sepetRefKodu;
//    @FXML
//    private TableColumn<Satis, String> personelID;
//    @FXML
//    private TableColumn<Satis, String> fiyat;
//    @FXML
//    private TableColumn<Satis, String> odemeTipi;
//    @FXML
//    private TableColumn<Satis, String> sTarih;
//    @FXML
//    private Label toplamlbl;
//    @FXML
//    private DatePicker datepicker;
//    ObservableList<Satis> data;
//    ArrayList<Satis> list = new ArrayList<>();
//    @FXML
//    private Label cirolbl;
//    @FXML
//    private Button yazdirbtn;
//    @FXML
//    private Button kapatbtn;
//    @FXML
//    private Button toplabtn;
//    @FXML
//    private ListView lsttoplam;
//
//  
//   int secilenpersonelıd;
//   
//   
//   
//    @FXML
//    private void kasaKapat(ActionEvent event) { 
//       
//         if (datepicker.getValue() == null) {
//            Alert alert = new Alert(AlertType.ERROR);
//            alert.setTitle("hatalı giriş");
//            alert.setHeaderText("");
//            alert.setContentText("lütfen tarih seciniz!");
//
//            alert.showAndWait();
//         }
//        
//        krediNakit();
//           
//        
//         TabloDoldur();
//
//    }
//
//    @FXML
//    private void topla(ActionEvent event) {
//
//      
//        
//        TabloDoldur();
//        topla();
//
//    }
//
//    @FXML
//    private void yazdırr(ActionEvent event) {
//
//          if (datepicker.getValue() == null) {
//            Alert alert = new Alert(AlertType.ERROR);
//            alert.setTitle("hatalı giriş");
//            alert.setHeaderText("");
//            alert.setContentText("lütfen tarih seciniz!");
//
//            alert.showAndWait();
//         }
//        
//     
//            topla();
//            
//            
//     Alert alert = new Alert(AlertType.CONFIRMATION);
//        alert.setTitle("Günlük takip formu");
//        alert.setHeaderText("");
//        alert.setContentText("DEVAM ETMEK İSTİYORMUSUN ?");
//
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == ButtonType.CANCEL) {
//
//        } else {
//            if (result.get() == ButtonType.OK) {
//            }
//            TabloDoldur();
//            printIt();
//
//        }
//    }
//
//    public void TabloDoldur() {
//
//        satisID.setCellValueFactory(new PropertyValueFactory<Satis, String>("satisID"));
//        sepetRefKodu.setCellValueFactory(new PropertyValueFactory<Satis, String>("sepetRefKodu"));
//        personelID.setCellValueFactory(new PropertyValueFactory<Satis, String>("personelID"));
//        fiyat.setCellValueFactory(new PropertyValueFactory<Satis, String>("fiyat"));
//        odemeTipi.setCellValueFactory(new PropertyValueFactory<Satis, String>("odemeTipi"));
//        sTarih.setCellValueFactory(new PropertyValueFactory<Satis, String>("sTarih"));
//        data = FXCollections.observableArrayList();
// 
//        try {
//
//            SessionFactory sf = new Configuration().configure().buildSessionFactory();
//            Session ss = sf.openSession();
//            Transaction ts = ss.beginTransaction();
//            LocalDate datePicked = datepicker.getValue();
//            String whereSql = "";
//
//            if (datePicked != null) {
//                whereSql = " where sTarih = '" + datepicker.getValue() + "'";
//            }
//
//            Query qee = ss.createQuery("from Satis" + whereSql);
//
//            Iterator ite = qee.iterate();
//
//            while (ite.hasNext()) {
//                Satis sat = (Satis) ite.next();
//                System.out.println(sat);
//                data.add(sat);
//                list.add(sat);
//            }
//
//            satist.setItems(data);
//            ts.commit();
//            ss.close();
//
//            satist.getSelectionModel().clearSelection();
//            list.clear();
//
//        } catch (Exception e) {
//            System.out.println("asdasd" + e);
//
//        }
//        if (datepicker.getValue() == null) {
//            Alert alert = new Alert(AlertType.ERROR);
//            alert.setTitle("hatalı giriş");
//            alert.setHeaderText("");
//            alert.setContentText("lütfen tarih seciniz!");
//
//            alert.showAndWait();
//        }
//
//    }
//
//    public void topla() {
//
//        SessionFactory sf = new Configuration().configure().buildSessionFactory();
//        Session ss = sf.openSession();
//        Transaction ts = ss.beginTransaction();
//        String tarih = "2015-12-10 00:00:00.000";
//        Query qee = ss.createQuery("select sum(fiyat) from Satis WHERE sTarih  = '" + datepicker.getValue() + "'");
//
//        BigDecimal sumFiyat = (BigDecimal) qee.uniqueResult();
//        toplamlbl.setText(String.valueOf(sumFiyat));
//
//        datepicker.getValue();
//
//        ts.commit();
//        ss.close();
//    }
//
//    
//  
//       public void krediNakit(){
//         
//         SessionFactory sf = new Configuration().configure().buildSessionFactory();
//        Session ss = sf.openSession();
//        Transaction ts = ss.beginTransaction();
//        String tarih = "2015-12-10 00:00:00.000";
//        Query qee = ss.createQuery("select sum(fiyat) from Satis WHERE odemeTipi <1 AND (sTarih ='" + datepicker.getValue() + "')");
//
//        BigDecimal sumFiyat = (BigDecimal) qee.uniqueResult();
//        cirolbl.setText(String.valueOf(sumFiyat));
//
//        datepicker.getValue();
//
//        ts.commit();
//        ss.close();
//        
//        
//    
//           
//       }
//
//
//
//
//  
// 
//               
//
//        
//    
//
//        
//            
//
//        
//    
//    @FXML
//    private void printIt() {
//
//        Printer printer = Printer.getDefaultPrinter();
//        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
//        double scaleX = pageLayout.getPrintableWidth() / satist.getBoundsInParent().getWidth();
//        double scaleY = pageLayout.getPrintableHeight() / satist.getBoundsInParent().getHeight();
//        satist.getTransforms().add(new Scale(1, 1));
//
//        PrinterJob job = PrinterJob.createPrinterJob();
//        if (job != null) {
//            boolean successPrintDialog = job.showPrintDialog(null);
//            if (successPrintDialog) {
//                boolean success = job.printPage(pageLayout, satist);
//                if (success) {
//                    job.endJob();
//
//                }
//            }
//
//        }
//
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//
//    }
//
//}

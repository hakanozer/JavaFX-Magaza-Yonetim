package stokblue;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.transform.Scale;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TableView<Stoklar> table;
    @FXML
    private TableColumn<Stoklar, Integer> StokID;
    @FXML
    private TableColumn<Stoklar, Integer> SuperID;
    @FXML
    private TableColumn<Stoklar, Integer> UrunID;
    @FXML
    private TableColumn<Stoklar, Integer> GelenAdet;
    @FXML
    private TableColumn<Stoklar, Integer> KalanAdet;
    @FXML
    private TableColumn<Stoklar, Date> Tarih;
    @FXML
    private Button btnSil;
    @FXML
    private Button btnDuzenle;
    @FXML
    private TextField txtUrunID;
    @FXML
    private TextField txtGelenAdet;
    private TextField txtTarih;
    @FXML
    private Button btnEkle;

    ObservableList<Stoklar> data;
    ArrayList<Stoklar> list = new ArrayList<>();
    int secilenId;

    @FXML
    private void ekle(ActionEvent event) {

        dataInsert();
    }

    @FXML
    private void btnSil(ActionEvent event) {
        dataSil();
    }

    @FXML
    private void selectAction(ActionEvent event) {
        dataSelect();
    }

    @FXML
    private void yazdir(ActionEvent event) {
        printIt();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StokID.setCellValueFactory(new PropertyValueFactory<>("stokID"));
        UrunID.setCellValueFactory(new PropertyValueFactory<>("urunID"));
        GelenAdet.setCellValueFactory(new PropertyValueFactory<>("gelenAdet"));
        KalanAdet.setCellValueFactory(new PropertyValueFactory<>("kalanAdet"));
        Tarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));

        data = FXCollections.observableArrayList();
        tabloDoldur();
    }

    public void tabloDoldur() {

        data = FXCollections.observableArrayList();

        try {
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session sess = sf.openSession();
            Transaction ts = sess.beginTransaction();

            Query qee = sess.createQuery("from Stoklar");
            Iterator ite = qee.iterate();
            while (ite.hasNext()) {
                Stoklar obj = (Stoklar) ite.next();
                System.out.println(obj);
                data.add(obj);
                list.add(obj);
            }
            table.setItems(data);
            ts.commit();
            sess.close();
        } catch (HibernateException he) {
            System.out.println(he);
        }
    }

    public void dataSil() {
        
        int idIndex = 0;
        idIndex = table.getSelectionModel().getSelectedIndex();
        if (idIndex == -1) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Lütfen Tablodan silinecek olan veriyi seçiniz", ButtonType.OK);
            alert.setHeaderText(null);
            alert.setTitle("Uyarı");
            alert.showAndWait();
        }else{
        

        idIndex = table.getSelectionModel().getSelectedIndex();
        Stoklar md = list.get(idIndex);

        Alert alert = new Alert(Alert.AlertType.WARNING, "Silmek için OK, iptal etmek için CANCEL butonuna basınız.", ButtonType.OK, ButtonType.CANCEL);
        alert.setHeaderText("Silmek istediğinizden emin misiniz?");
        alert.setTitle("Uyarı");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    SessionFactory sf = new Configuration().configure().buildSessionFactory();
                    Session sess = sf.openSession();
                    Transaction ts = sess.beginTransaction();
                    sess.delete(md);
                    ts.commit();
                    sess.flush();
                    table.getSelectionModel().clearSelection();
                    list.clear();
                    tabloDoldur();
                    Alert alert2 = new Alert(Alert.AlertType.WARNING, "Silme Başarılı", ButtonType.OK);
                    alert2.setHeaderText(null);
                    alert2.setTitle("Uyarı");
                    alert2.showAndWait();
                    txtUrunID.setText("");
                    txtGelenAdet.setText("");

                } catch (HibernateException he) {
                    System.out.println(he);
                }
            }
        });
    }}

    @FXML
    public void dataSelect() {
        try {

            int idIndex = table.getSelectionModel().getSelectedIndex();

            Stoklar stokObj = list.get(idIndex);
            secilenId = stokObj.getStokID();
            txtUrunID.setText(stokObj.getUrunID().toString());
            txtGelenAdet.setText(stokObj.getGelenAdet().toString());
           

            System.out.println(secilenId);
        } catch (Exception e) {
            System.out.println("Seçme hatası : " + e);
        }
    }

    public void dataInsert() {
        if (txtUrunID.getText().equals("") || txtGelenAdet.getText().equals("")) {
            
            Alert alert = new Alert(Alert.AlertType.WARNING, "Lütfen Boş Alanları Doldurunuz.", ButtonType.OK);
            alert.setHeaderText(null);
            alert.setTitle("Uyarı");
            alert.showAndWait();
            txtUrunID.requestFocus();
        }

        else {
            if(sayikontrol(txtUrunID.getText()) == false){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Barkod Rakamlardan Oluşmak Zorunda", ButtonType.OK);
            alert.setHeaderText(null);
            alert.setTitle("Uyarı");
            alert.showAndWait();
            txtUrunID.requestFocus();         
            }
            else if(!sayikontrol(txtGelenAdet.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Gelen Adet Rakamlardan Oluşmak Zorunda", ButtonType.OK);
            alert.setHeaderText(null);
            alert.setTitle("Uyarı");
            alert.showAndWait();
            txtGelenAdet.requestFocus();
            }
            else{
            try {
                System.out.println("veysel");
                SessionFactory sf = new Configuration().configure().buildSessionFactory();
                Session sess = sf.openSession();
                Transaction ts = sess.beginTransaction();
                Stoklar k = new Stoklar();

                int urunID = Integer.parseInt(txtUrunID.getText());
                short gelenAdet = Short.parseShort(txtGelenAdet.getText());
                k.setStokID(Integer.SIZE);

                k.setUrunID(urunID);
                k.setGelenAdet(gelenAdet);
                Date dt = new Date();
                k.setTarih(dt);

                sess.save(k);
                ts.commit();
                sess.close();
                table.getSelectionModel().clearSelection();
                list.clear();
                tabloDoldur();
                Alert alert = new Alert(Alert.AlertType.WARNING, "Ekleme Başarılı", ButtonType.OK);
                alert.setHeaderText(null);
                alert.setTitle("Uyarı");
                alert.showAndWait();
                txtUrunID.setText("");
                txtGelenAdet.setText("");

            } catch (HibernateException he) {
                System.out.println(he);
            }}
        }
    }

    @FXML
    public void dataUpdate() {
        boolean durum = true;
        if(secilenId==0){//
            Alert alert = new Alert(Alert.AlertType.WARNING, "Lütefen Tablodan Düzeltilecek Veriyi Seçiniz.", ButtonType.OK);
                alert.setHeaderText(null);
                alert.setTitle("Uyarı");
                alert.showAndWait();
                durum = false;
            
            }
        else if (txtUrunID.equals("") || txtGelenAdet.equals("")) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Lütfen Boş Alanları Doldurunuz.", ButtonType.OK);
                alert.setHeaderText(null);
                alert.setTitle("Uyarı");
                alert.showAndWait();
                durum = false;
                
            }
        
        
        else{
            if(!sayikontrol(txtUrunID.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Barkod Rakamlardan Oluşmak Zorunda", ButtonType.OK);
            alert.setHeaderText(null);
            alert.setTitle("Uyarı");
            alert.showAndWait();
            txtUrunID.requestFocus();         
            }
             if(!sayikontrol(txtGelenAdet.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Gelen Adet Rakamlardan Oluşmak Zorunda", ButtonType.OK);
            alert.setHeaderText(null);
            alert.setTitle("Uyarı");
            alert.showAndWait();
            txtGelenAdet.requestFocus();
            }
        System.out.println("data update çalıştı");
        try {
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session sess = sf.openSession();
            Transaction ts = sess.beginTransaction();

            Stoklar md = new Stoklar();
            md.setStokID(secilenId);

            int urunID = Integer.parseInt(txtUrunID.getText());
            short gelenAdet = Short.parseShort(txtGelenAdet.getText());

            //md.setSuperID(urunID);
            md.setUrunID(urunID);
            md.setGelenAdet(gelenAdet);
            Date date = new Date();
            md.setTarih(date);

            sess.update(md);
            ts.commit();
            sess.close();
            table.getSelectionModel().clearSelection();
            list.clear();
            Alert alert = new Alert(Alert.AlertType.WARNING, "Güncelleme Başarılı.", ButtonType.OK);
            alert.setHeaderText(null);
            alert.setTitle("Uyarı");
            alert.showAndWait();
            tabloDoldur();
            txtUrunID.setText("");
            txtGelenAdet.setText("");
          

        } catch (HibernateException | NumberFormatException e) {
            System.out.println("data güncelleme hatası : " + e);
        }}

    }

    @FXML
    private void printIt() {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.EQUAL);
        table.getTransforms().add(new Scale(1.0, 1.0));
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            boolean successPrintDialog = job.showPrintDialog(null);
            if (successPrintDialog) {
                boolean success = job.printPage(pageLayout, table);
                if (success) {
                    job.endJob();
                    JOptionPane.showMessageDialog(null, "Yazdırıldı");

                }
            }
        }

    }

    public static boolean sayikontrol(String data) {
        boolean durum = false;
        for (int i = 0; i < data.length(); i++) {
            if (Character.isDigit(data.charAt(i))) {
                durum = true;
            } else {
                durum = false;
                break;
            }
        }
        return durum;

    }

}

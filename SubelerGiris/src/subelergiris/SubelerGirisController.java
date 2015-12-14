package subelergiris;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SubelerGirisController implements Initializable {

    private static Integer secilenID = null;
    private static Integer sID = null;

    public static Integer getsID() {
        return sID;
    }

    public static void setsID(Integer superID) {
        SubelerGirisController.sID = superID;
    }

    public static Integer getSecilenID() {
        return secilenID;
    }

    public static void setSecilenID(Integer secilenID) {
        SubelerGirisController.secilenID = secilenID;
    }
    @FXML
    private Button bt_subeDuzenle;
    @FXML
    private Button bt_subeSil;
    @FXML
    private Button bt_subeEkle;
    @FXML
    private TableView tv_subelerGiris;

    public TableView getTv_subelerGiris() {
        return tv_subelerGiris;
    }
    @FXML
    private TableColumn<Subeler, String> cl_subeID;
    @FXML
    private TableColumn<Subeler, String> cl_superID;
    @FXML
    private TableColumn<Subeler, String> cl_baslik;
    @FXML
    private TableColumn<Subeler, String> cl_adres;
    @FXML
    private TableColumn<Subeler, String> cl_telefon;
    @FXML
    private TableColumn<Subeler, String> cl_mail;
    @FXML
    private TableColumn<Subeler, String> cl_tarih;
    
    @FXML
    private void cikis(ActionEvent evt) {
        System.exit(1);
    }

    @FXML
    public void ekleBasildi(ActionEvent evt) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("YeniSube.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    if(!YeniSubeController.icerikVarmi){
                        subeyeDon();
                    }else{
                         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("DİKKAT");
                            alert.setHeaderText("EKLEME UYARISI");
                            alert.setContentText("kaydedilmemiş değişiklikler var.Çıkmak istediğinize emin misiniz?");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                            subeyeDon();
                            }else{
                                event.consume();
                            }
                    }

                    
                } catch (IOException ex) {
                    Logger.getLogger(SubelerGirisController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        stage.setScene(scene);
        stage.show();
        ((Node) evt.getSource()).getScene().getWindow().hide();
    }

    @FXML
    public void duzenleBasildi(ActionEvent evt) throws IOException {
        if (getSecilenID() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("DİKKAT !!!");
            alert.setHeaderText("Önce bir şube seçin!");

            alert.showAndWait();
        } else {
            
            Parent root = FXMLLoader.load(getClass().getResource("SubeDuzenle.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            setSecilenID(null);
            ((Node) evt.getSource()).getScene().getWindow().hide();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    try {

                        if (SubeDuzenleController.icerikDegisimi) {

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("DİKKAT !!!");
                            alert.setHeaderText("WARNING");
                            alert.setContentText("kaydedilmemiş değişiklikler var.Çıkmak istediğinize emin misiniz?");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {

                                ((Node) evt.getSource()).getScene().getWindow().hide();
                                 subeyeDon();
                            } else {

                                event.consume();

                            }
                        } else {
                            System.out.println("herhangi bi değişiklik yok ekran kapatılıyor");
                            ((Node) evt.getSource()).getScene().getWindow().hide();
                            subeyeDon();
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(SubelerGirisController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
        }
    }
private void subeyeDon() throws IOException{
    Parent root = FXMLLoader.load(getClass().getResource("SubelerGiris.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
       stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

        @Override
        public void handle(WindowEvent event) {

            SessionFactory sf = NewHibernateUtil.getSessionFactory();
            Session session = sf.openSession();
            session.close();
            sf.close();
        }
    });
}
    @FXML
    public void silBasildi(ActionEvent evt) throws IOException {
        if (getSecilenID() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("DİKKAT !!!");
            alert.setHeaderText("Önce bir şube seçin!");

            alert.showAndWait();

        } else {
            try {
                SessionFactory sf2 = NewHibernateUtil.getSessionFactory();
                Session session = sf2.openSession();
                Subeler st = (Subeler) tv_subelerGiris.getSelectionModel().getSelectedItem();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("DİKKAT !!!");
                alert.setHeaderText("WARNING");
                alert.setContentText(st.getSBaslik() + " isimli şube silinsin mi ?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {

                    Transaction trans = session.beginTransaction();
                    session.delete(st);
                    System.out.println("silme başarılı");
                    //   setSecilenID(null);
                    //   setsID(null);
                    trans.commit();
                    setSecilenID(null);
                    tabloGiris();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @FXML
    private void printIt() {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
        double scaleX = pageLayout.getPrintableWidth() / tv_subelerGiris.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / tv_subelerGiris.getBoundsInParent().getHeight();
        tv_subelerGiris.getTransforms().add(new Scale(scaleX, scaleY));

        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            boolean successPrintDialog = job.showPrintDialog(null);
            if (successPrintDialog) {
                boolean success = job.printPage(pageLayout, tv_subelerGiris);
                if (success) {
                    job.endJob();
                    System.out.println("Yazdırıldı");
                }
            }
        }
    }

    @FXML
    protected void tabloGiris() {
        try {
            ObservableList<Subeler> sube = FXCollections.observableArrayList();
            SessionFactory sf3 = NewHibernateUtil.getSessionFactory();
            Session session = sf3.openSession();
            Query query = session.createQuery("from Subeler");
            List<Subeler> list = query.list();
            for (Subeler item : list) {
                Subeler s = new Subeler();
                s.setSuperID(item.getSuperID());
                s.setSAdres(item.getSAdres());
                s.setSBaslik(item.getSBaslik());
                s.setSMail(item.getSMail());
                s.setSTarih(item.getSTarih());
                s.setSTelefon(item.getSTelefon());
                s.setSubeID(item.getSubeID());
                sube.add(s);
            }
            cl_subeID.setCellValueFactory(new PropertyValueFactory<Subeler, String>("subeID"));
            cl_baslik.setCellValueFactory(new PropertyValueFactory<Subeler, String>("sBaslik"));
            cl_mail.setCellValueFactory(new PropertyValueFactory<Subeler, String>("sMail"));
            cl_tarih.setCellValueFactory(new PropertyValueFactory<Subeler, String>("sTarih"));
            cl_adres.setCellValueFactory(new PropertyValueFactory<Subeler, String>("sAdres"));
            cl_telefon.setCellValueFactory(new PropertyValueFactory<Subeler, String>("sTelefon"));
            cl_superID.setCellValueFactory(new PropertyValueFactory<Subeler, String>("superID"));
            tv_subelerGiris.setItems(sube);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tabloGiris();
        tv_subelerGiris.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Subeler>() {

            @Override
            public void changed(ObservableValue<? extends Subeler> observable, Subeler oldValue, Subeler newValue) {
                try {
                    setSecilenID(newValue.getSubeID());
                    setsID(newValue.getSuperID());
                    System.out.println(newValue.getSBaslik());

                } catch (Exception e) {
                    System.out.println("hata " + e);
                }

            }
        });
    }
}

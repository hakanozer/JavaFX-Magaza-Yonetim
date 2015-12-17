package subelergiris;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import hibernetVeritabani.NewHibernateUtil;
import hibernetVeritabani.Subeler;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SubelerGirisController implements Initializable, MapComponentInitializedListener {

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
    private Button haritaBtn;
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
    GoogleMap map;
    GoogleMapView mapView;

    ArrayList<Subeler> liste = new ArrayList<>();

    @FXML
    private void haritaBtn(ActionEvent evt) throws IOException {

        latLongGetir();
        Stage stage = new Stage();
        mapView = new GoogleMapView();
        mapView.addMapInializedListener(this);
        stage.setScene(new Scene(mapView));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();

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
                    if (!YeniSubeController.icerikVarmi) {
                        subeyeDon();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("DİKKAT");
                        alert.setHeaderText("EKLEME UYARISI");
                        alert.setContentText("kaydedilmemiş değişiklikler var.Çıkmak istediğinize emin misiniz?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            subeyeDon();
                        } else {
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

    private void subeyeDon() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SubelerGiris.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
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
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.EQUAL);
        tv_subelerGiris.getTransforms().add(new Scale(1.0, 1.0));
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            boolean successPrintDialog = job.showPrintDialog(null);
            if (successPrintDialog) {
                boolean success = job.printPage(pageLayout, tv_subelerGiris);
                if (success) {
                    job.endJob();
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
                liste.add(s);
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

        try {
            latLongGetir();
        } catch (IOException ex) {
            Logger.getLogger(SubelerGirisController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabloGiris();
        tv_subelerGiris.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Subeler>() {

            @Override
            public void changed(ObservableValue<? extends Subeler> observable, Subeler oldValue, Subeler newValue) {
                try {
                    setSecilenID(newValue.getSubeID());
                    setsID(newValue.getSuperID());

                } catch (Exception e) {
                    System.out.println("hata " + e);
                }

            }
        });
    }

    @Override
    public void mapInitialized() {

        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(lat, lon));
        mapOptions.mapType(MapTypeIdEnum.ROADMAP);
        mapOptions.overviewMapControl(false);
        mapOptions.panControl(false);
        mapOptions.rotateControl(false);
        mapOptions.scaleControl(false);
        mapOptions.streetViewControl(false);
        mapOptions.zoomControl(false);
        mapOptions.zoom(12);

        map = mapView.createMap(mapOptions);

        //Marker
        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(new LatLong(lat, lon))
                .visible(Boolean.TRUE)
                .title("My Marker");

        Marker marker = new Marker(markerOptions);

        map.addMarker(marker);

    }

    private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json";

    public GoogleResponse convertToLatLong(String fullAddress) throws IOException {

        URL url = new URL(URL + "?address="
                + URLEncoder.encode(fullAddress, "UTF-8") + "&sensor=false");
        // Open the Connection
        URLConnection conn = url.openConnection();
        InputStream in = conn.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        GoogleResponse response = (GoogleResponse) mapper.readValue(in, GoogleResponse.class);
        in.close();
        return response;

    }
    Double lat;
    Double lon;

    public void latLongGetir() throws IOException {
        GoogleResponse res = new GoogleResponse();
        res = convertToLatLong("\"" + adres + "\"");
        if (res.getStatus().equals("OK")) {
            for (Result result : res.getResults()) {

                lat = Double.valueOf(result.getGeometry().getLocation().getLat());
                lon = Double.valueOf(result.getGeometry().getLocation().getLng());

            }
        }

    }
    String adres;

    @FXML
    public void dataSelect() {
        int idIndex = tv_subelerGiris.getSelectionModel().getSelectedIndex();

         Subeler sube = liste.get(idIndex);
        adres = sube.getSAdres();

    }

}

package persnldznl;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Murat E. Taşcı
 */
public class FXMLDocumentController implements Initializable {

    private static int secilenID;

    public static int getSecilenID() {
        return secilenID;
    }

    public static void setSecilenID(int secilenID) {
        FXMLDocumentController.secilenID = secilenID;
    }
    
    @FXML
    private TableColumn<Personel, String> prsID;
    @FXML
    private TableColumn<Personel, String> prsKonum;
    @FXML
    private TableColumn<Personel, String> prsKulAdi;
    @FXML
    private TableColumn<Personel, String> prsSifre;
    @FXML
    private TableColumn<Personel, String> prsAdi;
    @FXML
    private TableColumn<Personel, String> prsSoyadi;
    @FXML
    private TableColumn<Personel, String> prsTel;
    @FXML
    private TableColumn<Personel, String> prsCep;
    @FXML
    private TableColumn<Personel, String> prsMail;
    @FXML
    private TableColumn<Personel, String> prsAdres;
    @FXML
    private Button prsDznlBtn;
    @FXML
    private TableView tviewGiris;
    
    
    @FXML
    private void DuzenleBtn(ActionEvent event) throws IOException {
        if(getSecilenID() == 0){
            Alert alert = new Alert(Alert.AlertType.NONE, "Tablodan Seçim Yapınız " , ButtonType.OK);
            alert.setTitle("Seçim Yapmadınız!.");
            alert.showAndWait();
            
        }else{
        Parent dznSyf = FXMLLoader.load(getClass().getResource("FXMLPersonelDuzenlemeSayfasi.fxml"));
        Scene scene = new Scene(dznSyf);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
        }

    }

    @FXML
    private void tabloGiris() {
        try {
            
            SessionFactory sf = NewHibernateUtil.getSessionFactory();
            Session ss = sf.openSession();
            
            Query sorgu = ss.createQuery("from Personel");
            ObservableList<Personel> obsPers = FXCollections.observableArrayList();
            List<Personel> liste = sorgu.list();
            for (Personel item : liste) {
                Personel prs = new Personel();
                prs.setKonum(item.getKonum());
                prs.setPAdi(item.getPAdi());
                prs.setPAdres(item.getPAdres());
                prs.setPCep(item.getPCep());
                prs.setPKulAdi(item.getPKulAdi());
                prs.setPMail(item.getPMail());
                prs.setPSifre(item.getPSifre());
                prs.setPSoyadi(item.getPSoyadi());
                prs.setPTelefon(item.getPTelefon());
                prs.setPersonelID(item.getPersonelID());
                obsPers.add(prs);
            }
            prsAdi.setCellValueFactory(new PropertyValueFactory<Personel, String>("pAdi"));
            prsAdres.setCellValueFactory(new PropertyValueFactory<Personel, String>("pAdres"));
            prsCep.setCellValueFactory(new PropertyValueFactory<Personel, String>("pCep"));
            prsID.setCellValueFactory(new PropertyValueFactory<Personel, String>("personelID"));
            prsKonum.setCellValueFactory(new PropertyValueFactory<Personel, String>("konum"));
            prsKulAdi.setCellValueFactory(new PropertyValueFactory<Personel, String>("pKulAdi"));
            prsMail.setCellValueFactory(new PropertyValueFactory<Personel, String>("pMail"));
            prsSifre.setCellValueFactory(new PropertyValueFactory<Personel, String>("pSifre"));
            prsSoyadi.setCellValueFactory(new PropertyValueFactory<Personel, String>("pSoyadi"));
            prsTel.setCellValueFactory(new PropertyValueFactory<Personel, String>("pTelefon"));
            tviewGiris.getItems().addAll(obsPers);
            ss.close();
//            sf.close();
        } catch (Exception e) {
            System.err.println("tabloGiris() hatası"+e);
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tabloGiris();
        
        tviewGiris.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Personel>() {

            @Override
            public void changed(ObservableValue<? extends Personel> observable, Personel oldValue, Personel newValue) {
                setSecilenID(newValue.getPersonelID());
                System.out.println("ID : " + getSecilenID());
            }
        });
        
    }

}

package persnldznl;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * FXML Controller class
 *
 * @author Murat E. Taşcı
 */
public class FXMLPersonelDuzenlemeSayfasiController implements Initializable {

    private int gelenID;

    public int getGelenID() {
        return gelenID;
    }

    public void setGelenID(int gelenID) {
        this.gelenID = gelenID;
    }

    @FXML
    Label lblKonum;
    @FXML
    Label lblKonumUyari1;
    @FXML
    Label lblKonumUyari2;
    @FXML
    Label lblKAdi;
    @FXML
    Label lblsifre;
    @FXML
    Label lblPrsAdi;
    @FXML
    Label lblPrsSoyadi;
    @FXML
    Label lblTel;
    @FXML
    Label lblTelUyari;
    @FXML
    Label lblCep;
    @FXML
    Label lblCepUyari;
    @FXML
    Label lblMail;
    @FXML
    Label lblMailUyari;
    @FXML
    Label lblAdres;

    @FXML
    TextField dznKonum;
    @FXML
    TextField dznKAdi;
    @FXML
    TextField dznSifre;
    @FXML
    TextField dznAdi;
    @FXML
    TextField dznSoyadi;
    @FXML
    TextField dznTel;
    @FXML
    TextField dznCep;
    @FXML
    TextField dznMail;
    @FXML
    TextArea dznAdres;

    @FXML
    Button kaydetBtn;

    @FXML
    private void dznKaydet(ActionEvent event) throws IOException {
        boolean dknm;
        boolean dtel;
        boolean dcep;
        boolean dmail;
        if (!dznKonum.getText().matches("(?simx)(?<!\\S)\\d++(?!\\S)")) {
            dknm = true;
            lblKonumUyari1.setVisible(true);
            lblKonumUyari2.setVisible(true);
        }else{
            dknm = false;
            lblKonumUyari1.setVisible(false);
            lblKonumUyari2.setVisible(false);
        } if (!dznTel.getText().matches("(?simx)(?<!\\S)\\d++(?!\\S)")) {
            dtel = true;
            lblTelUyari.setVisible(true);
        }else{
            dtel = false;
            lblTelUyari.setVisible(false);
        } if (!dznCep.getText().matches("(?simx)(?<!\\S)\\d++(?!\\S)")) {
            dcep = true;
            lblCepUyari.setVisible(true);
        }else{
            dcep = false;
            lblCepUyari.setVisible(false);
        } if (!dznMail.getText().matches("(?simx)\\b[A-Z0-9._%+-]+" + "@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b")) {
            dmail = true;
            lblMailUyari.setVisible(true);
        }else{
            dmail = false;
            lblMailUyari.setVisible(false);
        }
        if(dknm == false && dtel == false && dcep == false && dmail == false){
            SessionFactory sf = NewHibernateUtil.getSessionFactory();
            Session ss = sf.openSession();
            Personel prsnl = new Personel();
            prsnl.setPersonelID(gelenID);
            prsnl.setKonum(Short.parseShort(dznKonum.getText()));
            prsnl.setPAdi(dznAdi.getText());
            prsnl.setPAdres(dznAdres.getText());
            prsnl.setPCep(dznCep.getText());
            prsnl.setPKulAdi(dznKAdi.getText());
            prsnl.setPMail(dznMail.getText());
            prsnl.setPSifre(dznSifre.getText());
            prsnl.setPSoyadi(dznSoyadi.getText());
            prsnl.setPTelefon(dznTel.getText());

            Transaction tr = ss.beginTransaction();
            ss.update(prsnl);
            tr.commit();
            ss.close();

            Parent dznSyf = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene scene = new Scene(dznSyf);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        }
    }
    private void secGetir() {
        setGelenID(FXMLDocumentController.getSecilenID());
        try {
            SessionFactory sf = NewHibernateUtil.getSessionFactory();
            Session ss = sf.openSession();
            Query sorgu = ss.createQuery("from Personel where personelID = " + getGelenID());
            List<Personel> list = sorgu.list();

            dznKonum.setText(list.get(0).getKonum().toString());
            dznKAdi.setText(list.get(0).getPKulAdi());
            dznSifre.setText(list.get(0).getPSifre());
            dznAdi.setText(list.get(0).getPAdi());
            dznSoyadi.setText(list.get(0).getPSoyadi());
            dznTel.setText(list.get(0).getPTelefon());
            dznCep.setText(list.get(0).getPCep());
            dznMail.setText(list.get(0).getPMail());
            dznAdres.setText(list.get(0).getPAdres());

            ss.close();
            //   sf.close();
        } catch (Exception e) {
            System.err.println("secGetir Hatası : " + e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblKonumUyari1.setVisible(false);
        lblKonumUyari2.setVisible(false);
        lblTelUyari.setVisible(false);
        lblCepUyari.setVisible(false);
        lblMailUyari.setVisible(false);
        secGetir();

    }

}

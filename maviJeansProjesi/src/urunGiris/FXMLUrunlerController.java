/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urunGiris;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;

public class FXMLUrunlerController implements Initializable {

    @FXML
    private Label lblUrunBeden2;

    @FXML
    private Label lblUrunRenk;

    @FXML
    private Label lblUrunTarih2;

    @FXML
    private Label lblUrunBeden;

    @FXML
    private Label lblUrunBarkod2;

    @FXML
    private Label lblUrunTarih;

    @FXML
    private Label lblUrunRenk2;

    @FXML
    private Label lblUrunAdi2;

    @FXML
    private Label lblUrunRenk1;

    @FXML
    private Label lblUrunAdi;

    @FXML
    private Label lblResim;

    @FXML
    private Label lblUrunSezon;

    @FXML
    private ComboBox<String> cmbBeden;

    @FXML
    private Label lblUrunSezon2;

    @FXML
    private TextField txtUrunBarkod;

    @FXML
    private ComboBox<?> cmbSezon;

    @FXML
    private Label lblUrunAciklama;

    @FXML
    private Label lblUrunAciklama2;

    @FXML
    private ComboBox<?> cmbRenk;

    @FXML
    private Label lblUrunFiyat2;

    @FXML
    private Label lblurunFiyat;

    @FXML
    private Button btnResimSec;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void resimSec() {

        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(btnResimSec.getScene().getWindow());

        try {
            String c = f.toURI().toURL().toExternalForm();
            
            
            ImageView livePerformIcon = new ImageView(c);
            livePerformIcon.setFitHeight(lblResim.getHeight());
            livePerformIcon.setFitWidth(lblResim.getWidth());
            lblResim.setGraphic(livePerformIcon);
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLUrunlerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

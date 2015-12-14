/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package satisiptal;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author javaSabah
 */
public class FXMLYoneticiGirisController implements Initializable {

    @FXML
    private TextField yKAdi;
    @FXML
    private TextField yKSifresi;
    @FXML
    private Button btnGiris;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    MsSqlCon db = new MsSqlCon();

    @FXML
    private void yoneticiGirisi(ActionEvent event) throws NoSuchAlgorithmException {
        String deger="yetkisiz";
      
        ResultSet rs = db.yoneticiGirisi(yKAdi.getText(), db.md5(yKSifresi.getText()));
        try {
            while(rs.next())
            {
                Parent root = FXMLLoader.load(getClass().getResource("FXMLmain.fxml"));
                Stage stage=new Stage();
                Scene scene = new Scene(root);
                stage.setTitle("Ürün İade Ekranı");
                stage.setScene(scene);
                ((Node)event.getSource()).getScene().getWindow().hide();
                stage.show();
                 deger="yetkili";
            }
            
                if(deger.equals("yetkisiz"))
                {
                    JOptionPane.showMessageDialog(null, "Hatalı Kullanıcı Adı veya Şifre");
                }
               
         

        } catch (Exception e) {
        }

    }

}

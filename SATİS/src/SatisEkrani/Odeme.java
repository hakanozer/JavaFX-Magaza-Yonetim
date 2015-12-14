
package SatisEkrani;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


public class Odeme implements Initializable {

    @FXML
    private Label lblLogo;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblLogo.setGraphic(new ImageView("images/maviLOGO.png"));
    }
    
    
  
}

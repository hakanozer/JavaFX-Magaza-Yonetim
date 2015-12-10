
package SatisEkrani;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


public class OdemeController implements Initializable {

    @FXML
    private Label lblLogo;
    public String ref;
    @FXML public Label lblReferans;


    public void getRefCode(String ref) {
        this.ref = ref;
        lblReferans.setText(lblReferans.getText()+"  "+ref);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblLogo.setGraphic(new ImageView("images/maviLOGO.png"));

      
      
    }
 
    
  
}

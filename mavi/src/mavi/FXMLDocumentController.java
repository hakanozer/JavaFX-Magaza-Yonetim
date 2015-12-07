package mavi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Label lblResim;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        Image resim = new Image(getClass().getResourceAsStream("C:\\Mavi.jpg"));
        lblResim.setGraphic(new ImageView("Mavi.jpg"));

    }

}

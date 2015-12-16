/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mudurgiris;

import static javafx.application.Application.launch;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Ahmet KAZĞIN
 */
public class yonlendirController extends Exception{
     @FXML
    public Integer ID;
@FXML
private Label label;

    public void getRefCode(Integer sID) {
        this.ID = sID;
        label.setText(ID.toString());
    }

    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
    public static void main(String[] args) {
        launch(args);
    }

    
   } 


package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author MetinB
 */
public class FXMLIndexController implements Initializable {

  
    double obje = -150;
   
    double frm = 0;
    @FXML
    private AnchorPane anch;
    @FXML
    private Pane panee;
    @FXML
    private Button kasaKapa;
    @FXML
    private Button perEkle;
    @FXML
    private Button terziKont;
    @FXML
    private Button satisIadeIslemi;
    @FXML
    private Button personelDuzenle;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        kasaKapa.setGraphic(new ImageView("/img/kapat.png"));
        perEkle.setGraphic(new ImageView("/img/ekle.png"));
        terziKont.setGraphic(new ImageView("/img/terzi.png"));
        personelDuzenle.setGraphic(new ImageView("/img/duzenle.png"));
        satisIadeIslemi.setGraphic(new ImageView("/img/cikar.png"));
        timerK();
         
    }

    @FXML
    private void kasaKapat(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/oldu/oldu.fxml"));
//        Stage stage =new Stage();
//        Scene scene = new Scene(root);
//        stage.setTitle("Şube Kontrol");
//        stage.setScene(scene);
//        stage.show();

      
        

    }
    
    
    @FXML
    private void personelEkle(ActionEvent event) {
        
        
    }
    
    @FXML
    private void satisIadeIslemleri(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/satisiptal/FXMLmain.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Şube Kontrol");
        stage.setScene(scene);
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void personelDuzenle(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/persnldznl/FXMLDocument.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Personel Düzenle");
        stage.setScene(scene);
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();

    }

    @FXML
    private void terziKontrol(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafxapplication7/FXMLDocument.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Personel Düzenle");
        stage.setScene(scene);
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    Timer tm;
    TimerTask ts;
    boolean durum = false;
    public void timerK() {
        
        tm = new Timer();
        ts = new TimerTask() {
            @Override
            public void run() {
                if (obje < 200 && durum == false) {
                    obje = (obje - (obje / 20)) + 10;
                } else {
                    obje = (obje - (obje / 30));
                    durum = true;
                }
                panee.setLayoutX(obje);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FXMLIndexController.class.getName()).log(Level.SEVERE, null, ex);
                }
                obje++;
                if (obje < 130 && durum == true) {
                    tm.cancel();
                    ts.cancel();
                  
                }
            }
        };
        tm.schedule(ts, 0, 20);
    }


  
    

    
    
  
    
  
    
    
    
    
    
    
}


package SatisEkrani;



import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.Label;

import javafx.scene.image.ImageView;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



public class SatisController implements Initializable {
    
    
    @FXML
    private Button btnIptal;
    
    @FXML
    private Button btnBeklet;

    @FXML
    private Button btnIade;
    
    @FXML
    private Label lblBarkod;
    
    @FXML
    private Button btnSil;
   
    @FXML 
    private Label lblTl;
    
    @FXML
    private Label lblAlinanPara;
    
    @FXML
    private Label lblParaUstu;
    
    @FXML
    private Button btnKart;
    
    @FXML 
    private Button btnNakit;
 
    @FXML
    private void handleButtonAction(ActionEvent event) {
   
    }
    
    @FXML
    private void BarkodOkut(ActionEvent event) 
    {
//        String path = "BEEP.mp3";
//        Media media = new Media(new File(path).toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setAutoPlay(true);
//        MediaView mediaView = new MediaView(mediaPlayer);
//        mediaPlayer.play();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
//    btnIptal= new Button("fgnfgj" ,imageview); 
      btnIptal.setGraphic(new ImageView("images/DELETE.png"));
      btnIptal.setText("SATIŞ" +"\n" +"İPTAL");
      btnIade.setGraphic(new ImageView("images/RETURN.png"));
      btnIade.setText("SATIŞ" +"\n" + "İADE");
      lblBarkod.setGraphic(new ImageView("images/BARKOD.png"));
      lblTl.setGraphic(new ImageView("images/TL.png"));
      btnBeklet.setGraphic(new ImageView("images/WAIT.png"));
      btnBeklet.setText("SATIŞ" +"\n" +"BEKLET");
      btnSil.setGraphic(new ImageView("images/CLEAR.png"));
      lblAlinanPara.setGraphic(new ImageView("images/MONEY.png"));
      lblParaUstu.setGraphic(new ImageView("images/COINS.png"));
      btnKart.setGraphic(new ImageView("images/CARD.png"));
      btnNakit.setGraphic(new ImageView("images/BILLS.png"));
      
//      SessionFactory sf = NewHibernateUtil.getSessionFactory();
////        Session gsesi = sf.openSession();
//        Transaction tr = gsesi.beginTransaction();
//       tr.commit();
//       sf.close();
    }    
    
}

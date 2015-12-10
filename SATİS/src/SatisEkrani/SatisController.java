
package SatisEkrani;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class SatisController implements Initializable {
    
    
    @FXML private Button btnIptal;
    @FXML private TextField txtAlinanPara;
    @FXML private Button btnBeklet;
    @FXML private Button btnIade; 
    @FXML private Label lblBarkod; 
    @FXML private Button btnSil;
    @FXML private Label lblTl; 
    @FXML private Label lblAlinanPara;  
    @FXML private Label lblParaUstu;
    @FXML private Button btnKart;
    @FXML private Button btnNakit;
    @FXML private Label lblFiyat;
    @FXML private Label lblAdet;
    @FXML private Label lblTutar;
    @FXML private Label lblUrun;
    @FXML private TextField txtBarkod;
    @FXML private TextField txtParaUstu;
    @FXML private TableView table;
    @FXML private TableColumn<Urunler, String> uAdi;
    @FXML private TableColumn<Urunler, String> uKisaAciklama;
    @FXML private TableColumn<Urunler, BigDecimal> uFiyat;
    @FXML private TableColumn<Urunler, String> sezon;
    @FXML private TableColumn<Urunler, String> beden;
    @FXML private TableColumn<Urunler, String> renk;
    @FXML private TableColumn<Urunler, String> uTarih;
    @FXML private TableColumn<Urunler, Integer> adet;
    @FXML private TableColumn<Urunler, Integer> kalanAdet;
    ObservableList<Urunler> kliste = FXCollections.observableArrayList();
    ArrayList<Double> para = new  ArrayList<>();
    ArrayList<String> barkod = new  ArrayList<>();
    DB db = new DB();
   
    private Object stage;
     
     
    @FXML
    private void BarkodBul() {
        String number = txtBarkod.getText();
        if (!barkodKntrl(number).isEmpty()) {

            Property(number);
            TutarHesapla();
            SoundClipTest();
        }
else {
            JOptionPane.showMessageDialog(null, "Barkod Numarası Bulunmamaktadır!", "HATA!", JOptionPane.WARNING_MESSAGE);
            txtBarkod.setText("");
        }
        
    }
 
    public void SoundClipTest() {

        try {
            
            String musicFile = "BEEP.mp3";
            Media sound = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
            
        } catch (Exception e) {
            
            System.err.println("Hata : " + e);
        }
        
   }
    private void Property(String number) {
         try {
            ResultSet pro = db.preProGetir("satisBilgiEkrani", number);
            while (pro.next()) {
                 Urunler ur = new Urunler();
                ur.setUAdi(pro.getString("uAdi"));
                lblUrun.setText("  " + pro.getString("uAdi"));
                ur.setUKisaAciklama(pro.getString("uKisaAciklama"));
                ur.setUFiyat(pro.getBigDecimal("uFiyat"));
                lblTutar.setText(ur.getUFiyat().toString().substring(0, (ur.getUFiyat().toString().indexOf(".") + 3)) + " ");
                ur.setSezon(pro.getString("sBaslik"));
                ur.setBeden(pro.getString("bBaslik"));
                ur.setRenk(pro.getString("rAdi"));
                ur.setUTarih(pro.getString("tarih"));
                ur.setAdet(pro.getInt("adet"));
                lblAdet.setText(String.valueOf(pro.getInt("adet")) + " ");
                ur.setKalan(pro.getInt("kalanAdet"));
                
                kliste.add(ur);
                
                fillTable();
                txtBarkod.setText("");
                
            }
        } catch (SQLException ex) {
            System.out.println("Tablo Doldur hatası : " + ex);
        }
    }

    @FXML
    public void OdemeEkraniStart() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("OdemeEkrani.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
    private void TutarHesapla() throws NumberFormatException {
  
        para.add(Double.valueOf(lblTutar.getText().trim()));
        Double price = 0.00;
        for (Double items : para) {
            
            price += items;
            
            
        }
        lblFiyat.setText(price.toString() + "     ");
      
    }

    @FXML
    private String barkodKntrl(String barkodNo) {
        String i = "";

        ResultSet rs = null;
        try {
            rs = db.barkodKontrol(barkodNo);
            while (rs.next()) {
                i = rs.getString("barkodNo");
               

            }
            barkod.add(i);
        } catch (Exception e) {
            System.out.println("barkod hatası : " + e);
        }
//        for (int j = 0; j < (barkod.size() - 1); j++) {
//            if (barkod.get(j).equals(txtBarkod.getText())) {
//                i = "";
//            }
//        }

        return i;
    }

    @FXML
    private void fillTable() {
         table.setItems(kliste);
        for (Urunler item : kliste) {
           
            uAdi.setCellValueFactory(new PropertyValueFactory<>("uAdi"));
            uKisaAciklama.setCellValueFactory(new PropertyValueFactory<>("uKisaAciklama"));
            uFiyat.setCellValueFactory(new PropertyValueFactory<>("uFiyat"));
            sezon.setCellValueFactory(new PropertyValueFactory<>("sezon"));
            beden.setCellValueFactory(new PropertyValueFactory<>("beden"));
            renk.setCellValueFactory(new PropertyValueFactory<>("renk"));
            uTarih.setCellValueFactory(new PropertyValueFactory<>("uTarih"));
            adet.setCellValueFactory(new PropertyValueFactory<>("adet"));
            kalanAdet.setCellValueFactory(new PropertyValueFactory<>("kalan"));
        
        }

    }

    @FXML
    private void totalPara(ActionEvent event) {

        double a = getButtonName(event);

        if (txtAlinanPara.getText().equals("") || a == 0.00) {
            txtAlinanPara.setText("0.00");
        }
        double b = Double.valueOf(txtAlinanPara.getText());

        String deger = String.format("%.2f", (b + a));
        String txt = deger.replace(",", ".");
        txtAlinanPara.setText(txt);
        Double money = Double.valueOf(txt);
        Double total = Double.valueOf(lblFiyat.getText());
        double paraustu = money - total;
        String hesap = String.format("%.2f", paraustu);
        txtParaUstu.setText(hesap);
    }

    @FXML
    private void labelAdetYaz(ActionEvent event) {
        String btn = "";
        Button button = (Button) event.getSource();
        String btnName = button.getText();
        if (btnName.contains("")) {
            lblAdet.setText("");
        }
        btn = btnName.replace("s", "");

        lblAdet.setText(btn);
    }

    private double getButtonName(ActionEvent event) {

        String aa = "";
        double a = 0.00;
        Button button = (Button) event.getSource();
        String btnName = button.getText();
        if (btnName.contains("KRŞ")) {
            aa = btnName.replace("KRŞ", "");
            if (aa.length() == 2) {
                aa = "0.0" + aa;
            } else {
                aa = "0." + aa;
            }
        } else if (btnName.contains("TL")) {
            aa = btnName.replace("TL", "");
        } else if (btnName.contains("0,00")) {

            aa = "0.00";
        }
        a = Double.valueOf(aa);
        return a;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

//    btnIptal= new Button("fgnfgj" ,imageview); 
        btnIptal.setGraphic(new ImageView("images/DELETE.png"));
        btnIptal.setText("SATIŞ" + "\n" + "İPTAL");
        btnIade.setGraphic(new ImageView("images/RETURN.png"));
        btnIade.setText("SATIŞ" + "\n" + "İADE");
        lblBarkod.setGraphic(new ImageView("images/BARKOD.png"));
        lblTl.setGraphic(new ImageView("images/TL.png"));
        btnBeklet.setGraphic(new ImageView("images/WAIT.png"));
        btnBeklet.setText("SATIŞ" + "\n" + "BEKLET");
        btnSil.setGraphic(new ImageView("images/CLEAR.png"));
        lblAlinanPara.setGraphic(new ImageView("images/MONEY.png"));
        lblParaUstu.setGraphic(new ImageView("images/COINS.png"));
        btnKart.setGraphic(new ImageView("images/CARD.png"));
        btnNakit.setGraphic(new ImageView("images/BILLS.png"));

    }

}

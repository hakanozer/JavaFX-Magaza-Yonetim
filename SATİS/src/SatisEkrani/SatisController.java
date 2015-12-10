package SatisEkrani;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.rmi.server.UID;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

public class SatisController implements Initializable {

    @FXML
    private Label lblRef;
    @FXML
    private Button btnIptal;
    @FXML
    private TextField txtAlinanPara;
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
    private Label lblFiyat;
    @FXML
    private Label lblAdet;
    @FXML
    private Label lblTutar;
    @FXML
    private Label lblUrun;
    @FXML
    private TextField txtBarkod;
    @FXML
    private TextField txtParaUstu;
    @FXML
    private TableView table;
    @FXML
    private TableColumn<Urunler, String> uAdi;
    @FXML
    private TableColumn<Urunler, String> uKisaAciklama;
    @FXML
    private TableColumn<Urunler, BigDecimal> uFiyat;
    @FXML
    private TableColumn<Urunler, String> sezon;
    @FXML
    private TableColumn<Urunler, String> beden;
    @FXML
    private TableColumn<Urunler, String> renk;
    @FXML
    private TableColumn<Urunler, String> uTarih;
    @FXML
    private TableColumn<Urunler, Boolean> Terzi;
    @FXML
    private TableColumn<Urunler, Integer> adet;
    ObservableList<Urunler> kliste = FXCollections.observableArrayList();
    ArrayList<Double> para = new ArrayList<>();
    ArrayList<String> barkod = new ArrayList<>();
    DB db = new DB();

    private Object stage;
    @FXML
    private Button btn1Krs;
    @FXML
    private Button btn5Krs;
    @FXML
    private Button btn10Krs;
    @FXML
    private Button btn1Tl;
    @FXML
    private Button btn5Tl;
    @FXML
    private Button btn10Tl;
    @FXML
    private Button btn25Krs;
    @FXML
    private Button btn50Krs;
    @FXML
    private Button btn20Tl;
    @FXML
    private Button btn50Tl;
    @FXML
    private Button btn200Tl;
    @FXML
    private Button btn100Tl;
    @FXML
    private Button btnC;
    @FXML
    private Button s1;
    @FXML
    private Button s2;
    @FXML
    private Button s3;
    @FXML
    private Button s4;
    @FXML
    private Button s5;
    @FXML
    private Button s0;
    @FXML
    private Button s9;
    @FXML
    private Button s8;
    @FXML
    private Button s7;
    @FXML
    private Button s6;
      @FXML
    private Label lblKasa;
    @FXML
    private Personel per;

    public Personel getPer() {
        return per;
    }

    public void setPer(Personel per) {
        this.per = per;
        lblKasa.setText(per.getPAdi()+" "+per.getPSoyadi());
        
     
                
    }
    
    
    @FXML
    private void BarkodBul() {
        String number = txtBarkod.getText();
        if (!barkodKntrl(number).isEmpty()) {

            Property(number);
            TutarHesapla();
            SoundClipTest();
        } else {
            JOptionPane.showMessageDialog(null, "Barkod Numarası Bulunmamaktadır!", "HATA!", JOptionPane.WARNING_MESSAGE);
            txtBarkod.setText("");
        }

    }

    public UID generateRefCode() {
        UID cruUID = new UID();
        return cruUID;

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
    int i = 1;

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
                ur.setDurum(pro.getBoolean("durum"));
                lblAdet.setText(String.valueOf(pro.getInt("adet")) + " ");
                ur.setAdet(i);
                i++;

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
        Parent root = null;
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OdemeEkrani.fxml"));
        root = fxmlLoader.load();
        OdemeController cont = fxmlLoader.getController();
        cont.getRefCode(lblRef.getText());
        stage.setScene(new Scene(root));
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
            Terzi.setCellValueFactory(new PropertyValueFactory<Urunler, Boolean>("durum"));
            Terzi.setCellFactory(new Callback<TableColumn<Urunler, Boolean>, TableCell<Urunler, Boolean>>() {

                @Override
                public TableCell<Urunler, Boolean> call(TableColumn<Urunler, Boolean> p) {
                    return new CheckBoxTableCell<Urunler, Boolean>();
                }
                /*      Callback<TableColumn<Urunler, Boolean>, TableCell<Urunler, Boolean>> booleanCellFactory = 
                 new Callback<TableColumn<Urunler, Boolean>, TableCell<Urunler, Boolean>>() {
                 @Override
                 public TableCell<Urunler, Boolean> call(TableColumn<Urunler, Boolean> p) {
                 return new BooleanCell();
                 }
                 };*/

//            kalanAdet.setCellValueFactory(new PropertyValueFactory<>("kalan"));
            });
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
        lblRef.setText(generateRefCode().toString());
        btnIptal.setGraphic(new ImageView("images/DELETE.png"));
        btnIptal.setText("SATIŞ" + "\n" + "İPTAL");
        btnIade.setGraphic(new ImageView("images/RETURN.png"));
        btnIade.setText("SATIŞ" + "\n" + "İADE");
        lblBarkod.setGraphic(new ImageView("images/BARKOD.png"));
        lblTl.setGraphic(new ImageView("images/TL.png"));
        btnBeklet.setGraphic(new ImageView("images/WAIT.png"));
        btnBeklet.setText("SATIŞ" + "\n" + "BEKLET");
     
        lblAlinanPara.setGraphic(new ImageView("images/MONEY.png"));
        lblParaUstu.setGraphic(new ImageView("images/COINS.png"));
        btnKart.setGraphic(new ImageView("images/CARD.png"));
        btnNakit.setGraphic(new ImageView("images/BILLS.png"));
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                txtBarkod.requestFocus();
            }
        });

    }

}

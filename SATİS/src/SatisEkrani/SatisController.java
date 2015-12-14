package SatisEkrani;

import Entities.UrunProperty;
import Entities.Personel;
import Entities.Sepet;
import Entities.Urunler;
import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.rmi.server.UID;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
    private Button btnUrunSil;
    @FXML
    private TableView table;
    @FXML
    private TableColumn<UrunProperty, String> uAdi;
    @FXML
    private TableColumn<UrunProperty, String> uKisaAciklama;
    @FXML
    private TableColumn<UrunProperty, BigDecimal> uFiyat;
    @FXML
    private TableColumn<UrunProperty, String> sezon;
    @FXML
    private TableColumn<UrunProperty, String> beden;
    @FXML
    private TableColumn<UrunProperty, String> renk;
    @FXML
    private TableColumn<UrunProperty, Integer> adet;
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
    private Label lblKasa;
    @FXML
    private Personel per;
    @FXML
    private Button btnOk;
    private Object stage;

    ObservableList<UrunProperty> kliste = FXCollections.observableArrayList();
    ArrayList<Double> para = new ArrayList<>();
    ArrayList<String> barkod = new ArrayList<>();
    DB db = new DB();
    SessionFactory sf;
    Session sesi;
    Transaction tr;
    int i = 1;
    UrunProperty ur;

    public Personel getPer() {
        return per;
    }

    public void setPer(Personel per) {
        this.per = per;
        lblKasa.setText(per.getPAdi().toUpperCase() + " " + per.getPSoyadi().toUpperCase());
    }

    @FXML
    private void BarkodBul() {

        String number = txtBarkod.getText();

        if (!barkodKntrl(number).isEmpty()) {

            SoundClipTest();
            Property(number);
            TutarHesapla();
            ekle();
            txtBarkod.setText("");
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
    ArrayList<Object> ar = new ArrayList<>();

    private void Property(String number) {
        try {

            ResultSet pro = db.preProGetir("satisBilgiEkrani", number);
            while (pro.next()) {
                ur = new UrunProperty();
                ur.setUAdi(pro.getString("uAdi"));
                lblUrun.setText("  " + pro.getString("uAdi"));
                ur.setUKisaAciklama(pro.getString("uKisaAciklama"));
                ur.setUFiyat(pro.getBigDecimal("uFiyat"));
                lblTutar.setText(ur.getUFiyat().toString().substring(0, (ur.getUFiyat().toString().indexOf(".") + 3)) + " ");
                ur.setSezon(pro.getString("sBaslik"));
                ur.setBeden(pro.getString("bBaslik"));
                ur.setRenk(pro.getString("rAdi"));
                lblAdet.setText("1");
                ur.setAdet(i);
                i++;
                ur.setUrunID(pro.getInt("urunID"));

                ur.setSilButon(true);
                kliste.add(ur);
                fillTable();

            }

        } catch (SQLException ex) {
            System.out.println("Tablo Doldur hatası : " + ex);
        }
    }

    @FXML
    public void ekle() {

        sf = SatisHibernateUtil.getSessionFactory();
        sesi = sf.getCurrentSession();
        tr = sesi.beginTransaction();
        Sepet sp = fillSepet(sesi);
        sesi.save(sp);
        ar.add(sp.getSepetID());

        tr.commit();

    }

    private Sepet fillSepet(Session sesi) {
        Sepet sp = new Sepet();
        sp.setRefKodu(lblRef.getText());
        sp.setPersonelID(per.getPersonelID());
        Query wuery = sesi.getNamedQuery("Urunler.findByBarkodNo").setParameter("barkodNo", txtBarkod.getText());
        Urunler ls = (Urunler) wuery.uniqueResult();

        sp.setUrunID(ls.getUrunID());

        sp.setUrunFiyat(ls.getUFiyat());
        sp.setAdet((short) 1);
        sp.setDurum(false);
        sp.setTarih(new Date());
        return sp;
    }

    @FXML
    public void OdemeEkraniStart(ActionEvent event) throws Exception {
        String name = buttonName(event);
        int refID = per.getPersonelID();

        Parent root = null;
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OdemeEkrani.fxml"));
        root = fxmlLoader.load();
        OdemeController cont = fxmlLoader.getController();
        cont.getRefCode(lblRef.getText(), lblKasa.getText(), name, lblFiyat.getText(), refID);
        cont.getTotal(BigDecimal.valueOf(tot));
        stage.setScene(new Scene(root));
        stage.show();

//        if (!kliste.isEmpty()) {
//           
//           
//        } else {
//            JOptionPane.showMessageDialog(null, "Ürün Girişi Yapılmamıştır", "HATA ", JOptionPane.WARNING_MESSAGE);
//        }
    }
    Double tot;

    private void TutarHesapla() throws NumberFormatException {
        Double price = 0.00;

        for (UrunProperty item : kliste) {
            String x = String.valueOf(item.getUFiyat());
            Double urunFiyat = Double.valueOf(x);
            price += urunFiyat;

        }
        tot = price;
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

        return i;
    }
    Object val;
    @FXML
    TableColumn<UrunProperty, Boolean> sil;

    private void fillTable() {
        table.setItems(kliste);
        for (UrunProperty item : kliste) {

            uAdi.setCellValueFactory(new PropertyValueFactory<>("uAdi"));
            uKisaAciklama.setCellValueFactory(new PropertyValueFactory<>("uKisaAciklama"));
            uFiyat.setCellValueFactory(new PropertyValueFactory<>("uFiyat"));
            sezon.setCellValueFactory(new PropertyValueFactory<>("sezon"));
            beden.setCellValueFactory(new PropertyValueFactory<>("beden"));
            renk.setCellValueFactory(new PropertyValueFactory<>("renk"));
            adet.setCellValueFactory(new PropertyValueFactory<>("adet"));

            sil.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<UrunProperty, Boolean>, ObservableValue<Boolean>>() {

                        @Override
                        public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<UrunProperty, Boolean> p) {
                            return new SimpleBooleanProperty(p.getValue() != null);
                        }
                    });

            sil.setCellFactory(
                    new Callback<TableColumn<UrunProperty, Boolean>, TableCell<UrunProperty, Boolean>>() {

                        @Override
                        public TableCell<UrunProperty, Boolean> call(TableColumn<UrunProperty, Boolean> p) {
                            return new ButtonCell();
                        }

                    });
        }
    }

    private class ButtonCell extends TableCell<UrunProperty, Boolean> {

        final Button cellButton = new Button("Sil");

        ButtonCell() {

            //Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                    UrunProperty urunler = (UrunProperty) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                    //remove selected item from the table list
                    TableRow row = getTableRow();
                    int rowNumber = row.getIndex();
                    SessionFactory sff = SatisHibernateUtil.getSessionFactory();
                    Session session = sff.getCurrentSession();
                    Transaction trr = session.beginTransaction();

                    String hql = ("delete from Sepet where sepetID=" + ar.get(rowNumber));
                    session.createQuery(hql).executeUpdate();
                    trr.commit();
                    ar.remove(rowNumber);
//                       session.clear();
//                       sff.close();
                    kliste.remove(urunler);
//              

                    TutarHesapla();
                }
            });

        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);

            } else {
                setGraphic(null);
            }
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

    public String buttonName(ActionEvent event) {
        Button button = (Button) event.getSource();
        String btnName = button.getId();
        btnName = btnName.replace("btn", "");
        return btnName;
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

    @FXML
    private void temizle(){
        lblAdet.setText("");
        lblAlinanPara.setText("");
        lblBarkod.setText("");
        lblFiyat.setText("");
        lblUrun.setText("");
        lblTutar.setText("");
//        UrunProperty urun = new UrunProperty();
//        kliste.remove(urun);
//        
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
        btnBeklet.setText("SATIŞ" + "\n" + "TEMİZLE");
        btnOk.setGraphic(new ImageView("images/OK.png"));
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

package satisiptal;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

public class FXMLmainController implements Initializable {

    @FXML
    private TextField txtFaturaNo;

    @FXML
    private TableView<PropertySatisIptal> faturaUrunleri;

    @FXML
    private TableColumn<PropertySatisIptal, String> urunadi;
    @FXML
    private TableColumn<PropertySatisIptal, String> aciklama;
    @FXML
    private TableColumn<PropertySatisIptal, String> sezon;
    @FXML
    private TableColumn<PropertySatisIptal, String> birimFiyati;
    @FXML
    private TableColumn<PropertySatisIptal, String> adet;
    @FXML
    private TableColumn<PropertySatisIptal, String> uTutar;
    @FXML
    private TableColumn<PropertySatisIptal, String> beden;
    @FXML
    private TableColumn<PropertySatisIptal, String> renk;
    @FXML
    private TableColumn<PropertySatisIptal, String> urunSira;
    @FXML
    private Label pKullaniciAdi;
    @FXML
    private Label pAdi;
    @FXML
    private Label pSoyadi;
    @FXML
    private Label fTutar;
    @FXML
    private Label oTipi;
    @FXML
    private Label fTarihi;
    @FXML
    private TableColumn<PropertySatisIptal, String> urunID;
    @FXML
    private Button uruniade;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        uruniade.setDisable(true);

    }
    MsSqlCon faturaIslem = new MsSqlCon();
    ObservableList<PropertySatisIptal> oFaturaDetay = FXCollections.observableArrayList();

    @FXML
    public void faturaUrunleriniGetir(ActionEvent event) {
        oFaturaDetay.removeAll(oFaturaDetay);
        ResultSet rs = faturaIslem.faturaGetir(txtFaturaNo.getText());
        boolean durum=false;
        try {
          
            int i = 0;
            while (rs.next()) {
                
            durum=true;
                uruniade.setDisable(false);

                i++;
                PropertySatisIptal sI = new PropertySatisIptal();
                sI.setUrunadi(rs.getString("uAdi"));
                sI.setSezon(rs.getString("sBaslik"));
                sI.setAciklama(rs.getString("uKisaAciklama"));
                sI.setBirimFiyati(rs.getString("urunFiyat"));
                sI.setAdet(rs.getString("adet"));
                sI.setpAdi(rs.getString("pAdi"));
                sI.setfTarihi(rs.getString("sTarih"));
                sI.setpSoyadi(rs.getString("pSoyadi"));
                sI.setpKullaniciAdi(rs.getString("pKulAdi"));
                sI.setTxtFaturaNo(rs.getString("refKodu"));
                sI.setFTutar(rs.getString("fiyat"));
                sI.setoTipi(rs.getString("odemeTipi"));
                sI.setBeden(rs.getString("bBaslik"));
                sI.setRenk(rs.getString("rAdi"));
                sI.setUrunid(rs.getString("barkodNo"));
                sI.setUTutar(rs.getString("urunFiyat") + "/" + rs.getString("fiyat"));
                sI.setUrunSirasi("" + i);
                oFaturaDetay.add(sI);
            }
            if(durum==false)
            {
                JOptionPane.showMessageDialog(null, "Böyle Bir Satış Yoktur!");
            }
            faturaUrunleri.setItems(oFaturaDetay);

            for (PropertySatisIptal item : oFaturaDetay) {
                urunSira.setCellValueFactory(new PropertyValueFactory<>("urunSirasi"));
                urunadi.setCellValueFactory(new PropertyValueFactory<>("urunadi"));
                aciklama.setCellValueFactory(new PropertyValueFactory<>("aciklama"));
                birimFiyati.setCellValueFactory(new PropertyValueFactory<>("birimFiyati"));
                sezon.setCellValueFactory(new PropertyValueFactory<>("sezon"));
                beden.setCellValueFactory(new PropertyValueFactory<>("beden"));
                renk.setCellValueFactory(new PropertyValueFactory<>("renk"));
                adet.setCellValueFactory(new PropertyValueFactory<>("adet"));
                uTutar.setCellValueFactory(new PropertyValueFactory<>("uTutar"));
                urunID.setCellValueFactory(new PropertyValueFactory<>("urunid"));
                fTutar.setText(item.getFTutar());
                fTarihi.setText(item.getfTarihi());
                oTipi.setText(item.getoTipi());
                pAdi.setText(item.getpAdi());
                pSoyadi.setText(item.getpSoyadi());
                pKullaniciAdi.setText(item.getpKullaniciAdi());

            }

        } catch (Exception e) {
            System.err.println("Hata:  " + e);
        }

    }

    @FXML
    private void urunIadeEt(ActionEvent event) {
        if (faturaUrunleri.getSelectionModel().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ÜRÜN SEÇİLİ DEĞİL");
        } else {
            int dialogButton = JOptionPane.showConfirmDialog(null, "ÜRÜNÜ İADE ALMAK İSTEDİĞİNİZE EMİN MİSİNİZ?", "UYARI!", JOptionPane.YES_NO_OPTION);

            if (dialogButton == JOptionPane.YES_OPTION) {
                String faturaNo = faturaUrunleri.getSelectionModel().getSelectedItem().getTxtFaturaNo() + "";
                String urunIDD = faturaUrunleri.getSelectionModel().getSelectedItem().getUrunid() + "";
                String fiyat = faturaUrunleri.getSelectionModel().getSelectedItem().getBirimFiyati() + "";
                faturaIslem.urunIadeYap(urunIDD, faturaNo, fiyat);

                faturaUrunleriniGetir(event);
            }

        }

    }

}

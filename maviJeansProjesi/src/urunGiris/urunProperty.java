package urunGiris;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class urunProperty {

    private int urunID;
    private int superID;
    private int sezonID;
    private int bedenID;
    private int renkID;
    private String urunAd;
    private String urunKisaAciklama;
    private String urunFiyat;
    private String urunResim;
    private String urunTarih;

    public urunProperty(int urunID, int superID, int sezonID, int bedenID, int renkID, String urunAd, String urunKisaAciklama, String urunFiyat, String urunResim, String urunTarih) {
        this.urunID = urunID;
        this.superID = superID;
        this.sezonID = sezonID;
        this.bedenID = bedenID;
        this.renkID = renkID;
        this.urunAd = urunAd;
        this.urunKisaAciklama = urunKisaAciklama;
        this.urunFiyat = urunFiyat;
        this.urunResim = urunResim;
        this.urunTarih = urunTarih;
    }

    public int getUrunID() {
        return urunID;
    }

    public void setUrunID(int urunID) {
        this.urunID = urunID;
    }

    public int getSuperID() {
        return superID;
    }

    public void setSuperID(int superID) {
        this.superID = superID;
    }

    public int getSezonID() {
        return sezonID;
    }

    public void setSezonID(int sezonID) {
        this.sezonID = sezonID;
    }

    public int getBedenID() {
        return bedenID;
    }

    public void setBedenID(int bedenID) {
        this.bedenID = bedenID;
    }

    public int getRenkID() {
        return renkID;
    }

    public void setRenkID(int renkID) {
        this.renkID = renkID;
    }

    public String getUrunAd() {
        return urunAd;
    }

    public void setUrunAd(String urunAd) {
        this.urunAd = urunAd;
    }

    public String getUrunKisaAciklama() {
        return urunKisaAciklama;
    }

    public void setUrunKisaAciklama(String urunKisaAciklama) {
        this.urunKisaAciklama = urunKisaAciklama;
    }

    public String getUrunFiyat() {
        return urunFiyat;
    }

    public void setUrunFiyat(String urunFiyat) {
        this.urunFiyat = urunFiyat;
    }

    public String getUrunResim() {
        return urunResim;
    }

    public void setUrunResim(String urunResim) {
        this.urunResim = urunResim;
    }

    public String getUrunTarih() {
        return urunTarih;
    }

    public void setUrunTarih(String urunTarih) {
        this.urunTarih = urunTarih;
    }

}

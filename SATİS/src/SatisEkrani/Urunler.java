
package SatisEkrani;

import java.math.BigDecimal;
import java.util.Date;


public class Urunler {

  
    public Urunler() {
        
    }
 
    
    private int renkID;
    private int urunID;
    private int superID;
    private int sezonID;
    private int bedenID;
    private String uTarih;
    private String uAdi;
    private String uResim;
    private String barkodNo;
    private BigDecimal uFiyat;
    private String uKisaAciklama;
    private boolean durum;

    public boolean getDurum() {
        return durum;
    }

    public void setDurum(boolean durum) {
        this.durum = durum;
    }
   
    public String getRenk() {
        return renk;
    }

    public void setRenk(String renk) {
        this.renk = renk;
    }

    public String getBeden() {
        return beden;
    }

    public void setBeden(String beden) {
        this.beden = beden;
    }

    public String getSezon() {
        return sezon;
    }

    public void setSezon(String sezon) {
        this.sezon = sezon;
    }

    private String renk;
    private String beden;
    private String sezon;

    public int getKalan() {
        return kalan;
    }

    public void setKalan(int kalan) {
        this.kalan = kalan;
    }

    public int getAdet() {
        return adet;
    }

    public void setAdet(int adet) {
        this.adet = adet;
    }
    private int kalan;
    private int adet;

    public Urunler(int renkID, int urunID, int superID, int sezonID, int bedenID, String uTarih, String uAdi, String uResim, String barkodNo, BigDecimal uFiyat, String uKisaAciklama, String renk) {
        this.renkID = renkID;
        this.urunID = urunID;
        this.superID = superID;
        this.sezonID = sezonID;
        this.bedenID = bedenID;
        this.uTarih = uTarih;
        this.uAdi = uAdi;
        this.uResim = uResim;
        this.barkodNo = barkodNo;
        this.uFiyat = uFiyat;
        this.uKisaAciklama = uKisaAciklama;
        this.renk = renk;
    }
    
    public int getRenkID() {
        return renkID;
    }

    public void setRenkID(int renkID) {
        this.renkID = renkID;
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

    public String getUTarih() {
        return uTarih;
    }

    public void setUTarih(String uTarih) {
        this.uTarih = uTarih;
    }

    public String getUAdi() {
        return uAdi;
    }

    public void setUAdi(String uAdi) {
        this.uAdi = uAdi;
    }

    public String getUResim() {
        return uResim;
    }

    public void setUResim(String uResim) {
        this.uResim = uResim;
    }

    public String getBarkodNo() {
        return barkodNo;
    }

    public void setBarkodNo(String barkodNo) {
        this.barkodNo = barkodNo;
    }

    public BigDecimal getUFiyat() {
        return uFiyat;
    }

    public void setUFiyat(BigDecimal uFiyat) {
        this.uFiyat = uFiyat;
    }

    public String getUKisaAciklama() {
        return uKisaAciklama;
    }

    public void setUKisaAciklama(String uKisaAciklama) {
        this.uKisaAciklama = uKisaAciklama;
    }
    
    
}

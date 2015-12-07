package stokGiris;

public class stokProperty {

    private int stokID;
    private int superID;
    private int urunID;
    private int gelenAdet;
    private int kalanAdet;
    private String Tarih;

    public stokProperty(int stokID, int superID, int urunID, int gelenAdet, int kalanAdet, String Tarih) {
        this.stokID = stokID;
        this.superID = superID;
        this.urunID = urunID;
        this.gelenAdet = gelenAdet;
        this.kalanAdet = kalanAdet;
        this.Tarih = Tarih;
    }

    public int getStokID() {
        return stokID;
    }

    public void setStokID(int stokID) {
        this.stokID = stokID;
    }

    public int getSuperID() {
        return superID;
    }

    public void setSuperID(int superID) {
        this.superID = superID;
    }

    public int getUrunID() {
        return urunID;
    }

    public void setUrunID(int urunID) {
        this.urunID = urunID;
    }

    public int getGelenAdet() {
        return gelenAdet;
    }

    public void setGelenAdet(int gelenAdet) {
        this.gelenAdet = gelenAdet;
    }

    public int getKalanAdet() {
        return kalanAdet;
    }

    public void setKalanAdet(int kalanAdet) {
        this.kalanAdet = kalanAdet;
    }

    public String getTarih() {
        return Tarih;
    }

    public void setTarih(String Tarih) {
        this.Tarih = Tarih;
    }

}

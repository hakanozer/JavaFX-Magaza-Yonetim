/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SatisEkrani;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JavaSabah
 */
@Entity
@Table(catalog = "mavi", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personel.findAll", query = "SELECT p FROM Personel p"),
    @NamedQuery(name = "Personel.findByPersonelID", query = "SELECT p FROM Personel p WHERE p.personelID = :personelID"),
    @NamedQuery(name = "Personel.findByMudurID", query = "SELECT p FROM Personel p WHERE p.mudurID = :mudurID"),
    @NamedQuery(name = "Personel.findByKonum", query = "SELECT p FROM Personel p WHERE p.konum = :konum"),
    @NamedQuery(name = "Personel.findByPKulAdi", query = "SELECT p FROM Personel p WHERE p.pKulAdi = :pKulAdi"),
    @NamedQuery(name = "Personel.findByPSifre", query = "SELECT p FROM Personel p WHERE p.pSifre = :pSifre"),
    @NamedQuery(name = "Personel.findByPAdi", query = "SELECT p FROM Personel p WHERE p.pAdi = :pAdi"),
    @NamedQuery(name = "Personel.findByPSoyadi", query = "SELECT p FROM Personel p WHERE p.pSoyadi = :pSoyadi"),
    @NamedQuery(name = "Personel.findByPTelefon", query = "SELECT p FROM Personel p WHERE p.pTelefon = :pTelefon"),
    @NamedQuery(name = "Personel.findByPCep", query = "SELECT p FROM Personel p WHERE p.pCep = :pCep"),
    @NamedQuery(name = "Personel.findByPMail", query = "SELECT p FROM Personel p WHERE p.pMail = :pMail"),
    @NamedQuery(name = "Personel.findByPAdres", query = "SELECT p FROM Personel p WHERE p.pAdres = :pAdres"),
    @NamedQuery(name = "Personel.findByDogumTarihi", query = "SELECT p FROM Personel p WHERE p.dogumTarihi = :dogumTarihi"),
    @NamedQuery(name = "Personel.findByBaslamaTarihi", query = "SELECT p FROM Personel p WHERE p.baslamaTarihi = :baslamaTarihi"),
    @NamedQuery(name = "Personel.findByTarih", query = "SELECT p FROM Personel p WHERE p.tarih = :tarih")})
public class Personel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer personelID;
    private Integer mudurID;
    private Short konum;
    @Column(length = 255)
    private String pKulAdi;
    @Column(length = 32)
    private String pSifre;
    @Column(length = 255)
    private String pAdi;
    @Column(length = 255)
    private String pSoyadi;
    @Column(length = 50)
    private String pTelefon;
    @Column(length = 50)
    private String pCep;
    @Column(length = 255)
    private String pMail;
    @Column(length = 500)
    private String pAdres;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dogumTarihi;
    @Temporal(TemporalType.TIMESTAMP)
    private Date baslamaTarihi;
    @Temporal(TemporalType.TIMESTAMP)
    private Date tarih;

    public Personel() {
    }

    public Personel(Integer personelID) {
        this.personelID = personelID;
    }

    public Integer getPersonelID() {
        return personelID;
    }

    public void setPersonelID(Integer personelID) {
        this.personelID = personelID;
    }

    public Integer getMudurID() {
        return mudurID;
    }

    public void setMudurID(Integer mudurID) {
        this.mudurID = mudurID;
    }

    public Short getKonum() {
        return konum;
    }

    public void setKonum(Short konum) {
        this.konum = konum;
    }

    public String getPKulAdi() {
        return pKulAdi;
    }

    public void setPKulAdi(String pKulAdi) {
        this.pKulAdi = pKulAdi;
    }

    public String getPSifre() {
        return pSifre;
    }

    public void setPSifre(String pSifre) {
        this.pSifre = pSifre;
    }

    public String getPAdi() {
        return pAdi;
    }

    public void setPAdi(String pAdi) {
        this.pAdi = pAdi;
    }

    public String getPSoyadi() {
        return pSoyadi;
    }

    public void setPSoyadi(String pSoyadi) {
        this.pSoyadi = pSoyadi;
    }

    public String getPTelefon() {
        return pTelefon;
    }

    public void setPTelefon(String pTelefon) {
        this.pTelefon = pTelefon;
    }

    public String getPCep() {
        return pCep;
    }

    public void setPCep(String pCep) {
        this.pCep = pCep;
    }

    public String getPMail() {
        return pMail;
    }

    public void setPMail(String pMail) {
        this.pMail = pMail;
    }

    public String getPAdres() {
        return pAdres;
    }

    public void setPAdres(String pAdres) {
        this.pAdres = pAdres;
    }

    public Date getDogumTarihi() {
        return dogumTarihi;
    }

    public void setDogumTarihi(Date dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }

    public Date getBaslamaTarihi() {
        return baslamaTarihi;
    }

    public void setBaslamaTarihi(Date baslamaTarihi) {
        this.baslamaTarihi = baslamaTarihi;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personelID != null ? personelID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personel)) {
            return false;
        }
        Personel other = (Personel) object;
        if ((this.personelID == null && other.personelID != null) || (this.personelID != null && !this.personelID.equals(other.personelID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SatisEkrani.Personel[ personelID=" + personelID + " ]";
    }
    
}

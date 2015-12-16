/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mavimudurler;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ahmet KAZĞIN
 */
@Entity
@Table(name = "mudurler")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mudurler.findAll", query = "SELECT m FROM Mudurler m"),
    @NamedQuery(name = "Mudurler.findByMID", query = "SELECT m FROM Mudurler m WHERE m.mID = :mID"),
    @NamedQuery(name = "Mudurler.findBySubeID", query = "SELECT m FROM Mudurler m WHERE m.subeID = :subeID"),
    @NamedQuery(name = "Mudurler.findBySubeAdi", query = "SELECT m FROM Mudurler m WHERE m.subeAdi = :subeAdi"),
    @NamedQuery(name = "Mudurler.findByMAdi", query = "SELECT m FROM Mudurler m WHERE m.mAdi = :mAdi"),
    @NamedQuery(name = "Mudurler.findByMSoyadi", query = "SELECT m FROM Mudurler m WHERE m.mSoyadi = :mSoyadi"),
    @NamedQuery(name = "Mudurler.findByMTelefon", query = "SELECT m FROM Mudurler m WHERE m.mTelefon = :mTelefon"),
    @NamedQuery(name = "Mudurler.findByMCep", query = "SELECT m FROM Mudurler m WHERE m.mCep = :mCep"),
    @NamedQuery(name = "Mudurler.findByMMail", query = "SELECT m FROM Mudurler m WHERE m.mMail = :mMail"),
    @NamedQuery(name = "Mudurler.findByMAdres", query = "SELECT m FROM Mudurler m WHERE m.mAdres = :mAdres"),
    @NamedQuery(name = "Mudurler.findByMKuladi", query = "SELECT m FROM Mudurler m WHERE m.mKuladi = :mKuladi"),
    @NamedQuery(name = "Mudurler.findByMSifre", query = "SELECT m FROM Mudurler m WHERE m.mSifre = :mSifre"),
    @NamedQuery(name = "Mudurler.findByMTarih", query = "SELECT m FROM Mudurler m WHERE m.mTarih = :mTarih")})
public class Mudurler implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "mID")
    private Integer mID;
    @Column(name = "subeID")
    private Integer subeID;
    @Column(name = "subeAdi")
    private String subeAdi;
    @Column(name = "mAdi")
    private String mAdi;
    @Column(name = "mSoyadi")
    private String mSoyadi;
    @Column(name = "mTelefon")
    private String mTelefon;
    @Column(name = "mCep")
    private String mCep;
    @Column(name = "mMail")
    private String mMail;
    @Column(name = "mAdres")
    private String mAdres;
    @Column(name = "mKuladi")
    private String mKuladi;
    @Column(name = "mSifre")
    private String mSifre;
    @Column(name = "mTarih")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mTarih;

    public Mudurler() {
    }

    public Mudurler(Integer mID) {
        this.mID = mID;
    }

    public Integer getMID() {
        return mID;
    }

    public void setMID(Integer mID) {
        this.mID = mID;
    }

    public Integer getSubeID() {
        return subeID;
    }

    public void setSubeID(Integer subeID) {
        this.subeID = subeID;
    }

    public String getSubeAdi() {
        return subeAdi;
    }

    public void setSubeAdi(String subeAdi) {
        this.subeAdi = subeAdi;
    }

    public String getMAdi() {
        return mAdi;
    }

    public void setMAdi(String mAdi) {
        this.mAdi = mAdi;
    }

    public String getMSoyadi() {
        return mSoyadi;
    }

    public void setMSoyadi(String mSoyadi) {
        this.mSoyadi = mSoyadi;
    }

    public String getMTelefon() {
        return mTelefon;
    }

    public void setMTelefon(String mTelefon) {
        this.mTelefon = mTelefon;
    }

    public String getMCep() {
        return mCep;
    }

    public void setMCep(String mCep) {
        this.mCep = mCep;
    }

    public String getMMail() {
        return mMail;
    }

    public void setMMail(String mMail) {
        this.mMail = mMail;
    }

    public String getMAdres() {
        return mAdres;
    }

    public void setMAdres(String mAdres) {
        this.mAdres = mAdres;
    }

    public String getMKuladi() {
        return mKuladi;
    }

    public void setMKuladi(String mKuladi) {
        this.mKuladi = mKuladi;
    }

    public String getMSifre() {
        return mSifre;
    }

    public void setMSifre(String mSifre) {
        this.mSifre = mSifre;
    }

    public Date getMTarih() {
        return mTarih;
    }

    public void setMTarih(Date mTarih) {
        this.mTarih = mTarih;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mID != null ? mID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mudurler)) {
            return false;
        }
        Mudurler other = (Mudurler) object;
        if ((this.mID == null && other.mID != null) || (this.mID != null && !this.mID.equals(other.mID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mavimudurler.Mudurler[ mID=" + mID + " ]";
    }
    
}

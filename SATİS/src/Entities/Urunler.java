/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "urunler", catalog = "mavi", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Urunler.findAll", query = "SELECT u FROM Urunler u"),
    @NamedQuery(name = "Urunler.findByUrunID", query = "SELECT u FROM Urunler u WHERE u.urunID = :urunID"),
    @NamedQuery(name = "Urunler.findBySuperID", query = "SELECT u FROM Urunler u WHERE u.superID = :superID"),
    @NamedQuery(name = "Urunler.findBySezonID", query = "SELECT u FROM Urunler u WHERE u.sezonID = :sezonID"),
    @NamedQuery(name = "Urunler.findByBedenID", query = "SELECT u FROM Urunler u WHERE u.bedenID = :bedenID"),
    @NamedQuery(name = "Urunler.findByRenkID", query = "SELECT u FROM Urunler u WHERE u.renkID = :renkID"),
    @NamedQuery(name = "Urunler.findByBarkodNo", query = "SELECT u FROM Urunler u WHERE u.barkodNo = :barkodNo"),
    @NamedQuery(name = "Urunler.findByUAdi", query = "SELECT u FROM Urunler u WHERE u.uAdi = :uAdi"),
    @NamedQuery(name = "Urunler.findByUKisaAciklama", query = "SELECT u FROM Urunler u WHERE u.uKisaAciklama = :uKisaAciklama"),
    @NamedQuery(name = "Urunler.findByUFiyat", query = "SELECT u FROM Urunler u WHERE u.uFiyat = :uFiyat"),
    @NamedQuery(name = "Urunler.findByUResim", query = "SELECT u FROM Urunler u WHERE u.uResim = :uResim"),
    @NamedQuery(name = "Urunler.findByUTarih", query = "SELECT u FROM Urunler u WHERE u.uTarih = :uTarih")})
public class Urunler implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "urunID", nullable = false)
    private Integer urunID;
    @Column(name = "superID")
    private Integer superID;
    @Column(name = "sezonID")
    private Integer sezonID;
    @Column(name = "bedenID")
    private Integer bedenID;
    @Column(name = "renkID")
    private Integer renkID;
    @Column(name = "barkodNo", length = 100)
    private String barkodNo;
    @Column(name = "uAdi", length = 255)
    private String uAdi;
    @Column(name = "uKisaAciklama", length = 500)
    private String uKisaAciklama;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "uFiyat", precision = 19, scale = 4)
    private BigDecimal uFiyat;
    @Column(name = "uResim", length = 500)
    private String uResim;
    @Column(name = "uTarih")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uTarih;

    public Urunler() {
    }

    public Urunler(Integer urunID) {
        this.urunID = urunID;
    }

    public Integer getUrunID() {
        return urunID;
    }

    public void setUrunID(Integer urunID) {
        this.urunID = urunID;
    }

    public Integer getSuperID() {
        return superID;
    }

    public void setSuperID(Integer superID) {
        this.superID = superID;
    }

    public Integer getSezonID() {
        return sezonID;
    }

    public void setSezonID(Integer sezonID) {
        this.sezonID = sezonID;
    }

    public Integer getBedenID() {
        return bedenID;
    }

    public void setBedenID(Integer bedenID) {
        this.bedenID = bedenID;
    }

    public Integer getRenkID() {
        return renkID;
    }

    public void setRenkID(Integer renkID) {
        this.renkID = renkID;
    }

    public String getBarkodNo() {
        return barkodNo;
    }

    public void setBarkodNo(String barkodNo) {
        this.barkodNo = barkodNo;
    }

    public String getUAdi() {
        return uAdi;
    }

    public void setUAdi(String uAdi) {
        this.uAdi = uAdi;
    }

    public String getUKisaAciklama() {
        return uKisaAciklama;
    }

    public void setUKisaAciklama(String uKisaAciklama) {
        this.uKisaAciklama = uKisaAciklama;
    }

    public BigDecimal getUFiyat() {
        return uFiyat;
    }

    public void setUFiyat(BigDecimal uFiyat) {
        this.uFiyat = uFiyat;
    }

    public String getUResim() {
        return uResim;
    }

    public void setUResim(String uResim) {
        this.uResim = uResim;
    }

    public Date getUTarih() {
        return uTarih;
    }

    public void setUTarih(Date uTarih) {
        this.uTarih = uTarih;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (urunID != null ? urunID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Urunler)) {
            return false;
        }
        Urunler other = (Urunler) object;
        if ((this.urunID == null && other.urunID != null) || (this.urunID != null && !this.urunID.equals(other.urunID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Urunler[ urunID=" + urunID + " ]";
    }
    
}

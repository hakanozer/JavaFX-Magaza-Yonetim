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
 * @author JavaSabah
 */
@Entity
@Table(name = "sepet", catalog = "mavi", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sepet.findAll", query = "SELECT s FROM Sepet s"),
    @NamedQuery(name = "Sepet.findBySepetID", query = "SELECT s FROM Sepet s WHERE s.sepetID = :sepetID"),
    @NamedQuery(name = "Sepet.findByRefKodu", query = "SELECT s FROM Sepet s WHERE s.refKodu = :refKodu"),
    @NamedQuery(name = "Sepet.findByPersonelID", query = "SELECT s FROM Sepet s WHERE s.personelID = :personelID"),
    @NamedQuery(name = "Sepet.findByUrunID", query = "SELECT s FROM Sepet s WHERE s.urunID = :urunID"),
    @NamedQuery(name = "Sepet.findByUrunFiyat", query = "SELECT s FROM Sepet s WHERE s.urunFiyat = :urunFiyat"),
    @NamedQuery(name = "Sepet.findByAdet", query = "SELECT s FROM Sepet s WHERE s.adet = :adet"),
    @NamedQuery(name = "Sepet.findByDurum", query = "SELECT s FROM Sepet s WHERE s.durum = :durum"),
    @NamedQuery(name = "Sepet.findByTarih", query = "SELECT s FROM Sepet s WHERE s.tarih = :tarih")})
public class Sepet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sepetID", nullable = false)
    private Integer sepetID;
    @Column(name = "refKodu", length = 50)
    private String refKodu;
    @Column(name = "personelID")
    private Integer personelID;
    @Column(name = "urunID")
    private Integer urunID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "urunFiyat", precision = 19, scale = 4)
    private BigDecimal urunFiyat;
    @Column(name = "adet")
    private Short adet;
    @Column(name = "durum")
    private Boolean durum;
    @Column(name = "tarih")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tarih;

    public Sepet() {
    }

    public Sepet(Integer sepetID) {
        this.sepetID = sepetID;
    }

    public Integer getSepetID() {
        return sepetID;
    }

    public void setSepetID(Integer sepetID) {
        this.sepetID = sepetID;
    }

    public String getRefKodu() {
        return refKodu;
    }

    public void setRefKodu(String refKodu) {
        this.refKodu = refKodu;
    }

    public Integer getPersonelID() {
        return personelID;
    }

    public void setPersonelID(Integer personelID) {
        this.personelID = personelID;
    }

    public Integer getUrunID() {
        return urunID;
    }

    public void setUrunID(Integer urunID) {
        this.urunID = urunID;
    }

    public BigDecimal getUrunFiyat() {
        return urunFiyat;
    }

    public void setUrunFiyat(BigDecimal urunFiyat) {
        this.urunFiyat = urunFiyat;
    }

    public Short getAdet() {
        return adet;
    }

    public void setAdet(Short adet) {
        this.adet = adet;
    }

    public Boolean getDurum() {
        return durum;
    }

    public void setDurum(Boolean durum) {
        this.durum = durum;
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
        hash += (sepetID != null ? sepetID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sepet)) {
            return false;
        }
        Sepet other = (Sepet) object;
        if ((this.sepetID == null && other.sepetID != null) || (this.sepetID != null && !this.sepetID.equals(other.sepetID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SatisEkrani.Sepet[ sepetID=" + sepetID + " ]";
    }
    
}

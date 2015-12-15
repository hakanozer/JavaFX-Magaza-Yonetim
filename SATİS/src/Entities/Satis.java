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
 * @author Gamze
 */
@Entity
@Table(catalog = "mavi", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Satis.findAll", query = "SELECT s FROM Satis s"),
    @NamedQuery(name = "Satis.findBySatisID", query = "SELECT s FROM Satis s WHERE s.satisID = :satisID"),
    @NamedQuery(name = "Satis.findBySepetRefKodu", query = "SELECT s FROM Satis s WHERE s.sepetRefKodu = :sepetRefKodu"),
    @NamedQuery(name = "Satis.findByPersonelID", query = "SELECT s FROM Satis s WHERE s.personelID = :personelID"),
    @NamedQuery(name = "Satis.findByFiyat", query = "SELECT s FROM Satis s WHERE s.fiyat = :fiyat"),
    @NamedQuery(name = "Satis.findByOdemeTipi", query = "SELECT s FROM Satis s WHERE s.odemeTipi = :odemeTipi"),
    @NamedQuery(name = "Satis.findBySTarih", query = "SELECT s FROM Satis s WHERE s.sTarih = :sTarih")})
public class Satis implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer satisID;
    @Column(length = 50)
    private String sepetRefKodu;
    private Integer personelID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 19, scale = 4)
    private BigDecimal fiyat;
    private Short odemeTipi;
    @Temporal(TemporalType.TIMESTAMP)
    private Date sTarih;

    public Satis() {
    }

    public Satis(Integer satisID) {
        this.satisID = satisID;
    }

    public Integer getSatisID() {
        return satisID;
    }

    public void setSatisID(Integer satisID) {
        this.satisID = satisID;
    }

    public String getSepetRefKodu() {
        return sepetRefKodu;
    }

    public void setSepetRefKodu(String sepetRefKodu) {
        this.sepetRefKodu = sepetRefKodu;
    }

    public Integer getPersonelID() {
        return personelID;
    }

    public void setPersonelID(Integer personelID) {
        this.personelID = personelID;
    }

    public BigDecimal getFiyat() {
        return fiyat;
    }

    public void setFiyat(BigDecimal fiyat) {
        this.fiyat = fiyat;
    }

    public Short getOdemeTipi() {
        return odemeTipi;
    }

    public void setOdemeTipi(Short odemeTipi) {
        this.odemeTipi = odemeTipi;
    }

    public Date getSTarih() {
        return sTarih;
    }

    public void setSTarih(Date sTarih) {
        this.sTarih = sTarih;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (satisID != null ? satisID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Satis)) {
            return false;
        }
        Satis other = (Satis) object;
        if ((this.satisID == null && other.satisID != null) || (this.satisID != null && !this.satisID.equals(other.satisID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Satis[ satisID=" + satisID + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urunGiris;

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
 * @author javaSabah
 */
@Entity
@Table(name = "renkler")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Renkler.findAll", query = "SELECT r FROM Renkler r"),
    @NamedQuery(name = "Renkler.findByRenkID", query = "SELECT r FROM Renkler r WHERE r.renkID = :renkID"),
    @NamedQuery(name = "Renkler.findBySuperID", query = "SELECT r FROM Renkler r WHERE r.superID = :superID"),
    @NamedQuery(name = "Renkler.findByRAdi", query = "SELECT r FROM Renkler r WHERE r.rAdi = :rAdi"),
    @NamedQuery(name = "Renkler.findByRKodu", query = "SELECT r FROM Renkler r WHERE r.rKodu = :rKodu"),
    @NamedQuery(name = "Renkler.findByRTarih", query = "SELECT r FROM Renkler r WHERE r.rTarih = :rTarih")})
public class Renkler implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "renkID")
    private Integer renkID;
    @Column(name = "superID")
    private Integer superID;
    @Column(name = "rAdi")
    private String rAdi;
    @Column(name = "rKodu")
    private String rKodu;
    @Column(name = "rTarih")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rTarih;

    public Renkler() {
    }

    public Renkler(Integer renkID) {
        this.renkID = renkID;
    }

    public Integer getRenkID() {
        return renkID;
    }

    public void setRenkID(Integer renkID) {
        this.renkID = renkID;
    }

    public Integer getSuperID() {
        return superID;
    }

    public void setSuperID(Integer superID) {
        this.superID = superID;
    }

    public String getRAdi() {
        return rAdi;
    }

    public void setRAdi(String rAdi) {
        this.rAdi = rAdi;
    }

    public String getRKodu() {
        return rKodu;
    }

    public void setRKodu(String rKodu) {
        this.rKodu = rKodu;
    }

    public Date getRTarih() {
        return rTarih;
    }

    public void setRTarih(Date rTarih) {
        this.rTarih = rTarih;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (renkID != null ? renkID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Renkler)) {
            return false;
        }
        Renkler other = (Renkler) object;
        if ((this.renkID == null && other.renkID != null) || (this.renkID != null && !this.renkID.equals(other.renkID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "urunGiris.Renkler[ renkID=" + renkID + " ]";
    }
    
}

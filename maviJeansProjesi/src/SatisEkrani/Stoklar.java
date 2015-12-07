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
 * @author Samsung
 */
@Entity
@Table(name = "stoklar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stoklar.findAll", query = "SELECT s FROM Stoklar s"),
    @NamedQuery(name = "Stoklar.findByStokID", query = "SELECT s FROM Stoklar s WHERE s.stokID = :stokID"),
    @NamedQuery(name = "Stoklar.findBySuperID", query = "SELECT s FROM Stoklar s WHERE s.superID = :superID"),
    @NamedQuery(name = "Stoklar.findByUrunID", query = "SELECT s FROM Stoklar s WHERE s.urunID = :urunID"),
    @NamedQuery(name = "Stoklar.findByGelenAdet", query = "SELECT s FROM Stoklar s WHERE s.gelenAdet = :gelenAdet"),
    @NamedQuery(name = "Stoklar.findByKalanAdet", query = "SELECT s FROM Stoklar s WHERE s.kalanAdet = :kalanAdet"),
    @NamedQuery(name = "Stoklar.findByTarih", query = "SELECT s FROM Stoklar s WHERE s.tarih = :tarih")})
public class Stoklar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "stokID")
    private Integer stokID;
    @Column(name = "superID")
    private Integer superID;
    @Column(name = "urunID")
    private Integer urunID;
    @Column(name = "gelenAdet")
    private Short gelenAdet;
    @Column(name = "kalanAdet")
    private Short kalanAdet;
    @Column(name = "tarih")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tarih;

    public Stoklar() {
    }

    public Stoklar(Integer stokID) {
        this.stokID = stokID;
    }

    public Integer getStokID() {
        return stokID;
    }

    public void setStokID(Integer stokID) {
        this.stokID = stokID;
    }

    public Integer getSuperID() {
        return superID;
    }

    public void setSuperID(Integer superID) {
        this.superID = superID;
    }

    public Integer getUrunID() {
        return urunID;
    }

    public void setUrunID(Integer urunID) {
        this.urunID = urunID;
    }

    public Short getGelenAdet() {
        return gelenAdet;
    }

    public void setGelenAdet(Short gelenAdet) {
        this.gelenAdet = gelenAdet;
    }

    public Short getKalanAdet() {
        return kalanAdet;
    }

    public void setKalanAdet(Short kalanAdet) {
        this.kalanAdet = kalanAdet;
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
        hash += (stokID != null ? stokID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stoklar)) {
            return false;
        }
        Stoklar other = (Stoklar) object;
        if ((this.stokID == null && other.stokID != null) || (this.stokID != null && !this.stokID.equals(other.stokID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mavi.Stoklar[ stokID=" + stokID + " ]";
    }
    
}

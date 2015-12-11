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
@Table(name = "bedenler")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bedenler.findAll", query = "SELECT b FROM Bedenler b"),
    @NamedQuery(name = "Bedenler.findByBedenID", query = "SELECT b FROM Bedenler b WHERE b.bedenID = :bedenID"),
    @NamedQuery(name = "Bedenler.findByBBaslik", query = "SELECT b FROM Bedenler b WHERE b.bBaslik = :bBaslik"),
    @NamedQuery(name = "Bedenler.findBySuperID", query = "SELECT b FROM Bedenler b WHERE b.superID = :superID"),
    @NamedQuery(name = "Bedenler.findByBTarih", query = "SELECT b FROM Bedenler b WHERE b.bTarih = :bTarih")})
public class Bedenler implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "bedenID")
    private Integer bedenID;
    @Column(name = "bBaslik")
    private String bBaslik;
    @Column(name = "superID")
    private Integer superID;
    @Column(name = "bTarih")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bTarih;

    public Bedenler() {
    }

    public Bedenler(Integer bedenID) {
        this.bedenID = bedenID;
    }

    public Integer getBedenID() {
        return bedenID;
    }

    public void setBedenID(Integer bedenID) {
        this.bedenID = bedenID;
    }

    public String getBBaslik() {
        return bBaslik;
    }

    public void setBBaslik(String bBaslik) {
        this.bBaslik = bBaslik;
    }

    public Integer getSuperID() {
        return superID;
    }

    public void setSuperID(Integer superID) {
        this.superID = superID;
    }

    public Date getBTarih() {
        return bTarih;
    }

    public void setBTarih(Date bTarih) {
        this.bTarih = bTarih;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bedenID != null ? bedenID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bedenler)) {
            return false;
        }
        Bedenler other = (Bedenler) object;
        if ((this.bedenID == null && other.bedenID != null) || (this.bedenID != null && !this.bedenID.equals(other.bedenID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "urunGiris.Bedenler[ bedenID=" + bedenID + " ]";
    }
    
}

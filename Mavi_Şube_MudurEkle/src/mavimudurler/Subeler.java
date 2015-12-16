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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author java6sabah
 */
@Entity
@Table(name = "subeler")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subeler.findAll", query = "SELECT s FROM Subeler s"),
    @NamedQuery(name = "Subeler.findBySubeID", query = "SELECT s FROM Subeler s WHERE s.subeID = :subeID"),
    @NamedQuery(name = "Subeler.findBySuperID", query = "SELECT s FROM Subeler s WHERE s.superID = :superID"),
    @NamedQuery(name = "Subeler.findBySBaslik", query = "SELECT s FROM Subeler s WHERE s.sBaslik = :sBaslik"),
    @NamedQuery(name = "Subeler.findBySAdres", query = "SELECT s FROM Subeler s WHERE s.sAdres = :sAdres"),
    @NamedQuery(name = "Subeler.findBySTelefon", query = "SELECT s FROM Subeler s WHERE s.sTelefon = :sTelefon"),
    @NamedQuery(name = "Subeler.findBySMail", query = "SELECT s FROM Subeler s WHERE s.sMail = :sMail"),
    @NamedQuery(name = "Subeler.findBySTarih", query = "SELECT s FROM Subeler s WHERE s.sTarih = :sTarih")})
public class Subeler implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "subeID")
    private Integer subeID;
    @Column(name = "superID")
    private Integer superID;
    @Column(name = "sBaslik")
    private String sBaslik;
    @Column(name = "sAdres")
    private String sAdres;
    @Column(name = "sTelefon")
    private String sTelefon;
    @Column(name = "sMail")
    private String sMail;
    @Column(name = "sTarih")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sTarih;

    public Subeler() {
    }

    public Subeler(Integer subeID) {
        this.subeID = subeID;
    }

    public Integer getSubeID() {
        return subeID;
    }

    public void setSubeID(Integer subeID) {
        this.subeID = subeID;
    }

    public Integer getSuperID() {
        return superID;
    }

    public void setSuperID(Integer superID) {
        this.superID = superID;
    }

    public String getSBaslik() {
        return sBaslik;
    }

    public void setSBaslik(String sBaslik) {
        this.sBaslik = sBaslik;
    }

    public String getSAdres() {
        return sAdres;
    }

    public void setSAdres(String sAdres) {
        this.sAdres = sAdres;
    }

    public String getSTelefon() {
        return sTelefon;
    }

    public void setSTelefon(String sTelefon) {
        this.sTelefon = sTelefon;
    }

    public String getSMail() {
        return sMail;
    }

    public void setSMail(String sMail) {
        this.sMail = sMail;
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
        hash += (subeID != null ? subeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subeler)) {
            return false;
        }
        Subeler other = (Subeler) object;
        if ((this.subeID == null && other.subeID != null) || (this.subeID != null && !this.subeID.equals(other.subeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mavimudurler.Subeler[ subeID=" + subeID + " ]";
    }
    
}

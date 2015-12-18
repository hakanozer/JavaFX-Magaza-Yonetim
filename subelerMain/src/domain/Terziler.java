/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

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
 * @author MetinB
 */
@Entity
@Table(name = "terziler")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Terziler.findAll", query = "SELECT t FROM Terziler t"),
    @NamedQuery(name = "Terziler.findByTerziID", query = "SELECT t FROM Terziler t WHERE t.terziID = :terziID"),
    @NamedQuery(name = "Terziler.findByMudurID", query = "SELECT t FROM Terziler t WHERE t.mudurID = :mudurID"),
    @NamedQuery(name = "Terziler.findByTAdi", query = "SELECT t FROM Terziler t WHERE t.tAdi = :tAdi"),
    @NamedQuery(name = "Terziler.findByTSoyadi", query = "SELECT t FROM Terziler t WHERE t.tSoyadi = :tSoyadi"),
    @NamedQuery(name = "Terziler.findByTTelefonu", query = "SELECT t FROM Terziler t WHERE t.tTelefonu = :tTelefonu"),
    @NamedQuery(name = "Terziler.findByTCep", query = "SELECT t FROM Terziler t WHERE t.tCep = :tCep"),
    @NamedQuery(name = "Terziler.findByTAdres", query = "SELECT t FROM Terziler t WHERE t.tAdres = :tAdres"),
    @NamedQuery(name = "Terziler.findByTTarih", query = "SELECT t FROM Terziler t WHERE t.tTarih = :tTarih")})
public class Terziler implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "terziID")
    private Integer terziID;
    @Column(name = "mudurID")
    private Integer mudurID;
    @Column(name = "tAdi")
    private String tAdi;
    @Column(name = "tSoyadi")
    private String tSoyadi;
    @Column(name = "tTelefonu")
    private String tTelefonu;
    @Column(name = "tCep")
    private String tCep;
    @Column(name = "tAdres")
    private String tAdres;
    @Column(name = "tTarih")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tTarih;

    public Terziler() {
    }

    public Terziler(Integer terziID) {
        this.terziID = terziID;
    }

    public Integer getTerziID() {
        return terziID;
    }

    public void setTerziID(Integer terziID) {
        this.terziID = terziID;
    }

    public Integer getMudurID() {
        return mudurID;
    }

    public void setMudurID(Integer mudurID) {
        this.mudurID = mudurID;
    }

    public String getTAdi() {
        return tAdi;
    }

    public void setTAdi(String tAdi) {
        this.tAdi = tAdi;
    }

    public String getTSoyadi() {
        return tSoyadi;
    }

    public void setTSoyadi(String tSoyadi) {
        this.tSoyadi = tSoyadi;
    }

    public String getTTelefonu() {
        return tTelefonu;
    }

    public void setTTelefonu(String tTelefonu) {
        this.tTelefonu = tTelefonu;
    }

    public String getTCep() {
        return tCep;
    }

    public void setTCep(String tCep) {
        this.tCep = tCep;
    }

    public String getTAdres() {
        return tAdres;
    }

    public void setTAdres(String tAdres) {
        this.tAdres = tAdres;
    }

    public Date getTTarih() {
        return tTarih;
    }

    public void setTTarih(Date tTarih) {
        this.tTarih = tTarih;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (terziID != null ? terziID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Terziler)) {
            return false;
        }
        Terziler other = (Terziler) object;
        if ((this.terziID == null && other.terziID != null) || (this.terziID != null && !this.terziID.equals(other.terziID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Terziler[ terziID=" + terziID + " ]";
    }
    
}

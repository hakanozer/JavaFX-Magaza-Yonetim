/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sadmingiris;

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
 * @author javasabah
 */
@Entity
@Table(name = "superAdmin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SuperAdmin.findAll", query = "SELECT s FROM SuperAdmin s"),
    @NamedQuery(name = "SuperAdmin.findBySuperID", query = "SELECT s FROM SuperAdmin s WHERE s.superID = :superID"),
    @NamedQuery(name = "SuperAdmin.findByKulAdi", query = "SELECT s FROM SuperAdmin s WHERE s.kulAdi = :kulAdi"),
    @NamedQuery(name = "SuperAdmin.findBySifre", query = "SELECT s FROM SuperAdmin s WHERE s.sifre = :sifre"),
    @NamedQuery(name = "SuperAdmin.findBySAdi", query = "SELECT s FROM SuperAdmin s WHERE s.sAdi = :sAdi"),
    @NamedQuery(name = "SuperAdmin.findBySSoyadi", query = "SELECT s FROM SuperAdmin s WHERE s.sSoyadi = :sSoyadi"),
    @NamedQuery(name = "SuperAdmin.findBySTelefon", query = "SELECT s FROM SuperAdmin s WHERE s.sTelefon = :sTelefon"),
    @NamedQuery(name = "SuperAdmin.findBySCep", query = "SELECT s FROM SuperAdmin s WHERE s.sCep = :sCep"),
    @NamedQuery(name = "SuperAdmin.findBySAdres", query = "SELECT s FROM SuperAdmin s WHERE s.sAdres = :sAdres"),
    @NamedQuery(name = "SuperAdmin.findBySMail", query = "SELECT s FROM SuperAdmin s WHERE s.sMail = :sMail"),
    @NamedQuery(name = "SuperAdmin.findBySTarih", query = "SELECT s FROM SuperAdmin s WHERE s.sTarih = :sTarih")})
public class SuperAdmin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "superID")
    private Integer superID;
    @Column(name = "kulAdi")
    private String kulAdi;
    @Column(name = "sifre")
    private String sifre;
    @Column(name = "sAdi")
    private String sAdi;
    @Column(name = "sSoyadi")
    private String sSoyadi;
    @Column(name = "sTelefon")
    private String sTelefon;
    @Column(name = "sCep")
    private String sCep;
    @Column(name = "sAdres")
    private String sAdres;
    @Column(name = "sMail")
    private String sMail;
    @Column(name = "sTarih")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sTarih;

    public SuperAdmin() {
    }

    public SuperAdmin(Integer superID) {
        this.superID = superID;
    }

    public Integer getSuperID() {
        return superID;
    }

    public void setSuperID(Integer superID) {
        this.superID = superID;
    }

    public String getKulAdi() {
        return kulAdi;
    }

    public void setKulAdi(String kulAdi) {
        this.kulAdi = kulAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getSAdi() {
        return sAdi;
    }

    public void setSAdi(String sAdi) {
        this.sAdi = sAdi;
    }

    public String getSSoyadi() {
        return sSoyadi;
    }

    public void setSSoyadi(String sSoyadi) {
        this.sSoyadi = sSoyadi;
    }

    public String getSTelefon() {
        return sTelefon;
    }

    public void setSTelefon(String sTelefon) {
        this.sTelefon = sTelefon;
    }

    public String getSCep() {
        return sCep;
    }

    public void setSCep(String sCep) {
        this.sCep = sCep;
    }

    public String getSAdres() {
        return sAdres;
    }

    public void setSAdres(String sAdres) {
        this.sAdres = sAdres;
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
        hash += (superID != null ? superID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SuperAdmin)) {
            return false;
        }
        SuperAdmin other = (SuperAdmin) object;
        if ((this.superID == null && other.superID != null) || (this.superID != null && !this.superID.equals(other.superID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sadmingiris.SuperAdmin[ superID=" + superID + " ]";
    }
    
}

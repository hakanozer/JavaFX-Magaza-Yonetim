/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superAdmin;

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
 * @author ERTAN
 */
@Entity
@Table(name = "superAdmin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SuperAdmin_1.findAll", query = "SELECT s FROM SuperAdmin_1 s"),
    @NamedQuery(name = "SuperAdmin_1.findBySuperID", query = "SELECT s FROM SuperAdmin_1 s WHERE s.superID = :superID"),
    @NamedQuery(name = "SuperAdmin_1.findByKulAdi", query = "SELECT s FROM SuperAdmin_1 s WHERE s.kulAdi = :kulAdi"),
    @NamedQuery(name = "SuperAdmin_1.findBySifre", query = "SELECT s FROM SuperAdmin_1 s WHERE s.sifre = :sifre"),
    @NamedQuery(name = "SuperAdmin_1.findBySAdi", query = "SELECT s FROM SuperAdmin_1 s WHERE s.sAdi = :sAdi"),
    @NamedQuery(name = "SuperAdmin_1.findBySSoyadi", query = "SELECT s FROM SuperAdmin_1 s WHERE s.sSoyadi = :sSoyadi"),
    @NamedQuery(name = "SuperAdmin_1.findBySTelefon", query = "SELECT s FROM SuperAdmin_1 s WHERE s.sTelefon = :sTelefon"),
    @NamedQuery(name = "SuperAdmin_1.findBySCep", query = "SELECT s FROM SuperAdmin_1 s WHERE s.sCep = :sCep"),
    @NamedQuery(name = "SuperAdmin_1.findBySAdres", query = "SELECT s FROM SuperAdmin_1 s WHERE s.sAdres = :sAdres"),
    @NamedQuery(name = "SuperAdmin_1.findBySMail", query = "SELECT s FROM SuperAdmin_1 s WHERE s.sMail = :sMail"),
    @NamedQuery(name = "SuperAdmin_1.findBySTarih", query = "SELECT s FROM SuperAdmin_1 s WHERE s.sTarih = :sTarih")})
public class SuperAdmin_1 implements Serializable {
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

    public SuperAdmin_1() {
    }

    public SuperAdmin_1(Integer superID) {
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
        if (!(object instanceof SuperAdmin_1)) {
            return false;
        }
        SuperAdmin_1 other = (SuperAdmin_1) object;
        if ((this.superID == null && other.superID != null) || (this.superID != null && !this.superID.equals(other.superID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "superAdmin.SuperAdmin_1[ superID=" + superID + " ]";
    }
    
}

package sezonGiris;

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

@Entity
@Table(name = "sezonlar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sezonlar.findAll", query = "SELECT s FROM Sezonlar s"),
    @NamedQuery(name = "Sezonlar.findBySezonID", query = "SELECT s FROM Sezonlar s WHERE s.sezonID = :sezonID"),
    @NamedQuery(name = "Sezonlar.findBySuperID", query = "SELECT s FROM Sezonlar s WHERE s.superID = :superID"),
    @NamedQuery(name = "Sezonlar.findBySBaslik", query = "SELECT s FROM Sezonlar s WHERE s.sBaslik = :sBaslik"),
    @NamedQuery(name = "Sezonlar.findBySDurum", query = "SELECT s FROM Sezonlar s WHERE s.sDurum = :sDurum"),
    @NamedQuery(name = "Sezonlar.findBySTarih", query = "SELECT s FROM Sezonlar s WHERE s.sTarih = :sTarih")})
public class Sezonlar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "sezonID")
    private Integer sezonID;
    @Column(name = "superID")
    private Integer superID;
    @Column(name = "sBaslik")
    private String sBaslik;
    @Column(name = "sDurum")
    private Boolean sDurum;
    @Column(name = "sTarih")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sTarih;

    public Sezonlar() {
    }

    public Sezonlar(Integer sezonID) {
        this.sezonID = sezonID;
    }

    public Integer getSezonID() {
        return sezonID;
    }

    public void setSezonID(Integer sezonID) {
        this.sezonID = sezonID;
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

    public Boolean getSDurum() {
        return sDurum;
    }

    public void setSDurum(Boolean sDurum) {
        this.sDurum = sDurum;
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
        hash += (sezonID != null ? sezonID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sezonlar)) {
            return false;
        }
        Sezonlar other = (Sezonlar) object;
        if ((this.sezonID == null && other.sezonID != null) || (this.sezonID != null && !this.sezonID.equals(other.sezonID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sezonGiris.Sezonlar[ sezonID=" + sezonID + " ]";
    }
    
}

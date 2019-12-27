/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DATLPSE62823
 */
@Entity
@Table(name = "BMIConstanst")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BMIConstanst.findAll", query = "SELECT b FROM BMIConstanst b"),
    @NamedQuery(name = "BMIConstanst.findById", query = "SELECT b FROM BMIConstanst b WHERE b.id = :id"),
    @NamedQuery(name = "BMIConstanst.findByMaleWeightAmount", query = "SELECT b FROM BMIConstanst b WHERE b.maleWeightAmount = :maleWeightAmount"),
    @NamedQuery(name = "BMIConstanst.findByMaleHeightAmount", query = "SELECT b FROM BMIConstanst b WHERE b.maleHeightAmount = :maleHeightAmount"),
    @NamedQuery(name = "BMIConstanst.findByMaleAgeAmount", query = "SELECT b FROM BMIConstanst b WHERE b.maleAgeAmount = :maleAgeAmount"),
    @NamedQuery(name = "BMIConstanst.findByMaleEpsilon", query = "SELECT b FROM BMIConstanst b WHERE b.maleEpsilon = :maleEpsilon"),
    @NamedQuery(name = "BMIConstanst.findByFemaleWeightAmount", query = "SELECT b FROM BMIConstanst b WHERE b.femaleWeightAmount = :femaleWeightAmount"),
    @NamedQuery(name = "BMIConstanst.findByFemaleHeightAmount", query = "SELECT b FROM BMIConstanst b WHERE b.femaleHeightAmount = :femaleHeightAmount"),
    @NamedQuery(name = "BMIConstanst.findByFemaleAgeAmount", query = "SELECT b FROM BMIConstanst b WHERE b.femaleAgeAmount = :femaleAgeAmount"),
    @NamedQuery(name = "BMIConstanst.findByFemaleEpsilon", query = "SELECT b FROM BMIConstanst b WHERE b.femaleEpsilon = :femaleEpsilon"),
    @NamedQuery(name = "BMIConstanst.findByCreatedAt", query = "SELECT b FROM BMIConstanst b WHERE b.createdAt = :createdAt"),
    @NamedQuery(name = "BMIConstanst.findByUpdatedAt", query = "SELECT b FROM BMIConstanst b WHERE b.updatedAt = :updatedAt"),
    @NamedQuery(name = "BMIConstanst.findLastest", query = "SELECT b FROM BMIConstanst b ORDER BY b.id DESC")})
public class BMIConstanst implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MaleWeightAmount")
    private Double maleWeightAmount;
    @Column(name = "MaleHeightAmount")
    private Double maleHeightAmount;
    @Column(name = "MaleAgeAmount")
    private Double maleAgeAmount;
    @Column(name = "MaleEpsilon")
    private Double maleEpsilon;
    @Column(name = "FemaleWeightAmount")
    private Double femaleWeightAmount;
    @Column(name = "FemaleHeightAmount")
    private Double femaleHeightAmount;
    @Column(name = "FemaleAgeAmount")
    private Double femaleAgeAmount;
    @Column(name = "FemaleEpsilon")
    private Double femaleEpsilon;
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "UpdatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToMany(mappedBy = "bmiId")
    private List<BMILevelOfWork> bMILevelOfWorkList;
    @OneToMany(mappedBy = "bmiId")
    private List<BMINeed> bMINeedList;

    public BMIConstanst() {
    }

    public BMIConstanst(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMaleWeightAmount() {
        return maleWeightAmount;
    }

    public void setMaleWeightAmount(Double maleWeightAmount) {
        this.maleWeightAmount = maleWeightAmount;
    }

    public Double getMaleHeightAmount() {
        return maleHeightAmount;
    }

    public void setMaleHeightAmount(Double maleHeightAmount) {
        this.maleHeightAmount = maleHeightAmount;
    }

    public Double getMaleAgeAmount() {
        return maleAgeAmount;
    }

    public void setMaleAgeAmount(Double maleAgeAmount) {
        this.maleAgeAmount = maleAgeAmount;
    }

    public Double getMaleEpsilon() {
        return maleEpsilon;
    }

    public void setMaleEpsilon(Double maleEpsilon) {
        this.maleEpsilon = maleEpsilon;
    }

    public Double getFemaleWeightAmount() {
        return femaleWeightAmount;
    }

    public void setFemaleWeightAmount(Double femaleWeightAmount) {
        this.femaleWeightAmount = femaleWeightAmount;
    }

    public Double getFemaleHeightAmount() {
        return femaleHeightAmount;
    }

    public void setFemaleHeightAmount(Double femaleHeightAmount) {
        this.femaleHeightAmount = femaleHeightAmount;
    }

    public Double getFemaleAgeAmount() {
        return femaleAgeAmount;
    }

    public void setFemaleAgeAmount(Double femaleAgeAmount) {
        this.femaleAgeAmount = femaleAgeAmount;
    }

    public Double getFemaleEpsilon() {
        return femaleEpsilon;
    }

    public void setFemaleEpsilon(Double femaleEpsilon) {
        this.femaleEpsilon = femaleEpsilon;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

//    @XmlTransient
    public List<BMILevelOfWork> getBMILevelOfWorkList() {
        return bMILevelOfWorkList;
    }

    public void setBMILevelOfWorkList(List<BMILevelOfWork> bMILevelOfWorkList) {
        this.bMILevelOfWorkList = bMILevelOfWorkList;
    }

//    @XmlTransient
    public List<BMINeed> getBMINeedList() {
        return bMINeedList;
    }

    public void setBMINeedList(List<BMINeed> bMINeedList) {
        this.bMINeedList = bMINeedList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BMIConstanst)) {
            return false;
        }
        BMIConstanst other = (BMIConstanst) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datlp.entity.BMIConstanst[ id=" + id + " ]";
    }
    
}

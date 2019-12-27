/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DATLPSE62823
 */
@Entity
@Table(name = "NutritionValue")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NutritionValue.findAll", query = "SELECT n FROM NutritionValue n"),
    @NamedQuery(name = "NutritionValue.findById", query = "SELECT n FROM NutritionValue n WHERE n.id = :id"),
    @NamedQuery(name = "NutritionValue.findByName", query = "SELECT n FROM NutritionValue n WHERE n.name = :name"),
    @NamedQuery(name = "NutritionValue.findByAmount", query = "SELECT n FROM NutritionValue n WHERE n.amount = :amount"),
    @NamedQuery(name = "NutritionValue.findByUnit", query = "SELECT n FROM NutritionValue n WHERE n.unit = :unit"),
    @NamedQuery(name = "NutritionValue.findByHashName", query = "SELECT n FROM NutritionValue n WHERE n.hashName = :hashName"),
    @NamedQuery(name = "NutritionValue.findByHashContent", query = "SELECT n FROM NutritionValue n WHERE n.hashContent = :hashContent"),
    @NamedQuery(name = "NutritionValue.findByCreatedAt", query = "SELECT n FROM NutritionValue n WHERE n.createdAt = :createdAt"),
    @NamedQuery(name = "NutritionValue.findByUpdatedAt", query = "SELECT n FROM NutritionValue n WHERE n.updatedAt = :updatedAt")})
public class NutritionValue implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Amount")
    private Double amount;
    @Column(name = "Unit")
    private String unit;
    @Column(name = "HashName")
    private Integer hashName;
    @Column(name = "HashContent")
    private Integer hashContent;
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "UpdatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "NutriId", referencedColumnName = "Id")
    @ManyToOne
    private Nutrition nutriId;

    public NutritionValue() {
    }

    public NutritionValue(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getHashName() {
        return hashName;
    }

    public void setHashName(Integer hashName) {
        this.hashName = hashName;
    }

    public Integer getHashContent() {
        return hashContent;
    }

    public void setHashContent(Integer hashContent) {
        this.hashContent = hashContent;
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

    @XmlTransient
    public Nutrition getNutriId() {
        return nutriId;
    }

    public void setNutriId(Nutrition nutriId) {
        this.nutriId = nutriId;
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
        if (!(object instanceof NutritionValue)) {
            return false;
        }
        NutritionValue other = (NutritionValue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datlp.entity.NutritionValue[ id=" + id + " ]";
    }
    
}

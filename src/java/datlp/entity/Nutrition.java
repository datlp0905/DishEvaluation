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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "Nutrition")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nutrition.findAll", query = "SELECT n FROM Nutrition n"),
    @NamedQuery(name = "Nutrition.findById", query = "SELECT n FROM Nutrition n WHERE n.id = :id"),
    @NamedQuery(name = "Nutrition.findByName", query = "SELECT n FROM Nutrition n WHERE n.name = :name"),
    @NamedQuery(name = "Nutrition.findByEnergyAmount", query = "SELECT n FROM Nutrition n WHERE n.energyAmount = :energyAmount"),
    @NamedQuery(name = "Nutrition.findByEnergyUnit", query = "SELECT n FROM Nutrition n WHERE n.energyUnit = :energyUnit"),
    @NamedQuery(name = "Nutrition.findByCalculatedPer", query = "SELECT n FROM Nutrition n WHERE n.calculatedPer = :calculatedPer"),
    @NamedQuery(name = "Nutrition.findByHashName", query = "SELECT n FROM Nutrition n WHERE n.hashName = :hashName"),
    @NamedQuery(name = "Nutrition.findByHashContent", query = "SELECT n FROM Nutrition n WHERE n.hashContent = :hashContent"),
    @NamedQuery(name = "Nutrition.findByCreatedAt", query = "SELECT n FROM Nutrition n WHERE n.createdAt = :createdAt"),
    @NamedQuery(name = "Nutrition.findByUpdatedAt", query = "SELECT n FROM Nutrition n WHERE n.updatedAt = :updatedAt")})
public class Nutrition implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "EnergyAmount")
    private Double energyAmount;
    @Column(name = "EnergyUnit")
    private String energyUnit;
    @Column(name = "CalculatedPer")
    private Double calculatedPer;
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
    @OneToMany(mappedBy = "nutriId")
    private List<NutritionValue> nutritionValueList;
    @JoinColumn(name = "GroupId", referencedColumnName = "Id")
    @ManyToOne
    private NutritionGroup groupId;

    public Nutrition() {
    }

    public Nutrition(Integer id) {
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

    public Double getEnergyAmount() {
        return energyAmount;
    }

    public void setEnergyAmount(Double energyAmount) {
        this.energyAmount = energyAmount;
    }

    public String getEnergyUnit() {
        return energyUnit;
    }

    public void setEnergyUnit(String energyUnit) {
        this.energyUnit = energyUnit;
    }

    public Double getCalculatedPer() {
        return calculatedPer;
    }

    public void setCalculatedPer(Double calculatedPer) {
        this.calculatedPer = calculatedPer;
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

//    @XmlTransient
    @XmlTransient
    public List<NutritionValue> getNutritionValueList() {
        return nutritionValueList;
    }

    public void setNutritionValueList(List<NutritionValue> nutritionValueList) {
        this.nutritionValueList = nutritionValueList;
    }

    public NutritionGroup getGroupId() {
        return groupId;
    }

    public void setGroupId(NutritionGroup groupId) {
        this.groupId = groupId;
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
        if (!(object instanceof Nutrition)) {
            return false;
        }
        Nutrition other = (Nutrition) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datlp.entity.Nutrition[ id=" + id + " ]";
    }
    
}

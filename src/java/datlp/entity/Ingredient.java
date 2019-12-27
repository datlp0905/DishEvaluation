/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.entity;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DATLPSE62823
 */
@Entity
@Table(name = "Ingredient")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ingredient.findAll", query = "SELECT i FROM Ingredient i"),
    @NamedQuery(name = "Ingredient.findById", query = "SELECT i FROM Ingredient i WHERE i.id = :id"),
    @NamedQuery(name = "Ingredient.findByName", query = "SELECT i FROM Ingredient i WHERE i.name = :name"),
    @NamedQuery(name = "Ingredient.findByAmount", query = "SELECT i FROM Ingredient i WHERE i.amount = :amount"),
    @NamedQuery(name = "Ingredient.findByUnit", query = "SELECT i FROM Ingredient i WHERE i.unit = :unit"),
    @NamedQuery(name = "Ingredient.findByNutritionHash", query = "SELECT i FROM Ingredient i WHERE i.nutritionHash = :nutritionHash"),
    @NamedQuery(name = "Ingredient.findByNutritionRecommend", query = "SELECT i FROM Ingredient i WHERE i.nutritionRecommend = :nutritionRecommend"),
    @NamedQuery(name = "Ingredient.findByValuable", query = "SELECT i FROM Ingredient i WHERE i.valuable = :valuable"),
    @NamedQuery(name = "Ingredient.findByHashName", query = "SELECT i FROM Ingredient i WHERE i.hashName = :hashName"),
    @NamedQuery(name = "Ingredient.findByHashContent", query = "SELECT i FROM Ingredient i WHERE i.hashContent = :hashContent"),
    @NamedQuery(name = "Ingredient.findByUnnormalizedUnit", query = "SELECT i FROM Ingredient i WHERE i.valuable = TRUE AND i.unit != :unit and i.standardAmount IS NULL"),
    @NamedQuery(name = "Ingredient.findByUnMappingNutrition", query = "SELECT i FROM Ingredient i WHERE i.valuable = TRUE and i.nutritionRecommend IS NOT NULL"),
    @NamedQuery(name = "Ingredient.findByDishId", query = "SELECT i FROM Ingredient i WHERE i.dishId.id = :dishId"),
    @NamedQuery(name = "Ingredient.findByUnitAndStandardAmountNull", 
            query = "SELECT i FROM Ingredient i WHERE i.unit = :unit AND i.standardAmount IS NULL"),
    @NamedQuery(name = "Ingredient.findByNameAndUnitAndStandardAmountNull",
            query = "SELECT i FROM Ingredient i WHERE i.name = :name AND i.unit = :unit AND i.standardAmount IS NULL"),
    @NamedQuery(name = "Ingredient.findByNameAndUnMappingNutrition", 
            query = "SELECT i FROM Ingredient i WHERE i.name = :name AND i.valuable = TRUE and i.nutritionRecommend IS NOT NULL")})
public class Ingredient implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "Amount")
    private Double amount;
    @Column(name = "StandardAmount")
    private Double standardAmount;
    @Column(name = "Unit")
    private String unit;
    @Column(name = "NutritionHash")
    private Integer nutritionHash;
    @Column(name = "NutritionRecommend")
    private Integer nutritionRecommend;
    @Column(name = "HashName")
    private Integer hashName;
    @Column(name = "HashContent")
    private Integer hashContent;
    @Column(name = "Valuable")
    private Boolean valuable;
    @Column(name = "Energy")
    private Double energy;
    @JoinColumn(name = "DishId", referencedColumnName = "Id")
    @ManyToOne
    private Dish dishId;

    public Ingredient() {
    }

    public Ingredient(Integer id) {
        this.id = id;
    }

    public Ingredient(Integer id, String name, double amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
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

    public Integer getNutritionHash() {
        return nutritionHash;
    }

    public void setNutritionHash(Integer nutritionHash) {
        this.nutritionHash = nutritionHash;
    }

    public Integer getNutritionRecommend() {
        return nutritionRecommend;
    }

    public void setNutritionRecommend(Integer nutritionRecommend) {
        this.nutritionRecommend = nutritionRecommend;
    }

    public Boolean getValuable() {
        return valuable;
    }

    public void setValuable(Boolean valuable) {
        this.valuable = valuable;
    }

    @XmlTransient
    public Dish getDishId() {
        return dishId;
    }

    public void setDishId(Dish dishId) {
        this.dishId = dishId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingredient)) {
            return false;
        }
        Ingredient other = (Ingredient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datlp.entity.Ingredient[ id=" + id + " ]";
    }

    public Double getStandardAmount() {
        return standardAmount;
    }

    public void setStandardAmount(Double standardAmount) {
        this.standardAmount = standardAmount;
    }

    public Double getEnergy() {
        return energy;
    }

    public void setEnergy(Double energy) {
        this.energy = energy;
    }
    
}

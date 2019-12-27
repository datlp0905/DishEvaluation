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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DATLPSE62823
 */
@Entity
@Table(name = "StandardUnitDictionary")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StandardUnitDictionary.findAll", query = "SELECT s FROM StandardUnitDictionary s"),
    @NamedQuery(name = "StandardUnitDictionary.findById", query = "SELECT s FROM StandardUnitDictionary s WHERE s.id = :id"),
    @NamedQuery(name = "StandardUnitDictionary.findByIngredientName", query = "SELECT s FROM StandardUnitDictionary s WHERE s.ingredientName = :ingredientName"),
    @NamedQuery(name = "StandardUnitDictionary.findByUnit", query = "SELECT s FROM StandardUnitDictionary s WHERE s.unit = :unit"),
    @NamedQuery(name = "StandardUnitDictionary.findByEqualTo", query = "SELECT s FROM StandardUnitDictionary s WHERE s.equalTo = :equalTo"),
    @NamedQuery(name = "StandardUnitDictionary.findByCreatedAt", query = "SELECT s FROM StandardUnitDictionary s WHERE s.createdAt = :createdAt"),
    @NamedQuery(name = "StandardUnitDictionary.findByUnitAndIngredientName", 
            query = "SELECT s FROM StandardUnitDictionary s WHERE s.unit = :unit AND s.ingredientName = :ingredientName")})
public class StandardUnitDictionary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "IngredientName")
    private String ingredientName;
    @Basic(optional = false)
    @Column(name = "Unit")
    private String unit;
    @Basic(optional = false)
    @Column(name = "EqualTo")
    private double equalTo;
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public StandardUnitDictionary() {
    }

    public StandardUnitDictionary(Integer id) {
        this.id = id;
    }

    public StandardUnitDictionary(Integer id, String ingredientName, String unit, double equalTo) {
        this.id = id;
        this.ingredientName = ingredientName;
        this.unit = unit;
        this.equalTo = equalTo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getEqualTo() {
        return equalTo;
    }

    public void setEqualTo(double equalTo) {
        this.equalTo = equalTo;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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
        if (!(object instanceof StandardUnitDictionary)) {
            return false;
        }
        StandardUnitDictionary other = (StandardUnitDictionary) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datlp.entity.StandardUnitDictionary[ id=" + id + " ]";
    }
    
}

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DATLPSE62823
 */
@Entity
@Table(name = "NutritionGroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NutritionGroup.findAll", query = "SELECT n FROM NutritionGroup n"),
    @NamedQuery(name = "NutritionGroup.findById", query = "SELECT n FROM NutritionGroup n WHERE n.id = :id"),
    @NamedQuery(name = "NutritionGroup.findByName", query = "SELECT n FROM NutritionGroup n WHERE n.name = :name"),
    @NamedQuery(name = "NutritionGroup.findByHashName", query = "SELECT n FROM NutritionGroup n WHERE n.hashName = :hashName"),
    @NamedQuery(name = "NutritionGroup.findByCreatedAt", query = "SELECT n FROM NutritionGroup n WHERE n.createdAt = :createdAt"),
    @NamedQuery(name = "NutritionGroup.findByUpdatedAt", query = "SELECT n FROM NutritionGroup n WHERE n.updatedAt = :updatedAt")})
public class NutritionGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Name")
    private String name;
    @Column(name = "HashName")
    private Integer hashName;
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "UpdatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToMany(mappedBy = "groupId")
    private List<Nutrition> nutritionList;

    public NutritionGroup() {
    }

    public NutritionGroup(Integer id) {
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

    public Integer getHashName() {
        return hashName;
    }

    public void setHashName(Integer hashName) {
        this.hashName = hashName;
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
    public List<Nutrition> getNutritionList() {
        return nutritionList;
    }

    public void setNutritionList(List<Nutrition> nutritionList) {
        this.nutritionList = nutritionList;
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
        if (!(object instanceof NutritionGroup)) {
            return false;
        }
        NutritionGroup other = (NutritionGroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datlp.entity.NutritionGroup[ id=" + id + " ]";
    }
    
}

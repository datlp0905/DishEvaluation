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
@Table(name = "IngredientValueDictionary")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IngredientValueDictionary.findAll", query = "SELECT i FROM IngredientValueDictionary i"),
    @NamedQuery(name = "IngredientValueDictionary.findById", query = "SELECT i FROM IngredientValueDictionary i WHERE i.id = :id"),
    @NamedQuery(name = "IngredientValueDictionary.findByIngredentName", query = "SELECT i FROM IngredientValueDictionary i WHERE i.ingredentName = :ingredentName"),
    @NamedQuery(name = "IngredientValueDictionary.findByHashValueName", query = "SELECT i FROM IngredientValueDictionary i WHERE i.hashValueName = :hashValueName"),
    @NamedQuery(name = "IngredientValueDictionary.findByCreatedAt", query = "SELECT i FROM IngredientValueDictionary i WHERE i.createdAt = :createdAt"),
    @NamedQuery(name = "IngredientValueDictionary.findByIngredentNameAndHashValueName", 
                query = "SELECT i FROM IngredientValueDictionary i WHERE i.ingredentName = :ingredientName AND i.hashValueName = :hashValueName"),
    @NamedQuery(name = "IngredientValueDictionary.findByIngredentNameLike", query = "SELECT i FROM IngredientValueDictionary i WHERE i.ingredentName LIKE :ingredentName")})
public class IngredientValueDictionary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "IngredentName")
    private String ingredentName;
    @Column(name = "HashValueName")
    private Integer hashValueName;
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public IngredientValueDictionary() {
    }

    public IngredientValueDictionary(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHashValueName() {
        return hashValueName;
    }

    public void setHashValueName(Integer hashValueName) {
        this.hashValueName = hashValueName;
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
        if (!(object instanceof IngredientValueDictionary)) {
            return false;
        }
        IngredientValueDictionary other = (IngredientValueDictionary) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datlp.entity.IngredientValueDictionary[ id=" + id + " ]";
    }

    public String getIngredentName() {
        return ingredentName;
    }

    public void setIngredentName(String ingredentName) {
        this.ingredentName = ingredentName;
    }
    
}

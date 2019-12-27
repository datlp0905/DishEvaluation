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

/**
 *
 * @author DATLPSE62823
 */
@Entity
@Table(name = "DishSubCategory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DishSubCategory.findAll", query = "SELECT d FROM DishSubCategory d"),
    @NamedQuery(name = "DishSubCategory.findById", query = "SELECT d FROM DishSubCategory d WHERE d.id = :id"),
    @NamedQuery(name = "DishSubCategory.findByDishIdAndSubCateId", 
            query = "SELECT d FROM DishSubCategory d WHERE d.dishId.id = :dishId and d.subCateId.id = :subCateId"),
    @NamedQuery(name = "DishSubCategory.findBySubCateIdIn", 
            query = "SELECT d FROM DishSubCategory d WHERE d.subCateId.id IN :listSubCateIds")})
public class DishSubCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "DishId", referencedColumnName = "Id")
    @ManyToOne
    private Dish dishId;
    @JoinColumn(name = "SubCateId", referencedColumnName = "Id")
    @ManyToOne
    private SubCategory subCateId;

    public DishSubCategory() {
    }

    public DishSubCategory(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Dish getDishId() {
        return dishId;
    }

    public void setDishId(Dish dishId) {
        this.dishId = dishId;
    }

    public SubCategory getSubCateId() {
        return subCateId;
    }

    public void setSubCateId(SubCategory subCateId) {
        this.subCateId = subCateId;
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
        if (!(object instanceof DishSubCategory)) {
            return false;
        }
        DishSubCategory other = (DishSubCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datlp.entity.DishSubCategory[ id=" + id + " ]";
    }
    
}

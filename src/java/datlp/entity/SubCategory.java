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
@Table(name = "SubCategory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubCategory.findAll", query = "SELECT s FROM SubCategory s"),
    @NamedQuery(name = "SubCategory.findById", query = "SELECT s FROM SubCategory s WHERE s.id = :id"),
    @NamedQuery(name = "SubCategory.findByName", query = "SELECT s FROM SubCategory s WHERE s.name = :name"),
    @NamedQuery(name = "SubCategory.findByHashName", query = "SELECT s FROM SubCategory s WHERE s.hashName = :hashName"),
    @NamedQuery(name = "SubCategory.findByCreatedAt", query = "SELECT s FROM SubCategory s WHERE s.createdAt = :createdAt"),
    @NamedQuery(name = "SubCategory.findByUpdatedAt", query = "SELECT s FROM SubCategory s WHERE s.updatedAt = :updatedAt")})
public class SubCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
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
    @JoinColumn(name = "MainCateId", referencedColumnName = "Id")
    @ManyToOne
    private MainCategory mainCateId;
    @OneToMany(mappedBy = "subCateId")
    private List<DishSubCategory> dishSubCategoryList;

    public SubCategory() {
    }

    public SubCategory(Integer id) {
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
    public MainCategory getMainCateId() {
        return mainCateId;
    }

    public void setMainCateId(MainCategory mainCateId) {
        this.mainCateId = mainCateId;
    }

    @XmlTransient
    public List<DishSubCategory> getDishSubCategoryList() {
        return dishSubCategoryList;
    }

    public void setDishSubCategoryList(List<DishSubCategory> dishSubCategoryList) {
        this.dishSubCategoryList = dishSubCategoryList;
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
        if (!(object instanceof SubCategory)) {
            return false;
        }
        SubCategory other = (SubCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datlp.entity.SubCategory[ id=" + id + " ]";
    }
    
}

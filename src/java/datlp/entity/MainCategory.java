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
@Table(name = "MainCategory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MainCategory.findAll", query = "SELECT m FROM MainCategory m"),
    @NamedQuery(name = "MainCategory.findById", query = "SELECT m FROM MainCategory m WHERE m.id = :id"),
    @NamedQuery(name = "MainCategory.findByName", query = "SELECT m FROM MainCategory m WHERE m.name = :name"),
    @NamedQuery(name = "MainCategory.findByHashName", query = "SELECT m FROM MainCategory m WHERE m.hashName = :hashName"),
    @NamedQuery(name = "MainCategory.findByCreatedAt", query = "SELECT m FROM MainCategory m WHERE m.createdAt = :createdAt"),
    @NamedQuery(name = "MainCategory.findByUpdatedAt", query = "SELECT m FROM MainCategory m WHERE m.updatedAt = :updatedAt")})
public class MainCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Column(name = "HashName")
    private Integer hashName;
    @Basic(optional = false)
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "UpdatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToMany(mappedBy = "mainCateId")
    private List<SubCategory> subCategoryList;

    public MainCategory() {
    }

    public MainCategory(Integer id) {
        this.id = id;
    }

    public MainCategory(Integer id, String name, Date createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
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

//    @XmlTransient
    public List<SubCategory> getSubCategoryList() {
        return subCategoryList;
    }

    public void setSubCategoryList(List<SubCategory> subCategoryList) {
        this.subCategoryList = subCategoryList;
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
        if (!(object instanceof MainCategory)) {
            return false;
        }
        MainCategory other = (MainCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datlp.entity.MainCategory[ id=" + id + " ]";
    }
    
}

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DATLPSE62823
 */
@Entity
@Table(name = "BMILevelOfWork")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BMILevelOfWork.findAll", query = "SELECT b FROM BMILevelOfWork b"),
    @NamedQuery(name = "BMILevelOfWork.findById", query = "SELECT b FROM BMILevelOfWork b WHERE b.id = :id"),
    @NamedQuery(name = "BMILevelOfWork.findByInfo", query = "SELECT b FROM BMILevelOfWork b WHERE b.info = :info"),
    @NamedQuery(name = "BMILevelOfWork.findByValue", query = "SELECT b FROM BMILevelOfWork b WHERE b.value = :value")})
public class BMILevelOfWork implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(max = 100)
    @Column(name = "Info")
    private String info;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Value")
    private Double value;
    @JoinColumn(name = "BMI_ID", referencedColumnName = "Id")
    @ManyToOne
    private BMIConstanst bmiId;

    public BMILevelOfWork() {
    }

    public BMILevelOfWork(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @XmlTransient
    public BMIConstanst getBmiId() {
        return bmiId;
    }

    public void setBmiId(BMIConstanst bmiId) {
        this.bmiId = bmiId;
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
        if (!(object instanceof BMILevelOfWork)) {
            return false;
        }
        BMILevelOfWork other = (BMILevelOfWork) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datlp.entity.BMILevelOfWork[ id=" + id + " ]";
    }
    
}

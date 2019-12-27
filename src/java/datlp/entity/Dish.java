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
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author DATLPSE62823
 */
@Entity
@Table(name = "Dish")
@XmlRootElement
@XmlType(propOrder = {
    "id",
    "name",
    "description",
    "level",
    "recipeYield",
    "cookTime",
    "img",
    "link",
    "hashName",
    "hashContent",
    "createdBy",
    "createdAt",
    "updatedBy",
    "updatedAt",
    "ingredientList"
})
@NamedQueries({
    @NamedQuery(name = "Dish.findAll", query = "SELECT d FROM Dish d"),
    @NamedQuery(name = "Dish.findById", query = "SELECT d FROM Dish d WHERE d.id = :id"),
    @NamedQuery(name = "Dish.findByName", query = "SELECT d FROM Dish d WHERE d.name = :name"),
    @NamedQuery(name = "Dish.findByDescription", query = "SELECT d FROM Dish d WHERE d.description = :description"),
    @NamedQuery(name = "Dish.findByLevel", query = "SELECT d FROM Dish d WHERE d.level = :level"),
    @NamedQuery(name = "Dish.findByRecipeYield", query = "SELECT d FROM Dish d WHERE d.recipeYield = :recipeYield"),
    @NamedQuery(name = "Dish.findByCookTime", query = "SELECT d FROM Dish d WHERE d.cookTime = :cookTime"),
    @NamedQuery(name = "Dish.findByImg", query = "SELECT d FROM Dish d WHERE d.img = :img"),
    @NamedQuery(name = "Dish.findByLink", query = "SELECT d FROM Dish d WHERE d.link = :link"),
    @NamedQuery(name = "Dish.findByHashName", query = "SELECT d FROM Dish d WHERE d.hashName = :hashName"),
    @NamedQuery(name = "Dish.findByHashContent", query = "SELECT d FROM Dish d WHERE d.hashContent = :hashContent"),
    @NamedQuery(name = "Dish.findByCreatedBy", query = "SELECT d FROM Dish d WHERE d.createdBy = :createdBy"),
    @NamedQuery(name = "Dish.findByCreatedAt", query = "SELECT d FROM Dish d WHERE d.createdAt = :createdAt"),
    @NamedQuery(name = "Dish.findByUpdatedBy", query = "SELECT d FROM Dish d WHERE d.updatedBy = :updatedBy"),
    @NamedQuery(name = "Dish.findByUpdatedAt", query = "SELECT d FROM Dish d WHERE d.updatedAt = :updatedAt"),
    @NamedQuery(name = "Dish.finByEnergyFromTo", 
            query = "SELECT d FROM Dish d "
                    + "WHERE d.id IN (SELECT i.dishId.id FROM Ingredient i "
                    + "                 GROUP BY i.dishId.id"
                    + "                 HAVING SUM(i.energy) BETWEEN :from AND :to)")})
public class Dish implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Description")
    private String description;
    @Column(name = "Level")
    private String level;
    @Column(name = "RecipeYield")
    private String recipeYield;
    @Column(name = "CookTime")
    private String cookTime;
    @Column(name = "Img")
    private String img;
    @Column(name = "Link")
    private String link;
    @Column(name = "HashName")
    private Integer hashName;
    @Column(name = "HashContent")
    private Integer hashContent;
    @Column(name = "CreatedBy")
    private String createdBy;
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "UpdatedBy")
    private String updatedBy;
    @Column(name = "UpdatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToMany(mappedBy = "dishId")
    private List<Ingredient> ingredientList;
    @OneToMany(mappedBy = "dishId")
    private List<DishSubCategory> dishSubCategoryList;

    public Dish() {
    }

    public Dish(Integer id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRecipeYield() {
        return recipeYield;
    }

    public void setRecipeYield(String recipeYield) {
        this.recipeYield = recipeYield;
    }

    public String getCookTime() {
        return cookTime;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

//    @XmlTransient
    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
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
        if (!(object instanceof Dish)) {
            return false;
        }
        Dish other = (Dish) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datlp.entity.Dish[ id=" + id + " ]";
    }
    
    public Double getTotalEnergy() {
        Double total = 0.0;
        for (Ingredient ingredient : ingredientList) {
            if(ingredient.getEnergy() != null) {
                total += ingredient.getEnergy();
            }
        }
        return total;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.dto;

import datlp.entity.Ingredient;
import datlp.entity.Nutrition;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DATLPSE62823
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class UnmappingIngredientDTO implements Serializable{
    
    private Ingredient ingredient;
    
    private Nutrition recommendedNutrition;

    public UnmappingIngredientDTO() {
    }

    public UnmappingIngredientDTO(Ingredient ingredient, Nutrition recommendedNutrition) {
        this.ingredient = ingredient;
        this.recommendedNutrition = recommendedNutrition;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Nutrition getRecommendedNutrition() {
        return recommendedNutrition;
    }

    public void setRecommendedNutrition(Nutrition recommendedNutrition) {
        this.recommendedNutrition = recommendedNutrition;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.mapper;

import datlp.entity.Nutrition;
import datlp.entity.NutritionValue;
import datlp.jaxb.nutrition.IngredientType;
import datlp.jaxb.nutrition.NutritionValuesType;

/**
 *
 * @author DATLPSE62823
 */
public interface NutritionMapper {
    
    Nutrition jaxbToEntity(IngredientType jaxbObj);
    
    NutritionValue jaxbToEntitt(NutritionValuesType jaxbObj, String nutriName);
}

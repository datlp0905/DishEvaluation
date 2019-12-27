/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.service;

import datlp.entity.Ingredient;
import java.util.List;

/**
 *
 * @author DATLPSE62823
 */
public interface IngredientService {
    
    Ingredient matchingIngredientToNutrition(int ingredientHash, int nutritionHash);
    
    Ingredient normalizeUnit(int ingredientId, double amout, boolean isIndependentUnit);
    
    double computeEnergy(int ingredientId);
    
    double computeEnergy(Ingredient ingredient);
    
    List<Ingredient> findByDishId(int dishId);
    
    void autoNormalizeUnit(String ingredientName, String unit, double amount);
    
    void autoMatchingIngredientToNutrition(String ingredientName, int nutritionHash);
}

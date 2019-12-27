/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.service;

import datlp.dto.UnmappingIngredientDTO;
import datlp.entity.Dish;
import datlp.entity.Ingredient;
import datlp.entity.NutritionValue;
import datlp.jaxb.dish.DishInfoType;
import java.util.List;

/**
 *
 * @author DATLPSE62823
 */
public interface DishService {
    
    void saveDishToDB(DishInfoType infoType);
    
    List<Ingredient> findByUnnormalizedUnit();
    
    List<Dish> findByCategoryIds(List<Integer> categoryIds);
    
    List<Dish> findByEnergyNeed(Double energyNeed, Integer numberOfDishes);
    
    List<UnmappingIngredientDTO> findByUnMappingNutrition();
    
    List<NutritionValue> findNutritionValueByDishId(int dishId);
}

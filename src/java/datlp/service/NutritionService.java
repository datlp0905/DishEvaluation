/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.service;

import datlp.entity.Nutrition;
import datlp.jaxb.nutrition.NutritionType;
import java.util.List;

/**
 *
 * @author DATLPSE62823
 */
public interface NutritionService {
    
    void saveNutritionToDB(NutritionType nutritionType);
    
    List<Nutrition> getAll();
    
    Nutrition findByHashName(int nutritionName);
}

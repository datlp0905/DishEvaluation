/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.service;

import datlp.entity.NutritionGroup;
import java.util.List;

/**
 *
 * @author DATLPSE62823
 */
public interface NutritionGroupService {
    
    List<NutritionGroup> findAll();
    
    NutritionGroup findById(int id);
    
    NutritionGroup findByHashName(int hashName);
    
    NutritionGroup create(NutritionGroup nutritionGroup);
    
    NutritionGroup update(NutritionGroup nutritionGroup);
}

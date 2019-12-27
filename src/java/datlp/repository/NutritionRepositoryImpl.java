/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.repository;

import datlp.entity.Nutrition;

/**
 *
 * @author DATLPSE62823
 */
public class NutritionRepositoryImpl extends BaseRepositoryImpl<Nutrition, Integer>
        implements NutritionRepository{

    public NutritionRepositoryImpl() {
        super(Nutrition.class);
    }
    
}

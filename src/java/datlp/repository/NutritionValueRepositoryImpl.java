/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.repository;

import datlp.entity.NutritionValue;

/**
 *
 * @author DATLPSE62823
 */
public class NutritionValueRepositoryImpl
        extends BaseRepositoryImpl<NutritionValue, Integer>
        implements NutritionValueRepository {

    public NutritionValueRepositoryImpl() {
        super(NutritionValue.class);
    }

}

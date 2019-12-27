/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.repository;

import datlp.entity.Ingredient;

/**
 *
 * @author DATLPSE62823
 */
public class IngredientRepositoryImpl
        extends BaseRepositoryImpl<Ingredient, Integer>
        implements IngredientRepository {

    public IngredientRepositoryImpl() {
        super(Ingredient.class);
    }

}

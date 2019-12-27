/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.mapper;

import datlp.entity.Ingredient;
import datlp.jaxb.dish.IngredientType;

/**
 *
 * @author DATLPSE62823
 */
public interface IngredientMapper {
    
    Ingredient jaxbToIngredient(IngredientType jaxbObj, String dishName);
}

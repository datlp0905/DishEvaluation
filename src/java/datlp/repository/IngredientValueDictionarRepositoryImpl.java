/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.repository;

import datlp.entity.IngredientValueDictionary;

/**
 *
 * @author DATLPSE62823
 */
public class IngredientValueDictionarRepositoryImpl
        extends BaseRepositoryImpl<IngredientValueDictionary, Integer>
        implements IngredientValueDictionarRepository {

    public IngredientValueDictionarRepositoryImpl() {
        super(IngredientValueDictionary.class);
    }

}

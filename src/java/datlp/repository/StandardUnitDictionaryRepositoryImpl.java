/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.repository;

import datlp.entity.StandardUnitDictionary;

/**
 *
 * @author DATLPSE62823
 */
public class StandardUnitDictionaryRepositoryImpl 
        extends BaseRepositoryImpl<StandardUnitDictionary, Integer>
        implements StandardUnitDictionaryRepository{

    public StandardUnitDictionaryRepositoryImpl() {
        super(StandardUnitDictionary.class);
    }
    
}

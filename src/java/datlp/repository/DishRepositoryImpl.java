/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.repository;

import datlp.entity.Dish;

/**
 *
 * @author DATLPSE62823
 */
public class DishRepositoryImpl 
        extends BaseRepositoryImpl<Dish, Integer>
        implements DishRepository{

    public DishRepositoryImpl() {
        super(Dish.class);
    }
    
}

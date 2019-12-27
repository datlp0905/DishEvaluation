/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.mapper;

import datlp.entity.Dish;
import datlp.jaxb.dish.DishType;

/**
 *
 * @author DATLPSE62823
 */
public interface DishMapper {
    
    Dish jaxbToDish(DishType jaxbObj);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.mapper;

import datlp.entity.Dish;
import datlp.jaxb.dish.DishType;
import datlp.utilities.StringUtils;

/**
 *
 * @author DATLPSE62823
 */
public class DishMapperImpl implements DishMapper{

    @Override
    public Dish jaxbToDish(DishType jaxbObj) {
        if(jaxbObj == null) {
            return null;
        }//end if obj is null
        Dish dish = new Dish();
        dish.setName(jaxbObj.getName().trim());
        dish.setDescription(jaxbObj.getDescription().trim());
        dish.setLevel(jaxbObj.getLevel().trim());
        dish.setRecipeYield(jaxbObj.getRecipeYield().trim());
        dish.setCookTime(jaxbObj.getCookedTime().trim());
        dish.setImg(jaxbObj.getImg().trim());
        dish.setLink(jaxbObj.getLink().trim());
        dish.setHashName(StringUtils.hashingString(jaxbObj.getName().trim()));
        dish.setHashContent(StringUtils.hashingString(jaxbObj.toString()));
        
        return dish;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.mapper;

import datlp.entity.Ingredient;
import datlp.jaxb.dish.IngredientType;
import datlp.utilities.StringUtils;

/**
 *
 * @author DATLPSE62823
 */
public class IngredientMapperImpl implements IngredientMapper{

    @Override
    public Ingredient jaxbToIngredient(IngredientType jaxbObj, String dishName) {
        if(jaxbObj == null) {
            return null;
        }//end if object is null
        Ingredient ingredient = new Ingredient();
        ingredient.setName(jaxbObj.getName());
        String amount = jaxbObj.getAmount().trim();
        if(amount != null && !amount.isEmpty()) {
            //Separate amount and unit in jaxbObj
            //Ex: 100g --> amount = 100 and unit = g
            String number = "0", unit = "";
            for(int i = 0; i < amount.length(); i++) {
                char c = amount.charAt(i);
                if(Character.isDigit(c)) {
                    number += c;
                }//end if c is digit
                else {
                  unit = amount.substring(i).trim();
                  break;
                }                     
            }
            ingredient.setAmount(Double.parseDouble(number));
            ingredient.setUnit(unit);
        }//end if amount is not null and not empty
        ingredient.setHashName(StringUtils.hashingString(jaxbObj.getName() + dishName));
        ingredient.setHashContent(StringUtils.hashingString(jaxbObj.toString()));
        
        return ingredient;
    }
    
}

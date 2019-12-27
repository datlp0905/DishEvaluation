/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.mapper;

import datlp.entity.Nutrition;
import datlp.entity.NutritionValue;
import datlp.jaxb.nutrition.IngredientType;
import datlp.jaxb.nutrition.NutritionValuesType;
import datlp.utilities.StringUtils;

/**
 *
 * @author DATLPSE62823
 */
public class NutritionMapperImpl implements NutritionMapper{

    @Override
    public Nutrition jaxbToEntity(IngredientType jaxbObj) {
        if(jaxbObj == null) {
            return null;
        }
        Nutrition nutrition = new Nutrition();
        nutrition.setName(jaxbObj.getNutriName());
        nutrition.setEnergyAmount(Double.parseDouble(jaxbObj.getEnergyAmount().toString()));
        nutrition.setEnergyUnit(jaxbObj.getEnergyUnit());
        nutrition.setCalculatedPer(Double.parseDouble(jaxbObj.getCalculatedPer().toString()));
        nutrition.setHashName(StringUtils.hashingString(jaxbObj.getNutriName()));
        nutrition.setHashContent(StringUtils.hashingString(jaxbObj.toString()));
        
        return nutrition;
    }

    @Override
    public NutritionValue jaxbToEntitt(NutritionValuesType jaxbObj, String nutriName) {
        if(jaxbObj == null) {
            return null;
        }
        NutritionValue value = new NutritionValue();
        value.setName(jaxbObj.getValueName());
        value.setAmount(Double.parseDouble(jaxbObj.getAmount().toString()));
        value.setUnit(jaxbObj.getUnit());
        value.setHashName(StringUtils.hashingString(jaxbObj.getValueName() + nutriName));
        value.setHashContent(StringUtils.hashingString(jaxbObj.toString()));
        
        return value;
    }

}

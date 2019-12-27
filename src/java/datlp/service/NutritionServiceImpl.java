/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.service;

import datlp.entity.Nutrition;
import datlp.entity.NutritionGroup;
import datlp.entity.NutritionValue;
import datlp.jaxb.nutrition.IngredientType;
import datlp.jaxb.nutrition.NutritionType;
import datlp.jaxb.nutrition.NutritionValuesType;
import datlp.mapper.NutritionMapper;
import datlp.mapper.NutritionMapperImpl;
import datlp.repository.NutritionRepository;
import datlp.repository.NutritionRepositoryImpl;
import datlp.repository.NutritionGroupRepository;
import datlp.repository.NutritionGroupRepositoryImpl;
import datlp.repository.NutritionValueRepository;
import datlp.repository.NutritionValueRepositoryImpl;
import datlp.utilities.StringUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DATLPSE62823
 */
public class NutritionServiceImpl implements NutritionService {

    private final NutritionGroupRepository groupRepository;
    private final NutritionRepository nutritionRepository;
    private final NutritionValueRepository valueRepository;
    private final NutritionMapper nutritionMapper;

    public NutritionServiceImpl() {
        nutritionRepository = new NutritionRepositoryImpl();
        groupRepository = new NutritionGroupRepositoryImpl();
        valueRepository = new NutritionValueRepositoryImpl();
        nutritionMapper = new NutritionMapperImpl();
    }

    @Override
    public void saveNutritionToDB(NutritionType nutritionType) {
        if (nutritionType == null) {
            return;
        }
        List<IngredientType> ingredientTypes = nutritionType.getNutrition();
        for (IngredientType ingredient : ingredientTypes) {
            //Insert group
            String nutriGroupName = ingredient.getNutriGroup().trim();
            NutritionGroup group = createGroup(nutriGroupName);

            //Insert nutrition
            Nutrition nutrition = nutritionMapper.jaxbToEntity(ingredient);
            nutrition = createNutrition(nutrition, group);

            //Insert value
            for (NutritionValuesType itemValue : ingredient.getNutritionValues().getNutritionValue()) {
                NutritionValue value = nutritionMapper.jaxbToEntitt(itemValue, nutrition.getName());
                createValue(value, nutrition);
            }
        }
    }

    private NutritionGroup createGroup(String nutriGroupName) {
        NutritionGroup group = null;
        try {
            if (nutriGroupName != null && !nutriGroupName.isEmpty()) {
                int hashName = StringUtils.hashingString(nutriGroupName);

                Map<String, Object> parameters = new HashMap<>();
                parameters.put("hashName", hashName);
                group = groupRepository.find("NutritionGroup.findByHashName", parameters);

                if (group == null) {
                    group = new NutritionGroup();
                    group.setName(nutriGroupName);
                    group.setHashName(hashName);
                    group.setCreatedAt(new Date());
                    group = groupRepository.create(group);
                }//end if group is null
            }//end if groupName not null and not empty
        } catch (Exception e) {
            e.printStackTrace();
        }

        return group;
    }

    private Nutrition createNutrition(Nutrition nutrition, NutritionGroup nutriGroup) {
        if (nutrition == null) {
            return null;
        }
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("hashName", nutrition.getHashName());
        Nutrition existed = nutritionRepository.find("Nutrition.findByHashName", parameters);
        if (existed == null) {
            nutrition.setGroupId(nutriGroup);
            nutrition.setCreatedAt(new Date());
            nutrition = nutritionRepository.create(nutrition);
        }//end if nutrition is not existed 
        else {
            if (existed.getHashContent() != nutrition.getHashContent()) {
                nutrition = updateNutrition(existed, nutrition);
            }//end if content is different
        }//end if nutrition is existed 
        return nutrition;
    }

    private NutritionValue createValue(NutritionValue value, Nutrition nutrition) {
        if (value == null) {
            return null;
        }
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("hashName", value.getHashName());
        NutritionValue existed = valueRepository.find("NutritionValue.findByHashName", parameters);

        if (existed == null) {
            value.setNutriId(nutrition);
            value.setCreatedAt(new Date());
            value = valueRepository.create(value);
        }//end if value not existed
        else {
            if (existed.getHashContent() != value.getHashContent()) {
                value = updateValue(existed, value);
            }//end if hashContent is different
        }//end if value existed

        return value;
    }

    private Nutrition updateNutrition(Nutrition oldNutrition, Nutrition newNutrition) {
        oldNutrition.setCalculatedPer(newNutrition.getCalculatedPer());
        oldNutrition.setEnergyAmount(newNutrition.getEnergyAmount());
        oldNutrition.setEnergyUnit(newNutrition.getEnergyUnit());
        oldNutrition.setHashContent(newNutrition.getHashContent());
        oldNutrition.setUpdatedAt(new Date());

        oldNutrition = nutritionRepository.update(oldNutrition);

        return oldNutrition;
    }

    private NutritionValue updateValue(NutritionValue oldValue, NutritionValue newValue) {
        oldValue.setAmount(newValue.getAmount());
        oldValue.setUnit(newValue.getUnit());
        oldValue.setHashContent(newValue.getHashContent());
        oldValue.setUpdatedAt(new Date());

        oldValue = valueRepository.update(oldValue);

        return oldValue;
    }

    @Override
    public List<Nutrition> getAll() {
        List<Nutrition> nutritions = nutritionRepository.findMany("Nutrition.findAll", null);
        
        return nutritions;
    }

    @Override
    public Nutrition findByHashName(int nutritionName) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("hashName", nutritionName);
        return nutritionRepository.find("Nutrition.findByHashName", parameters);
    }
}

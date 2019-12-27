/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.service;

import datlp.constants.Constants;
import datlp.entity.Ingredient;
import datlp.entity.IngredientValueDictionary;
import datlp.entity.Nutrition;
import datlp.entity.StandardUnitDictionary;
import datlp.repository.IngredientRepository;
import datlp.repository.IngredientRepositoryImpl;
import datlp.repository.IngredientValueDictionarRepository;
import datlp.repository.IngredientValueDictionarRepositoryImpl;
import datlp.repository.NutritionRepository;
import datlp.repository.NutritionRepositoryImpl;
import datlp.repository.StandardUnitDictionaryRepository;
import datlp.repository.StandardUnitDictionaryRepositoryImpl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DATLPSE62823
 */
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final NutritionRepository nutritionRepository;
    private final IngredientValueDictionarRepository dictionarRepository;
    private final StandardUnitDictionaryRepository unitDictionaryRepository;

    public IngredientServiceImpl() {
        ingredientRepository = new IngredientRepositoryImpl();
        nutritionRepository = new NutritionRepositoryImpl();
        dictionarRepository = new IngredientValueDictionarRepositoryImpl();
        unitDictionaryRepository = new StandardUnitDictionaryRepositoryImpl();
    }

    @Override
    public Ingredient matchingIngredientToNutrition(int ingredientHash, int nutritionHash) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("hashName", ingredientHash);

        Ingredient ingredient = ingredientRepository.find("Ingredient.findByHashName", parameter);
        if (ingredient != null) {
            parameter.put("hashName", nutritionHash);
            Nutrition nutrition = nutritionRepository.find("Nutrition.findByHashName", parameter);
            if (nutrition != null) {
                //Update nutrition hash
                ingredient.setNutritionHash(nutritionHash);
                ingredient.setNutritionRecommend(null);

                double energy = computeEnergy(ingredient);
                ingredient.setEnergy(energy);

                ingredient = ingredientRepository.update(ingredient);

                //Insert record in dictionary
                createDictionary(ingredient.getName(), nutritionHash);
                
                autoMatchingIngredientToNutrition(ingredient.getName(), nutritionHash);
            } else {
                return null;
            }
        }
        return ingredient;
    }
    
    @Override
    public void autoMatchingIngredientToNutrition(String ingredientName, int nutritionHash) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("name", ingredientName);
        
        List<Ingredient> ingredients = ingredientRepository
                .findMany("Ingredient.findByNameAndUnMappingNutrition", parameter);
        if(ingredients != null && !ingredients.isEmpty()) {
            for (Ingredient ingredient : ingredients) {
                //Update nutrition hash
                ingredient.setNutritionHash(nutritionHash);
                ingredient.setNutritionRecommend(null);

                double energy = computeEnergy(ingredient);
                ingredient.setEnergy(energy);

                ingredient = ingredientRepository.update(ingredient);
            }
        }
    }

    private IngredientValueDictionary createDictionary(String ingredientName, int nutritionHash) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ingredientName", ingredientName);
        parameters.put("hashValueName", nutritionHash);

        IngredientValueDictionary dictionary = dictionarRepository
                .find("IngredientValueDictionary.findByIngredentNameAndHashValueName", parameters);
        if (dictionary == null) {
            dictionary = new IngredientValueDictionary();
            dictionary.setIngredentName(ingredientName);
            dictionary.setHashValueName(nutritionHash);
            dictionary.setCreatedAt(new Date());
            try {
                dictionary = dictionarRepository.create(dictionary);
            } catch (Exception e) {
                System.out.println("ERROR: duplicate IngredientValueDictionary. Ingredient name: " + 
                        ingredientName + " nutritionHash: " + nutritionHash);
            }
            
        }

        return dictionary;
    }

    @Override
    public Ingredient normalizeUnit(int ingredientId, double amout, boolean isIndependentUnit) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId);
        if (ingredient != null) {
            //Update ingredient
            double standardAmount = ingredient.getAmount() * amout;
            ingredient.setStandardAmount(standardAmount);

            double energy = computeEnergy(ingredient);
            ingredient.setEnergy(energy);

            ingredient = ingredientRepository.update(ingredient);

            //Insert to StandardUnitDictionary
            String ingredientName = isIndependentUnit ? null : ingredient.getName();
            createUnitDictionary(ingredientName, ingredient.getUnit(), amout);
            
            autoNormalizeUnit(ingredientName, ingredient.getUnit(), amout);
        }

        return ingredient;
    }

    private StandardUnitDictionary createUnitDictionary(String ingredientName, String unit, double equalTo) {
        StandardUnitDictionary dictionary;
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ingredientName", ingredientName);
        parameters.put("unit", unit);

        dictionary = unitDictionaryRepository
                .find("StandardUnitDictionary.findByUnitAndIngredientName", parameters);
        if (dictionary == null) {
            dictionary = new StandardUnitDictionary();
            dictionary.setIngredientName(ingredientName);
            dictionary.setUnit(unit);
            dictionary.setEqualTo(equalTo);
            dictionary.setCreatedAt(new Date());

            dictionary = unitDictionaryRepository.create(dictionary);
        }

        return dictionary;
    }

    @Override
    public List<Ingredient> findByDishId(int dishId) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("dishId", dishId);

        List<Ingredient> ingredients = ingredientRepository.findMany("Ingredient.findByDishId", parameter);

        return ingredients;
    }

    @Override
    public double computeEnergy(int ingredientId) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("id", ingredientId);

        Ingredient ingredient = ingredientRepository.find("Ingredient.findById", parameter);
        if (ingredient != null) {
            return computeEnergy(ingredient);
        }//end if ingredient is null

        return 0;
    }

    @Override
    public double computeEnergy(Ingredient ingredient) {
        if (ingredient == null) {
            return 0;
        }//end if ingredient is null
        double energy = 0;
        if (ingredient.getNutritionHash() != null) {
            if (ingredient.getValuable()) {
                NutritionService ns = new NutritionServiceImpl();
                Nutrition nutrition = ns.findByHashName(ingredient.getNutritionHash());

                if (nutrition != null) {
                    if (ingredient.getUnit().equals(Constants.INGREDIENT_STANDARD_UNIT)) {
                        if (ingredient.getAmount() != null) {
                            energy = ingredient.getAmount() * nutrition.getEnergyAmount() / nutrition.getCalculatedPer();
                        }//end if amount is not null
                    }//end if unit is STANDARD_UNIT
                    else {
                        if (ingredient.getStandardAmount() != null) {
                            energy = ingredient.getStandardAmount() * nutrition.getEnergyAmount() / nutrition.getCalculatedPer();
                        }//end if standard amout is not null
                    }//end if unit is not STAND_UNIT
                }//end if nutrition is not null
            }//end if ingredient is valuable
        }//end if nutrition hash is null
        return energy;
    }

    @Override
    public void autoNormalizeUnit(String ingredientName, String unit, double amount) {
        Map<String, Object> parameters = new HashMap<>();
        List<Ingredient> ingredients;
        if (ingredientName != null) {
            parameters.put("name", ingredientName);
            parameters.put("unit", unit);

            ingredients = ingredientRepository
                    .findMany("Ingredient.findByNameAndUnitAndStandardAmountNull", parameters);

        } else {
            parameters.put("unit", unit);

            ingredients = ingredientRepository
                    .findMany("Ingredient.findByUnitAndStandardAmountNull", parameters);
        }
        if (ingredients != null && !ingredients.isEmpty()) {
            for (Ingredient ingredient : ingredients) {
                double standardAmount = ingredient.getAmount() * amount;
                ingredient.setStandardAmount(standardAmount);

                double energy = computeEnergy(ingredient);
                ingredient.setEnergy(energy);

                ingredient = ingredientRepository.update(ingredient);
            }
        }
    }

}

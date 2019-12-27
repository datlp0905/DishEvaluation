/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.service;

import datlp.constants.Constants;
import datlp.dto.UnmappingIngredientDTO;
import datlp.entity.Dish;
import datlp.entity.DishSubCategory;
import datlp.entity.Ingredient;
import datlp.entity.IngredientValueDictionary;
import datlp.entity.MainCategory;
import datlp.entity.Nutrition;
import datlp.entity.NutritionValue;
import datlp.entity.StandardUnitDictionary;
import datlp.entity.SubCategory;
import datlp.jaxb.dish.DishInfoType;
import datlp.jaxb.dish.DishType;
import datlp.jaxb.dish.IngredientType;
import datlp.jaxb.dish.MainCategoryType;
import datlp.jaxb.dish.SubCategoryType;
import datlp.mapper.DishMapper;
import datlp.mapper.DishMapperImpl;
import datlp.mapper.IngredientMapper;
import datlp.mapper.IngredientMapperImpl;
import datlp.repository.DishRepository;
import datlp.repository.DishRepositoryImpl;
import datlp.repository.DishSubCategoryRepository;
import datlp.repository.DishSubCategoryRepositoryImpl;
import datlp.repository.IngredientRepository;
import datlp.repository.IngredientRepositoryImpl;
import datlp.repository.IngredientValueDictionarRepository;
import datlp.repository.IngredientValueDictionarRepositoryImpl;
import datlp.repository.MainCategoryRepository;
import datlp.repository.MainCategoryRepositoryImpl;
import datlp.repository.NutritionRepository;
import datlp.repository.NutritionRepositoryImpl;
import datlp.repository.StandardUnitDictionaryRepository;
import datlp.repository.StandardUnitDictionaryRepositoryImpl;
import datlp.repository.SubCategoryRepository;
import datlp.repository.SubCategoryRepositoryImpl;
import datlp.utilities.MatchNutrition;
import datlp.utilities.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DATLPSE62823
 */
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final DishSubCategoryRepository dishSubCategoryRepository;
    private final IngredientRepository ingredientRepository;
    private final IngredientValueDictionarRepository dictionarRepository;
    private final NutritionRepository nutritionRepository;
    private final StandardUnitDictionaryRepository unitDictionaryRepository;

    private final DishMapper dishMapper;
    private final IngredientMapper ingredientMapper;

    private final List<Nutrition> nutritions;

    public DishServiceImpl() {
        dishRepository = new DishRepositoryImpl();
        mainCategoryRepository = new MainCategoryRepositoryImpl();
        subCategoryRepository = new SubCategoryRepositoryImpl();
        dishSubCategoryRepository = new DishSubCategoryRepositoryImpl();
        ingredientRepository = new IngredientRepositoryImpl();
        dictionarRepository = new IngredientValueDictionarRepositoryImpl();
        nutritionRepository = new NutritionRepositoryImpl();
        unitDictionaryRepository = new StandardUnitDictionaryRepositoryImpl();

        dishMapper = new DishMapperImpl();
        ingredientMapper = new IngredientMapperImpl();

        nutritions = nutritionRepository.findMany("Nutrition.findAll", null);
    }

    @Override
    public void saveDishToDB(DishInfoType infoType) {
        if (infoType == null) {
            return;
        }//end if info is null
        for (MainCategoryType mainCate : infoType.getMainCategory()) {
            MainCategory mainCategory = createMainCategory(mainCate);

            for (SubCategoryType subCate : mainCate.getSubCategory()) {
                SubCategory subCategory = createSubCategory(subCate, mainCategory);

                for (DishType dishType : subCate.getDish()) {
                    Dish dish = createDish(dishType);
                    createDishSubCategory(dish, subCategory);

                    for (IngredientType ingredientType : dishType.getIngredient()) {
                        createIngredient(ingredientType, dish);
                    }
                }
            }
        }
    }

    private MainCategory createMainCategory(MainCategoryType categoryType) {
        if (categoryType == null) {
            return null;
        }//end if category type is null
        int hashName = StringUtils.hashingString(categoryType.getName().trim());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("hashName", hashName);

        MainCategory mainCategory = mainCategoryRepository.find("MainCategory.findByHashName", parameters);
        if (mainCategory == null) {
            mainCategory = new MainCategory();
            mainCategory.setName(categoryType.getName().trim());
            mainCategory.setHashName(hashName);
            mainCategory.setCreatedAt(new Date());

            mainCategory = mainCategoryRepository.create(mainCategory);
        }//end if mainCateory is null

        return mainCategory;
    }

    private SubCategory createSubCategory(SubCategoryType categoryType, MainCategory mainCategory) {
        if (categoryType == null) {
            return null;
        }//end if category type is null
        if (mainCategory == null) {
            return null;
        }//end if main category is null
        int hashName = StringUtils.hashingString(categoryType.getName().trim());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("hashName", hashName);

        SubCategory subCategory = subCategoryRepository.find("SubCategory.findByHashName", parameters);
        if (subCategory == null) {
            subCategory = new SubCategory();
            subCategory.setName(categoryType.getName().trim());
            subCategory.setHashName(hashName);
            subCategory.setMainCateId(mainCategory);
            subCategory.setCreatedAt(new Date());

            subCategory = subCategoryRepository.create(subCategory);
        }//end if subCategory is null

        return subCategory;
    }

    private Dish createDish(DishType dishType) {
        if (dishType == null) {
            return null;
        }//end if dishType is null
        int hashName = StringUtils.hashingString(dishType.getName().trim());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("hashName", hashName);

        Dish dish = dishRepository.find("Dish.findByHashName", parameters);
        if (dish == null) {
            dish = dishMapper.jaxbToDish(dishType);
            dish.setCreatedAt(new Date());
            dish = dishRepository.create(dish);
        }//end if dish is null
        else {
            int hashContent = StringUtils.hashingString(dishType.toString());
            if (dish.getHashContent() != hashContent) {
                dish = updateDish(dish, dishType);
            }//end if hashContent is different
        }//end if dish is not null
        return dish;
    }

    private Dish updateDish(Dish oldDish, DishType newDish) {
        oldDish.setCookTime(newDish.getCookedTime().trim());
        oldDish.setDescription(newDish.getDescription().trim());
        oldDish.setHashContent(StringUtils.hashingString(newDish.toString().trim()));
        oldDish.setImg(newDish.getImg().trim());
        oldDish.setLevel(newDish.getLevel().trim());
        oldDish.setLink(newDish.getLink().trim());
        oldDish.setRecipeYield(newDish.getRecipeYield().trim());
        oldDish.setUpdatedAt(new Date());

        oldDish = dishRepository.update(oldDish);
        return oldDish;
    }

    private DishSubCategory createDishSubCategory(Dish dish, SubCategory subCategory) {
        if (dish == null) {
            return null;
        }//end is dish is null
        if (subCategory == null) {
            return null;
        }//end if subCategory is null
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("dishId", dish.getId());
        parameter.put("subCateId", subCategory.getId());

        DishSubCategory dishSubCategory = dishSubCategoryRepository
                .find("DishSubCategory.findByDishIdAndSubCateId", parameter);
        if (dishSubCategory == null) {
            dishSubCategory = new DishSubCategory();
            dishSubCategory.setDishId(dish);
            dishSubCategory.setSubCateId(subCategory);

            dishSubCategory = dishSubCategoryRepository.create(dishSubCategory);
        }//end if dishSubCate is null

        return dishSubCategory;
    }

    private Ingredient createIngredient(IngredientType ingredientType, Dish dish) {
        if (ingredientType == null) {
            return null;
        }//end if ingredient is null
        if (dish == null) {
            return null;
        }//end if dish is null

        Ingredient ingredient = ingredientMapper.jaxbToIngredient(ingredientType, dish.getName());
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("hashName", ingredient.getHashName());

        Ingredient existed = ingredientRepository.find("Ingredient.findByHashName", parameter);
        if (existed == null) {
            checkIngredientUnit(ingredient);
            if (ingredient.getAmount() != null && ingredient.getAmount() > 0) {
                ingredient.setValuable(Boolean.TRUE);
                setIngredientHashNutrition(ingredient);
            } else {
                ingredient.setValuable(Boolean.FALSE);
            }
            ingredient.setDishId(dish);
            
            ingredient = ingredientRepository.create(ingredient);
        }//end if existed is null
        else {
            ingredient = existed;
        }//end if existed is not null

        return ingredient;
    }

    /*
     Return hashValue if found
     Return -1 if not found
     */
    private int matchDictionary(String ingreName) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("ingredentName", "%" + ingreName + "%");

        List<IngredientValueDictionary> dictionarys = dictionarRepository
                .findMany("IngredientValueDictionary.findByIngredentNameLike", parameter);
        if (dictionarys != null && !dictionarys.isEmpty()) {
            for (IngredientValueDictionary item : dictionarys) {
                float matchPercent = StringUtils.computeMatchingPercent(ingreName, item.getIngredentName());
                if (matchPercent >= Constants.NUTRITION_BEST_MATCHING_PERCENT) {
                    return item.getHashValueName();
                }
            }
        }//end if dictionary is not null and not empty

        return -1;
    }

    /*
     Return the matchest hashValue if found
     Return null if not found
     */
    private MatchNutrition getMatchingValue(String ingredientName) {
        if (ingredientName != null && !ingredientName.isEmpty()) {
            float maxMatchPercent = -1;
            Nutrition matchNutrition = null;
            for (Nutrition nutrition : nutritions) {
                float matchPercent = StringUtils.computeMatchingPercent(ingredientName, nutrition.getName());

                if (matchPercent >= Constants.NUTRITION_RECOMMEND_MATCHING_PERCENT) {
                    if (matchPercent > maxMatchPercent) {
                        maxMatchPercent = matchPercent;
                        matchNutrition = nutrition;
                    }
                }//end if matchPercent is greater or equal than NUTRITION_BEST_MATCHING_PERCENT
            }
            if (matchNutrition != null) {
                System.out.println(ingredientName + "-"
                        + matchNutrition.getName() + " Match: " + maxMatchPercent + "%");
                return new MatchNutrition(maxMatchPercent, matchNutrition);
            }
        }//end if ingredient name is not null and not empty

        return null;
    }

    private IngredientValueDictionary createIngredientDictionary(String ingredientName, int valueHashName) {
        IngredientValueDictionary dictionary = new IngredientValueDictionary();
        dictionary.setIngredentName(ingredientName);
        dictionary.setHashValueName(valueHashName);
        dictionary.setCreatedAt(new Date());

        try {
            dictionary = dictionarRepository.create(dictionary);
        } catch (Exception e) {
            System.out.println("ERROR: Duplicate ingredient dictionary(ingredientName:" +
                    ingredientName + ", valueHashName:" + valueHashName + ")");
        }
        return dictionary;
    }

    private void checkIngredientUnit(Ingredient ingredient) {
        if (ingredient == null) {
            return;
        }
        if (ingredient.getUnit() != null
                && !ingredient.getUnit().equalsIgnoreCase(Constants.INGREDIENT_STANDARD_UNIT)) {
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("unit", ingredient.getUnit());

            List<StandardUnitDictionary> unitDictionarys = unitDictionaryRepository
                    .findMany("StandardUnitDictionary.findByUnit", parameter);

            if (unitDictionarys != null && !unitDictionarys.isEmpty()) {
                for (StandardUnitDictionary unitDictionary : unitDictionarys) {
                    if (unitDictionary.getIngredientName() == null) {
                        double standardAmount = ingredient.getAmount() * unitDictionary.getEqualTo();
                        ingredient.setStandardAmount(standardAmount);
                        break;
                    } else if (unitDictionary.getIngredientName().equalsIgnoreCase(ingredient.getName())) {
                        double standardAmount = ingredient.getAmount() * unitDictionary.getEqualTo();
                        ingredient.setStandardAmount(standardAmount);
                        break;
                    }//end if unitDictionaryName match ingredientName
                }
            }//end if unitDictionary is not null and not empty
        }
    }

    private void setIngredientHashNutrition(Ingredient ingredient) {
        if (ingredient == null) {
            return;
        }
        IngredientService ingredientService = new IngredientServiceImpl();
        int hashNutrition = matchDictionary(ingredient.getName());

        if (hashNutrition != -1) {
            ingredient.setNutritionHash(hashNutrition);
            double energy = ingredientService.computeEnergy(ingredient);
            ingredient.setEnergy(energy);
        }//end if hashNutrition is found in dictionary
        else {
            MatchNutrition matchNutrition = getMatchingValue(ingredient.getName());
            if (matchNutrition != null) {
                if (matchNutrition.getMatchingPercent() >= Constants.NUTRITION_BEST_MATCHING_PERCENT) {
                    ingredient.setNutritionHash(matchNutrition.getNutrition().getHashName());
                    ingredient.setEnergy(ingredientService.computeEnergy(ingredient));
                    createIngredientDictionary(ingredient.getName(), matchNutrition.getNutrition().getHashName());
                } else {
                    ingredient.setNutritionRecommend(matchNutrition.getNutrition().getHashName());
                }
            }
        }//end if hashNutrition is not found in dictionary
    }

    @Override
    public List<Ingredient> findByUnnormalizedUnit() {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("unit", Constants.INGREDIENT_STANDARD_UNIT);

        List<Ingredient> ingredients = ingredientRepository
                .findMany("Ingredient.findByUnnormalizedUnit", parameter);

        return ingredients;
    }

    @Override
    public List<UnmappingIngredientDTO> findByUnMappingNutrition() {
        List<Ingredient> ingredients;
        List<UnmappingIngredientDTO> dtos = new ArrayList<>();
        ingredients = ingredientRepository.findMany("Ingredient.findByUnMappingNutrition", null);
        if (ingredients.size() > 0) {
            dtos = new ArrayList<>();
            for (Ingredient ingredient : ingredients) {
                int nutritionHashRecommended = ingredient.getNutritionRecommend();
                Map<String, Object> parameter = new HashMap<>();
                parameter.put("hashName", nutritionHashRecommended);

                Nutrition nutrition = nutritionRepository.find("Nutrition.findByHashName", parameter);
                if (nutrition != null) {
                    UnmappingIngredientDTO dto = new UnmappingIngredientDTO(ingredient, nutrition);
                    dtos.add(dto);
                }
            }
        }
        return dtos;
    }

    @Override
    public List<Dish> findByCategoryIds(List<Integer> categoryIds) {
        if (categoryIds != null && !categoryIds.isEmpty()) {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("listSubCateIds", categoryIds);

            List<DishSubCategory> categorys = dishSubCategoryRepository
                    .findMany("DishSubCategory.findBySubCateIdIn", parameters);
            List<Dish> dishes = new ArrayList<>();
            for (DishSubCategory subCate : categorys) {
                dishes.add(subCate.getDishId());
            }
            return dishes;
        }
        return null;
    }

    @Override
    public List<NutritionValue> findNutritionValueByDishId(int dishId) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("id", dishId);

        Dish dish = dishRepository.find("Dish.findById", parameter);
        if (dish != null) {
            List<Ingredient> ingredients = dish.getIngredientList();
            List<NutritionValue> results = new ArrayList<>();
            List<String> valueNames = new ArrayList<>();
            
            for (Ingredient ingredient : ingredients) {
                Integer nutriHash = ingredient.getNutritionHash();

                if (nutriHash != null) {
                    NutritionService nutritionService = new NutritionServiceImpl();
                    Nutrition nutrition = nutritionService.findByHashName(nutriHash);
                    
                    Double amount = null;
                    
                    if(ingredient.getUnit().equals(Constants.INGREDIENT_STANDARD_UNIT)) {
                        if(ingredient.getAmount() != null) {
                            amount = ingredient.getAmount();
                        }
                    } else if(ingredient.getStandardAmount() != null) {
                        amount = ingredient.getStandardAmount();
                    }
                    
                    if (nutrition != null && amount != null) {
                        List<NutritionValue> values = nutrition.getNutritionValueList();
                        for (int i = 0; i < values.size(); i++) {
                            NutritionValue value = values.get(i);
                            
                            if (value.getAmount() > 0) {
                                double amountAfterCalculated = value.getAmount() * amount / nutrition.getCalculatedPer();
                                value.setAmount(amountAfterCalculated);
                                
                                if (!valueNames.contains(value.getName())) {
                                    results.add(value);
                                    valueNames.add(value.getName());
                                } else {
                                    int index = valueNames.indexOf(value.getName());
                                    double sumAmount = results.get(index).getAmount() + value.getAmount();
                                    results.get(index).setAmount(sumAmount);
                                }
                            }//end if amount < 0
                        }
                    }//end if nutrition is not null and amount is not null
                }//end if nutriHash is not null
            }
            return results;
        }//end if dish is not null
        return new ArrayList<>();
    }

    @Override
    public List<Dish> findByEnergyNeed(Double energyNeed, Integer numberOfDishes) {
        double from = energyNeed / numberOfDishes - Constants.AVERAGE_ENERGY_DISTANCE;
        double to = energyNeed / numberOfDishes + Constants.AVERAGE_ENERGY_DISTANCE;
        
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("from", from);
        parameters.put("to", to);
        
        List<Dish> dishes = dishRepository.findMany("Dish.finByEnergyFromTo", parameters);
        return dishes;
    }
}

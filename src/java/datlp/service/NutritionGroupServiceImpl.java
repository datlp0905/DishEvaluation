/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.service;

import datlp.entity.NutritionGroup;
import datlp.repository.NutritionGroupRepository;
import datlp.repository.NutritionGroupRepositoryImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DATLPSE62823
 */
public class NutritionGroupServiceImpl implements NutritionGroupService{

    private final NutritionGroupRepository repository;

    public NutritionGroupServiceImpl() {
        repository = new NutritionGroupRepositoryImpl();
    }
    
    @Override
    public List<NutritionGroup> findAll() {
        return repository.findMany("NutritionGroup.findAll", null);
    }

    @Override
    public NutritionGroup findById(int id) {
        return repository.findById(id);
    }

    @Override
    public NutritionGroup findByHashName(int hashName) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("hashName", hashName);
        return repository.find("NutritionGroup.findByHashName", parameters);
    }

    @Override
    public NutritionGroup create(NutritionGroup nutritionGroup) {
        return repository.create(nutritionGroup);
    }

    @Override
    public NutritionGroup update(NutritionGroup nutritionGroup) {
        return repository.update(nutritionGroup);
    }
    
}

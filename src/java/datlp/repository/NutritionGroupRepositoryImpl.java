/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.repository;

import datlp.entity.NutritionGroup;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DATLPSE62823
 */
public class NutritionGroupRepositoryImpl extends BaseRepositoryImpl<NutritionGroup, Integer>
        implements NutritionGroupRepository {

    public NutritionGroupRepositoryImpl() {
        super(NutritionGroup.class);
    }

    @Override
    public boolean existed(int hashName) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("hashName", hashName);
        NutritionGroup group = find("NutritionGroup.findByHashName", parameters);

        return group != null;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.repository;

import datlp.entity.NutritionGroup;
import java.io.Serializable;

/**
 *
 * @author DATLPSE62823
 */
public interface NutritionGroupRepository extends BaseRepository<NutritionGroup, Integer>{
    boolean existed(int hashName);
}

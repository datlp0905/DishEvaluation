/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.repository;

import datlp.entity.DishSubCategory;

/**
 *
 * @author DATLPSE62823
 */
public class DishSubCategoryRepositoryImpl
        extends BaseRepositoryImpl<DishSubCategory, Integer>
        implements DishSubCategoryRepository {

    public DishSubCategoryRepositoryImpl() {
        super(DishSubCategory.class);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.repository;

import datlp.entity.MainCategory;

/**
 *
 * @author DATLPSE62823
 */
public class MainCategoryRepositoryImpl
        extends BaseRepositoryImpl<MainCategory, Integer>
        implements MainCategoryRepository {

    public MainCategoryRepositoryImpl() {
        super(MainCategory.class);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.repository;

import datlp.entity.SubCategory;

/**
 *
 * @author DATLPSE62823
 */
public class SubCategoryRepositoryImpl
        extends BaseRepositoryImpl<SubCategory, Integer>
        implements SubCategoryRepository {

    public SubCategoryRepositoryImpl() {
        super(SubCategory.class);
    }

}

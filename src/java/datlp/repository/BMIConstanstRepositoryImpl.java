/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.repository;

import datlp.entity.BMIConstanst;

/**
 *
 * @author DATLPSE62823
 */
public class BMIConstanstRepositoryImpl
        extends BaseRepositoryImpl<BMIConstanst, Integer>
        implements BMIConstanstRepository {

    public BMIConstanstRepositoryImpl() {
        super(BMIConstanst.class);
    }

}

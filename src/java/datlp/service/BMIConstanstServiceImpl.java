/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.service;

import datlp.entity.BMIConstanst;
import datlp.repository.BMIConstanstRepository;
import datlp.repository.BMIConstanstRepositoryImpl;

/**
 *
 * @author DATLPSE62823
 */
public class BMIConstanstServiceImpl implements BMIConstanstService{

    private final BMIConstanstRepository bmiRepository;    

    public BMIConstanstServiceImpl() {
        this.bmiRepository = new BMIConstanstRepositoryImpl();
    }
    
    @Override
    public BMIConstanst findLastest() {
        return bmiRepository.find("BMIConstanst.findLastest", null);
    }
    
}

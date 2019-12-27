/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.service;

import datlp.entity.MainCategory;
import java.util.List;

/**
 *
 * @author DATLPSE62823
 */
public interface CategoryService {
 
    List<MainCategory> findAll();
}

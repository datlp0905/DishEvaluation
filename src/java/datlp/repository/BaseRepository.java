/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DATLPSE62823
 * @param <T>
 * @param <PK>
 */
public interface BaseRepository<T, PK extends Serializable> {
    
    T findById(PK primaryKey);
    
    T find(String query, Map<String, Object> parameters);
    
    List<T> findMany(String query, Map<String, Object> parameters);
    
    T create(T entity);
    
    T update(T entity);
    
    void delete(T entity);
}

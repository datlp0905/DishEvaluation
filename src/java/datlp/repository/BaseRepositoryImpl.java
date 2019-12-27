/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.repository;

import datlp.utilities.JPAUtils;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author DATLPSE62823
 * @param <T>
 * @param <PK>
 */
public class BaseRepositoryImpl<T, PK extends Serializable>
        implements BaseRepository<T, PK> {

    protected final Class<T> type;
    protected EntityManager em;
    protected Map<String, String> nameQueryMap;

    public BaseRepositoryImpl(Class<T> type) {
        this.type = type;
        nameQueryMap = new HashMap<>();
    }

    @Override
    public T findById(Serializable primaryKey) {
        em = JPAUtils.getEntityManager();
        try {
            return em.find(type, primaryKey);
        } finally {
            closeConnection();
        }

    }

    @Override
    public T find(String query, Map<String, Object> parameters) {
        if (query == null) {
            return null;
        }
        T result = null;
        em = JPAUtils.getEntityManager();
        try {
            Query sql = em.createNamedQuery(query);
            if (parameters != null && !parameters.isEmpty()) {
                for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                    sql.setParameter(entry.getKey(), entry.getValue());
                }
            }
            result = (T) sql.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return result;
    }

    @Override
    public T create(T entity) {
        if (entity == null) {
            return null;
        }
        em = JPAUtils.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            entity = em.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("ERROR in BaseRepo.create " + e.getMessage());
        } finally {
            closeConnection();
        }
        return entity;
    }

    @Override
    public T update(T entity) {
        if (entity == null) {
            return null;
        }
        em = JPAUtils.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
//            if (em.contains(entity)) {
            entity = em.merge(entity);
//            }//end if em contain this entity
            transaction.commit();
        } finally {
            closeConnection();
        }
        return entity;
    }

    @Override
    public void delete(T entity) {
        if (entity != null) {
            em = JPAUtils.getEntityManager();
            EntityTransaction transaction = em.getTransaction();
            try {
                if (em.contains(entity)) {
                    transaction.begin();
                    em.remove(entity);
                    transaction.commit();
                }//end if em contain this entity
            } finally {
                closeConnection();
            }
        }
    }

    protected void closeConnection() {
        if (em != null) {
            em.close();
        }
    }

    @Override
    public List<T> findMany(String query, Map<String, Object> parameters) {
        if (query == null) {
            return null;
        }
        em = JPAUtils.getEntityManager();
        try {
            Query sql = em.createNamedQuery(query);
            if (parameters != null && !parameters.isEmpty()) {
                for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                    sql.setParameter(entry.getKey(), entry.getValue());
                }
            }
//            sql.setFirstResult(0);
//            sql.setMaxResults(100);
            return sql.getResultList();
        } finally {
            closeConnection();
        }
    }

}

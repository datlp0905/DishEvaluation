/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.utilities;

import datlp.constants.Constants;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author DATLPSE62823
 */
public class JPAUtils {
    
    private static EntityManagerFactory emf;
    
    public static EntityManager getEntityManager() {
        if(emf == null) {
            emf = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT);
        }
        return emf.createEntityManager();
    }
}

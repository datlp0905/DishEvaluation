/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.webservice;

import datlp.constants.Constants;
import datlp.entity.MainCategory;
import datlp.service.CategoryService;
import datlp.service.CategoryServiceImpl;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author DATLPSE62823
 */
@Path("maincategory")
public class MainCategoryFacadeREST extends AbstractFacade<MainCategory> {
    
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT);
    private final EntityManager em;
    
    private final CategoryService categoryService;

    public MainCategoryFacadeREST() {
        super(MainCategory.class);
        em = emf.createEntityManager();
        
        categoryService = new CategoryServiceImpl();
    }
    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<MainCategory> findAll() {
        return categoryService.findAll();
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}

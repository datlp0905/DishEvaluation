/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.webservice;

import datlp.constants.Constants;
import datlp.entity.Dish;
import datlp.entity.NutritionValue;
import datlp.service.DishService;
import datlp.service.DishServiceImpl;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author DATLPSE62823
 */
@Path("dish")
public class DishFacadeREST extends AbstractFacade<Dish> {
    
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT);
    private final EntityManager em;
    
    private final DishService dishService;
    
    public DishFacadeREST() {
        super(Dish.class);
        this.em = emf.createEntityManager();
        
        dishService = new DishServiceImpl();
    }

    @POST
    @Override
    @Consumes({"application/xml", MediaType.APPLICATION_XML})
    public void create(Dish entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Dish entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Dish find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    @Override
    public List<Dish> findAll() {
        return super.findAll();
    }
    
    @GET
    @Path("category")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_XML)
    public List<Dish> findByCategories(@QueryParam("categoryIds") List<Integer> categoryIds) {
        return dishService.findByCategoryIds(categoryIds);
    }
    
    @GET
    @Path("{id}/nutrition-value")
    @Produces(MediaType.APPLICATION_XML)
    public List<NutritionValue> findNutritionValueByDishId(@PathParam("id") Integer dishId) {
        return dishService.findNutritionValueByDishId(dishId);
    }
    
    @GET
    @Path("suggest-menu")
    @Produces(MediaType.APPLICATION_XML)
    public List<Dish> findMenuByEnergyNeed(
            @QueryParam("energyNeed") Double energyNeed,
            @QueryParam("numberOfDish") Integer numberOfDishes) {
        return dishService.findByEnergyNeed(energyNeed, numberOfDishes);
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

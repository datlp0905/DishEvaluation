/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.webservice;

import datlp.constants.Constants;
import datlp.dto.UnmappingIngredientDTO;
import datlp.entity.Ingredient;
import datlp.service.DishService;
import datlp.service.DishServiceImpl;
import datlp.service.IngredientService;
import datlp.service.IngredientServiceImpl;
import java.util.Comparator;
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
@Path("ingredient")
public class IngredientFacadeREST extends AbstractFacade<Ingredient> {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT);
    private final EntityManager em;

    private final IngredientService ingredientService;

    public IngredientFacadeREST() {
        super(Ingredient.class);
        em = emf.createEntityManager();

        ingredientService = new IngredientServiceImpl();
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Ingredient entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Ingredient entity) {
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
    public Ingredient find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Ingredient> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Ingredient> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @GET
    @Path("dishId/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public List<Ingredient> findByDishId(@PathParam("id") Integer dishId) {
        return ingredientService.findByDishId(dishId);
    }

    @GET
    @Path("matching-ingredient")
    @Produces(MediaType.APPLICATION_XML)
    public List<UnmappingIngredientDTO> matchingIngredient(
            @QueryParam("txtIngredientHashName") Integer ingredientHash,
            @QueryParam("txtNutritionHashName") Integer nutritionHash,
            @QueryParam("mappingNutrition") Integer mappingNutrition) {
        if (mappingNutrition != -1) {
            ingredientService.matchingIngredientToNutrition(ingredientHash, mappingNutrition);
        } else {
            ingredientService.matchingIngredientToNutrition(ingredientHash, nutritionHash);
        }

        DishService dishService = new DishServiceImpl();
        List<UnmappingIngredientDTO> unMappingNutritions = dishService.findByUnMappingNutrition();
        
        unMappingNutritions.sort(new Comparator<UnmappingIngredientDTO>() {

            @Override
            public int compare(UnmappingIngredientDTO o1, UnmappingIngredientDTO o2) {
                return o1.getIngredient().getName().compareTo(o2.getIngredient().getName());
            }
        });

        return unMappingNutritions;
    }

    @GET
    @Path("normalize-unit")
    @Produces(MediaType.APPLICATION_XML)
    public List<Ingredient> normalizeUnit(
            @QueryParam("txtIngredientId") Integer ingredientId,
            @QueryParam("txtEqualTo") Double equalTo,
            @QueryParam("cbIndependent") Boolean isIndependentUnit) {
        ingredientService.normalizeUnit(ingredientId, equalTo, isIndependentUnit);

        DishService dishService = new DishServiceImpl();
        List<Ingredient> unNomarlizeUnits = dishService.findByUnnormalizedUnit();

        unNomarlizeUnits.sort(new Comparator<Ingredient>() {

            @Override
            public int compare(Ingredient o1, Ingredient o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        return unNomarlizeUnits;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}

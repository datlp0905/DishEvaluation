/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.webservice;

import datlp.constants.Constants;
import datlp.entity.BMIConstanst;
import datlp.service.BMIConstanstService;
import datlp.service.BMIConstanstServiceImpl;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author DATLPSE62823
 */
@Path("bmi-constanst")
public class BMIConstanstFacadeREST extends AbstractFacade<BMIConstanst> {
    
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT);
    private final EntityManager em;
    private final BMIConstanstService bmiService;

    public BMIConstanstFacadeREST() {
        super(BMIConstanst.class);
        
        em = emf.createEntityManager();
        bmiService = new BMIConstanstServiceImpl();
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(BMIConstanst entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, BMIConstanst entity) {
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
    public BMIConstanst find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<BMIConstanst> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<BMIConstanst> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("lastest")
    @Produces(MediaType.APPLICATION_XML)
    public BMIConstanst findLatest() {
        return bmiService.findLastest();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}

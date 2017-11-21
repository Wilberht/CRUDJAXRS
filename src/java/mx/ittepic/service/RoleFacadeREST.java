/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ittepic.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
import mx.ittepic.entities.Role;

/**
 *
 * @author rh1n0
 */
@Stateless
@Path("mx.ittepic.entities.role")
public class RoleFacadeREST extends AbstractFacade<Role> {

    @PersistenceContext(unitName = "RestFullWebServicePU")
    private EntityManager em;

    public RoleFacadeREST() {
        super(Role.class);
    }

    @POST
    @Override
    @Consumes({"application/json"})
    public void create(Role s) {
        super.create(s);
    }

    @PUT
    @Path("/actualizar/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, JsonObject e) {
        
        super.edit(new Role());
    }

    @DELETE
    @Path("/borrar/{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("/id/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String findOne(@PathParam("id") Integer id){
        return super.findOne(id);
    }
    
    
    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public String findAll() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Role.class));
        return gson.toJson(getEntityManager().createQuery(cq).getResultList());
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Role> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}

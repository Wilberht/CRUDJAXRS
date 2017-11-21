/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ittepic.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import mx.ittepic.entities.Role;

/**
 *
 * @author rh1n0
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(JsonObject s) {
        Role r = new Role();
        r.setRolename(s.getString("rolename"));
        r.setSalary(s.getInt("salary"));
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = new Date();
        try{
        fecha = dateFormat.parse(s.getString("createdat"));
        }catch(ParseException e){
            System.out.println(e.getMessage());
        }
        r.setCreatedat(fecha);
        getEntityManager().persist(r);
    }

    public void edit(Object i, JsonObject s) {
        Role r = (Role) find(i);
        r.setRolename(s.getString("rolename"));
        r.setSalary(s.getInt("salary"));
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = new Date();
        try{
        fecha = dateFormat.parse(s.getString("createdat"));
        }catch(ParseException e){
            System.out.println(e.getMessage());
        }
        r.setCreatedat(fecha);
        getEntityManager().merge(r);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }
    
    public String findOne(Object id){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(getEntityManager().find(entityClass, id));
    }

    public String findAll() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Query q;
        q = getEntityManager().createNamedQuery(entityClass.getSimpleName() + ".findAll");
        return gson.toJson(q.getResultList());
    }

   
    
}

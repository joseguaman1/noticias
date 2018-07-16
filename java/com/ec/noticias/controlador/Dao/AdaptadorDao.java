/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.noticias.controlador.Dao;

import com.ec.noticias.controlador.Conexion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

/**
 *
 * @author sissysebas
 */

public class AdaptadorDao<T> implements InterfazDao<T> {
    
    private Conexion conexion;
    
    private Class clazz;  

    public AdaptadorDao (Conexion conexion, Class clazz) {
        this.conexion = conexion;
        this.clazz = clazz;
    }
    
    @Override
    public void guardar(T obj)  throws Exception {
        EntityManager manager =  this.conexion.getEMF();//.createEntityManager();    
        try {    
            manager.getTransaction().begin();
            manager.persist(obj);    
            manager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en guardar datos *** "+e);
            manager.getTransaction().rollback();    
            manager.close();
        } finally {
            manager.close();
            
        }
    }

    @Override
    public List<T> listar() {
        List<T> lista = new ArrayList<>();
        EntityManager manager =  this.conexion.getEMF();//.createEntityManager();
        try {
            Query query = manager.createQuery("SELECT p FROM "+clazz.getSimpleName()+" p");
            lista = query.getResultList();
        } catch (Exception e) {
            System.out.println("Error en listar datos");
            //manager.close();
        } finally {
           // manager.close();
        }
        return lista;
    }

    @Override
    public void modificar(T obj) throws Exception {
        EntityManager manager =  this.conexion.getEMF();//.createEntityManager();
        try {
            manager.getTransaction().begin();
            manager.merge(obj);
            manager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en editar datos");
            manager.getTransaction().rollback();
            manager.close();
        } finally {
            manager.close();
        }
    }

    @Override
    public T obtener(Long id) {
        EntityManager manager =  this.conexion.getEMF();//.createEntityManager();
        T obj = null;
        try {
            obj = (T)manager.find(clazz, id);
            
        } catch (Exception e) {
            System.out.println("Error en editar datos");
            
        } 
        return obj;
    }

    public Conexion getConexion() {
        return conexion;
    }
    
    
    
}

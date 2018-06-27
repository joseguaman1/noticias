/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.noticias.controlador;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
/**
 *
 * @author sissysebas
 */
@ManagedBean(name="jpaConexionBean")
@ApplicationScoped
@Stateless
public class Conexion {
    /** Persistance unit name - persistence.xml */
    public final static String PERSISTANCE_UNIT_NAME = "com.ec.noticias_noticias_war_1.0-SNAPSHOTPU";
    /** Referencia na EntityManagerFactory  */
    //protected EntityManagerFactory emf;
    
//    @PersistenceContext(unitName = PERSISTANCE_UNIT_NAME)
    private EntityManager EMF;
    

    public EntityManager getEMF() {
        if (EMF == null){
            EMF = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME).createEntityManager();
        }
        return EMF;
    }

    public void setEMF(EntityManager EMF) {
        this.EMF = EMF;
    }
}

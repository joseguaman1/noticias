/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.noticias.controlador.Dao;

import com.ec.noticias.controlador.Conexion;
import com.ec.noticias.modelo.Administrador;
import javax.faces.bean.ApplicationScoped;
import javax.persistence.Query;


/**
 *
 * @author sissysebas
 */

@ApplicationScoped

public class AdministradorDao extends AdaptadorDao<Administrador> {
    private Administrador administrador;
    public AdministradorDao() {
        super(new Conexion(), Administrador.class);
    }

    public Administrador getAdministrador() {
        if(this.administrador == null)
            administrador = new Administrador();
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }
    
    public boolean guardar() {
        boolean band = false;
        try {
            if(this.administrador.getId() != null) {
                modificar(administrador);
            } else {
                guardar(administrador);
            }
            band = true;
        } catch (Exception e) {
            System.out.println("No se pudo guardar administrador "+e);            
        }
        return band;
    }
    
    public Administrador inicioSesion(String correo, String clave) {
        Administrador admin = null;
        try {
            Query q = getConexion().getEMF()
                    .createQuery("SELECT a FROM Administrador a WHERE a.correo = :correito AND a.clave = :clave");
            q.setParameter("correito", correo);
            q.setParameter("clave", clave);
            admin = (Administrador)q.getSingleResult();
        } catch (Exception e) {
        }
        return admin;
    }
            
            
            
            
            
            
            
            
            
            
            
    
}

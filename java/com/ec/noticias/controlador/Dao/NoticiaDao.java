/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.noticias.controlador.Dao;

import com.ec.noticias.controlador.Conexion;
import com.ec.noticias.modelo.Noticia;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.persistence.Query;


/**
 *
 * @author sissysebas
 */

@ApplicationScoped

public class NoticiaDao extends AdaptadorDao<Noticia> {
    private Noticia noticia;
    public NoticiaDao() {
        super(new Conexion(), Noticia.class);
    }

    public Noticia getNoticia() {
        if(this.noticia == null)
            noticia = new Noticia();
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }
    
    public boolean guardar() {
        boolean band = false;
        try {
            if(this.noticia.getId() != null) {
                modificar(noticia);
            } else {
                guardar(noticia);
            }
            band = true;
        } catch (Exception e) {
            System.out.println("No se pudo guardar noticia "+e);            
        }
        return band;
    }
    
    public List<Noticia> listadoNoticiasAdmin(String external) {
        List<Noticia> listado = new ArrayList<>();
        try {
            Query q = getConexion().getEMF()
                    .createQuery("SELECT a FROM Noticia a WHERE a.administrador.external_id = :external ORDER BY a.fecha_creacion");
            q.setParameter("external", external);
            
            listado = q.getResultList();
        } catch (Exception e) {
        }
        return listado;
    }
            
            
            
            
            
            
            
            
            
            
            
    
}

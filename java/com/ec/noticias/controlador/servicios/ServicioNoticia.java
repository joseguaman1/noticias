/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.noticias.controlador.servicios;

import com.ec.noticias.controlador.Dao.NoticiaDao;
import com.ec.noticias.modelo.Noticia;
import com.ec.noticias.modelo.enums.TipoNoticia;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sissysebas
 */
@Named("servicioNoticia")
@SessionScoped
public class ServicioNoticia implements Serializable {
    
    private NoticiaDao obj = new NoticiaDao();
    private Noticia noticia;
    private List<Noticia> listado = new ArrayList<>();

    public Noticia getNoticia() {
        if(noticia == null) {
            noticia = new Noticia();
        }
        return noticia;
    }
    
    public void fijarInstancia(Noticia noticia) {
        this.noticia = noticia;
    }
    
    public void guardar() {
        obj.setNoticia(noticia);
        String msg = "";
        msg = (obj.getNoticia().getId() != null) ? "Se ha modificicado " : "Se ha guardado";
        if(obj.guardar()) {
            fijarInstancia(null);
            obj.setNoticia(null);
            System.out.println(msg);
        } else {
            System.out.println("error");
        }
    }
    
    public void editar(Noticia noticia) {
        this.noticia = obj.obtener(noticia.getId());        
    }

    public List<Noticia> getListado() {
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String external = session.getAttribute("id") != null ? session.getAttribute("id").toString() : "";
        listado = obj.listadoNoticiasAdmin(external);
        return listado;
    }

    public void setListado(List<Noticia> listado) {
        this.listado = listado;
    }
    
    public List<TipoNoticia> getListaTipo() {
        return Arrays.asList(TipoNoticia.values()) ;
    }
    
}

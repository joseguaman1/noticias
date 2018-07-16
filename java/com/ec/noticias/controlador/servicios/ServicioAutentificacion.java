/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.noticias.controlador.servicios;

import com.ec.noticias.controlador.Dao.AdministradorDao;
import com.ec.noticias.modelo.Administrador;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.primefaces.context.RequestContext;

/**
 *
 * @author sissysebas
 */
@Named("servicioAutentificacion")
@SessionScoped
public class ServicioAutentificacion implements Serializable {    
    private AdministradorDao obj = new AdministradorDao();
    @Getter
    @Setter
    private String correo;
    @Getter
    @Setter
    private String clave;
    
    private boolean logueado;
    private String nombre;
    
    public void inicioSesion(ActionEvent event) {
        Administrador administrador = obj.inicioSesion(correo, clave);
        FacesMessage msg = null;
        RequestContext context = RequestContext.getCurrentInstance();
        if(administrador != null) {
            HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("username", administrador.getNombre());
            session.setAttribute("id", administrador.getExternal_id());
            session.setAttribute("correo", administrador.getCorreo());
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "Bienvenido administrador", 
                    session.getAttribute("username").toString());            
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Cuenta inexistente", 
                    "Por favor intente de nuevo");            
        }
        System.out.println("extenal " + UUID.randomUUID());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        //context.addCallbackParam("estalogueado", (administrador != null));
        PrimeFaces.current().ajax().addCallbackParam("estalogueado", (administrador != null));
        if(administrador != null) {
           PrimeFaces.current().ajax().addCallbackParam("view", "http://localhost:8080/noticias");
           //context.addCallbackParam("view", "http://localhost:8080/noticias/faces/administracion.xhtml");
        } 
    }

    public boolean isLogueado() {
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(session.getAttribute("id") != null)
            logueado = true;
        else 
            logueado = false;
        return logueado;
    }

    public String getNombre() {
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(session.getAttribute("username") != null)
            nombre = session.getAttribute("username").toString();
        else 
            nombre = "";
        return nombre;
    }
    
    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }
    public String verInicioSesion() {        
        return "/login.xhtml?faces-redirect=true";
    }
    
    
    
    
    
    
    
    
    
}

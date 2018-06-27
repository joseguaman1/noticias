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

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

/**
 *
 * @author sissysebas
 */
@Named("servicioAdministrador")
@SessionScoped
public class ServicioAdministrador implements Serializable {
    
    private AdministradorDao obj = new AdministradorDao();
    private Administrador administrador;
    private List<Administrador> listado = new ArrayList<>();

    public Administrador getAdministrador() {
        if(administrador == null) {
            administrador = new Administrador();
        }
        return administrador;
    }
    
    public void fijarInstancia(Administrador administrador) {
        this.administrador = administrador;
    }
    
    public void guardar() {
        obj.setAdministrador(administrador);
        String msg = "";
        msg = (obj.getAdministrador().getId() != null) ? "Se ha modificicado " : "Se ha guardado";
        if(obj.guardar()) {
            fijarInstancia(null);
            obj.setAdministrador(null);
            System.out.println(msg);
        } else {
            System.out.println("error");
        }
    }
    
    public void editar(Administrador administrador) {
        this.administrador = obj.obtener(administrador.getId());        
    }

    public List<Administrador> getListado() {
        listado = obj.listar();
        return listado;
    }

    public void setListado(List<Administrador> listado) {
        this.listado = listado;
    }
    
    
    
}

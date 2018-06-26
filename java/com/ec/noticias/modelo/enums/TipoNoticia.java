/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.noticias.modelo.enums;

/**
 *
 * @author sissysebas
 */
public enum TipoNoticia {
    LOCAL("Local"),
    NACIONAL("Nacional"),
    INTERNACIONAL("Internacional"),
    DEPORTES("Deportes");
    private String tipo;

    private TipoNoticia(String tipo) {
        this.tipo = tipo;
    }
    
}

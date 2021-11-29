/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MEMO0464
 */
public class Producto {
    String descripcion;
    float precio;
    int id, existencia;

    public Producto() {
    }

    public Producto(String descripcion, float precio, int existencia) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.existencia = existencia;
    }
    
    
}

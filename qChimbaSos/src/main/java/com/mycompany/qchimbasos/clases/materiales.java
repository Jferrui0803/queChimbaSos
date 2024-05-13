/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.qchimbasos.clases;

/**
 *
 * @author tadeo
 */
public class materiales {
    private String nombre;
    private String tipo_material;
    private String descripcion;
    private int numero_serie;

    public materiales(String nombre, String tipo_material, String descripcion, int numero_serie) {
        this.nombre = nombre;
        this.tipo_material = tipo_material;
        this.descripcion = descripcion;
        this.numero_serie = numero_serie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo_material() {
        return tipo_material;
    }

    public void setTipo_material(String tipo_material) {
        this.tipo_material = tipo_material;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumero_serie() {
        return numero_serie;
    }

    public void setNumero_serie(int numero_serie) {
        this.numero_serie = numero_serie;
    }
    
    
}

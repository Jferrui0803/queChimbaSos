/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.qchimbasos.clases;

/**
 *
 * @author tadeo
 */
public abstract class Inventario {
    private int idAlmacen;
    private int idProducto;
    private int idUbicacion;
    private int cantidad;
    private String fechaCaducidad;
    private int stockMinimo;

    public Inventario(int idAlmacen, int idProducto, int idUbicacion, int cantidad, String fechaCaducidad, int stockMinimo) {
        this.idAlmacen = idAlmacen;
        this.idProducto = idProducto;
        this.idUbicacion = idUbicacion;
        this.cantidad = cantidad;
        this.fechaCaducidad = fechaCaducidad;
        this.stockMinimo = stockMinimo;
    }
    
    

    public int getIdAlmacen() {
        return idAlmacen;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.qchimbasos.clases;

/**
 *
 * @author tadeo
 */
public class InventarioReactivosProvisional extends InventarioProvisional{
    private String fechaCaducidad;
    private int cantidad;
    private int stockMinimo;

    public InventarioReactivosProvisional(int idAlmacen, int idProducto, int idUbicacion, int cantidad, String fechaCaducidad, int stockMinimo) {
        super(idAlmacen, idProducto, idUbicacion, cantidad, fechaCaducidad, stockMinimo);
        this.cantidad = cantidad;
        this.fechaCaducidad = fechaCaducidad;
        this.stockMinimo = stockMinimo;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
    
    
}

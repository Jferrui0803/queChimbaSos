/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.qchimbasos.clases;

/**
 *
 * @author tadeo
 */
public class InventarioReactivosMateriales {

    private String idAlmacen;
    private int codigoAlmacen;
    private String idUbicacion;
    private int codigoUbicacion;
    private int cantidad;
    private String fechaCaducidad;
    private int stockMinimo;

    public InventarioReactivosMateriales(String idAlmacen, String idUbicacion, int cantidad, String fechaCaducidad, int stockMinimo) {
        this.idAlmacen = idAlmacen;
        this.idUbicacion = idUbicacion;
        this.cantidad = cantidad;
        this.fechaCaducidad = fechaCaducidad;
        this.stockMinimo = stockMinimo;
        setCodigoAlmacen(idAlmacen);
        setCodigoUbicacion(idUbicacion);

    }

    public String getIdAlmacen() {
        return idAlmacen;
    }

    public int getCodigoAlmacen() {
        return codigoAlmacen;
    }

    public String getIdUbicacion() {
        return idUbicacion;
    }

    public int getCodigoUbicacion() {
        return codigoUbicacion;
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

    public void setIdAlmacen(String idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public void setCodigoAlmacen(String idAlmacen) {
        switch (idAlmacen) {
            case "Almacen 1 /principal" -> {
                this.codigoAlmacen = 1;
            }
            case "Almacen General" -> {
                this.codigoAlmacen = 2;
            }
            case "Laboratorio Instrumental" -> {
                this.codigoAlmacen = 3;
            }
        }
    }

    public void setIdUbicacion(String idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public void setCodigoUbicacion(String idUbicacion) {
        switch (idUbicacion) {
            case "1CA" -> {
                this.codigoUbicacion = 1;
            }
            case "2N" -> {
                this.codigoUbicacion = 2;
            }
            case "3N" -> {
                this.codigoUbicacion = 3;
            }
            case "4N" -> {
                this.codigoUbicacion = 4;
            }
            case "5N" -> {
                this.codigoUbicacion = 5;
            }
            case "8l" -> {
                this.codigoUbicacion = 6;
            }
            case "estantería 1, balda 3" -> {
                this.codigoUbicacion = 7;
            }
            case "estantería 1,balda 4" -> {
                this.codigoUbicacion = 8;
            }
            case "C1" -> {
                this.codigoUbicacion = 9;
            }
            case "C2" -> {
                this.codigoUbicacion = 10;
            }
            case "P1" -> {
                this.codigoUbicacion = 11;
            }
            case "P2" -> {
                this.codigoUbicacion = 12;
            }
            case "Estantería 0, balda 4" -> {
                this.codigoUbicacion = 13;
            }
        }

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


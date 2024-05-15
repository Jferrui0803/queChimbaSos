/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.qchimbasos.clases;

import Excepciones.LongitudNSerieIncorrectaException;
import Excepciones.NombreIncorrectoException;

/**
 *
 * @author tadeo
 */
public class Materiales {

    private String nombre;
    private String tipoMaterial;
    private String descripcion;
    private int numeroSerie;

    private Materiales(String nombre, String tipo_material, String descripcion, int numero_serie) {
        this.nombre = nombre;
        this.tipoMaterial = tipo_material;
        this.descripcion = descripcion;
        this.numeroSerie = numero_serie;
    }

    public static Materiales crearMateriales(String nombre, String tipo_material, String descripcion, int numero_serie) {
        try {
            if (!(validarNombre(nombre)) || !(validarNSerie(numero_serie))) {

                return new Materiales(nombre, tipo_material, descripcion, numero_serie);
            }

        } catch (NombreIncorrectoException |  LongitudNSerieIncorrectaException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            return null; 
        }
        return new Materiales(nombre, tipo_material, descripcion, numero_serie);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo_material() {
        return tipoMaterial;
    }

    public void setTipo_material(String tipo_material) {
        this.tipoMaterial = tipo_material;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumero_serie() {
        return numeroSerie;
    }

    public void setNumero_serie(int numero_serie) {
        this.numeroSerie = numero_serie;
    }

    public static boolean validarNombre(String nombre) throws NombreIncorrectoException {
        if (nombre.isBlank() || nombre.isEmpty()) {
            throw new NombreIncorrectoException();
        }
        return nombre.isBlank() || nombre.isEmpty();
    }

    public static boolean validarNSerie(int numero_serie) throws LongitudNSerieIncorrectaException {
        String nSerie = Integer.toString(numero_serie);
        if (nSerie.length() > 6 || nSerie.length() < 6) {
            throw new LongitudNSerieIncorrectaException();

        }
        return nSerie.length() > 6 || nSerie.length() < 6;

    }

}

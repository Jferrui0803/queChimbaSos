/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.qchimbasos.clases;

import Excepciones.NombreIncorrectoException;

/**
 *
 * @author tadeo
 */
public class Auxiliares {

    private String nombre;
    private String tipo_material;

    private Auxiliares(String nombre, String tipo_material) {
        this.nombre = nombre;
        this.tipo_material = tipo_material;
    }

    public static Auxiliares crearAuxiliar(String nombre, String tipo_material) {
        try {
            if (!(validarNombre(nombre))) {


                return new Auxiliares(nombre,  tipo_material);
            }

        } catch (NombreIncorrectoException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        }
        return new Auxiliares(nombre,  tipo_material);

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

    public static boolean validarNombre(String nombre) throws NombreIncorrectoException {
        if (nombre.isBlank() || nombre.isEmpty()) {
            throw new NombreIncorrectoException();
        }
        return nombre.isBlank() || nombre.isEmpty();
    }

}

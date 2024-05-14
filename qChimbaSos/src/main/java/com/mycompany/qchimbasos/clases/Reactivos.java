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
public class Reactivos {

    private String nombre;
    private String idFormato;
    private String gradoPuereza;
    private int codigoFormat;

    public Reactivos(String nombre, String id_formato, String grado_pureza) {

        this.nombre = nombre;
        this.idFormato = id_formato;
        this.gradoPuereza = grado_pureza;
        setCodigoFormat(id_formato);

    }

    public static Reactivos crearReactivos(String nombre, String id_formato, String grado_pureza) {

        try {
            if (!(validarNombre(nombre))) {
                if (grado_pureza.isBlank() || grado_pureza.isEmpty()) {
                    grado_pureza = "No viene reflejado";
                }

                return new Reactivos(nombre, id_formato, grado_pureza);
            }

        } catch (NombreIncorrectoException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        }
        return new Reactivos(nombre, id_formato, grado_pureza);

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdFormato() {

        return idFormato;
    }

    public void setIdFormato(String idFormato) {
        this.idFormato = idFormato;
    }

    public String getGradoPuereza() {
        return gradoPuereza;
    }

    public void setGradoPuereza(String gradoPuereza) {

        if (gradoPuereza.isBlank() || gradoPuereza.isEmpty()) {
            this.gradoPuereza = "No viene reflejado";

        }
        this.gradoPuereza = gradoPuereza;
    }

    public int getCodigoFormat() {
        return codigoFormat;
    }

    public void setCodigoFormat(String id_formato) {
        switch (id_formato) {
            case "1kg" -> {
                this.codigoFormat = 1;
            }
            case "100 g" -> {
                this.codigoFormat = 2;
            }
            case "250 g" -> {
                this.codigoFormat = 3;
            }
            case "500 g" -> {
                this.codigoFormat = 4;
            }
            case "5 g" -> {
                this.codigoFormat = 5;
            }
            case "No viene reflejado" -> {
                this.codigoFormat = 6;
            }
            case "1 L" -> {
                this.codigoFormat = 7;
            }
            case "500 mL" -> {
                this.codigoFormat = 8;
            }
            case "5 Kg" -> {
                this.codigoFormat = 9;
            }
            case "2.5 L" -> {
                this.codigoFormat = 10;
            }
            case "250 mL" -> {
                this.codigoFormat = 11;
            }
            case "100 mL" -> {
                this.codigoFormat = 12;
            }
            case "250 g,1 kg" -> {
                this.codigoFormat = 13;
            }
            case "10 g" -> {
                this.codigoFormat = 14;
            }

        }
    }

    @Override
    public String toString() {
        return "Reactivos{" + "nombre=" + nombre + ", id_formato=" + idFormato + ", grado_pureza=" + gradoPuereza + '}';
    }

    public static boolean validarNombre(String nombre) throws NombreIncorrectoException {
        if (nombre.isBlank() || nombre.isEmpty()) {
            throw new NombreIncorrectoException();
        }
        return nombre.isBlank() || nombre.isEmpty();
    }

}

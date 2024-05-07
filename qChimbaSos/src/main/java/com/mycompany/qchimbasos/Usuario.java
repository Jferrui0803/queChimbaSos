/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.qchimbasos;

/**
 *
 * @author DAW MAÑANA
 */
public class Usuario {
    private String nombre;
    private String contraseña;

//    public Usuario(String nombre, String contraseña) {
//       setNombre(nombre);
//       setContraseña(contraseña);
//    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

//    public void setContraseña(String contraseña) {
//        if (validarContraseña(contraseña)){
//           throw new  
//        }
//        this.contraseña = contraseña;
//    }
    
    
    public boolean validarNombre(String nombre){
     
        return (nombre.isBlank() || nombre.isEmpty());
        
    }
    
    
    public boolean validarContraseña(String contraseña){
        return (contraseña.isBlank() || contraseña.isEmpty() || contraseña.isBlank() || contraseña.isEmpty());
        
    }
    
}

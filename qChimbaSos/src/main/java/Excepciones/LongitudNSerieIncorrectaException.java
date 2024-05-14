/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

/**
 *
 * @author Usuario
 */
public class LongitudNSerieIncorrectaException extends Exception{
    public LongitudNSerieIncorrectaException(){
        super("La longitud del Nº de serie es incorrecta "
                + ", debe ser de 6 digitos ");
    }
    
}

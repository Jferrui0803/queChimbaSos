/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.qchimbasos.clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tadeo
 */
public class Conexion {
 
    private String JDBC_DRIVER;
    private String DB_URL;
    private String USER;
    private String PASS;
    // Atributos variables
    Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public Conexion(String JDBC_DRIVER, String DB_URL, String USER, String PASS) {
        this.JDBC_DRIVER = JDBC_DRIVER;
        this.DB_URL = DB_URL;
        this.USER = USER;
        this.PASS = PASS;
    }
    

    
    
    // Conexion a la BD
    public Connection conecta() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(this.DB_URL, this.USER, this.PASS);
            System.out.println("CONECTADA");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        return con;
    }
    
    public  void cerrar() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
}

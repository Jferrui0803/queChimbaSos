/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.qchimbasos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author DAW MAÑANA
 */
public class Registro extends javax.swing.JFrame {

    /**
     * Creates new form Registro
     */
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/pruebas";
    static final String USER = "root";
    static final String PASS = "";

    // Atributos variables
    Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public static Registro reg;

    public Registro() {

        initComponents();
        setLocationRelativeTo(null);
    }

    // Creamos método para hacer la conexión a la base de datos
    public Connection conecta() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("CONECTADA");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        return con;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jUsu1 = new javax.swing.JTextField();
        jPasswd1 = new javax.swing.JTextField();
        jPasswd2 = new javax.swing.JTextField();
        jCrearUsuario = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Registro de usuarios"));

        jUsu1.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuario"));

        jPasswd1.setBorder(javax.swing.BorderFactory.createTitledBorder("Contraseña"));

        jPasswd2.setBorder(javax.swing.BorderFactory.createTitledBorder("Confirmar Contraseña"));
        jPasswd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswd2ActionPerformed(evt);
            }
        });

        jCrearUsuario.setText("Crear Usuario");
        jCrearUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCrearUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jUsu1)
                            .addComponent(jPasswd1)
                            .addComponent(jPasswd2, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jCrearUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(175, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jUsu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPasswd1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPasswd2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jCrearUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCrearUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCrearUsuarioActionPerformed
        // TODO add your handling code here:
        String usuario = jUsu1.getText();
        String passwd1 = jPasswd1.getText();
        String passwd2 = jPasswd2.getText();

        if (usuario.isBlank() || usuario.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null, "El usuario no puede estar vacio");
        } else if (passwd1.isBlank() || passwd1.isEmpty() || passwd2.isBlank() || passwd2.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null, "Algun campo de la contraseña esta vacio");
        } else if (!passwd1.equals(passwd2)) {
            javax.swing.JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
        } else {

            // Verificar en la base de datos
            try {
                conexion = conecta();

                //Sentencia para añadir un nuevo usuario y contraseña 
                String sql = "INSERT INTO usuario (Nombre, Contraseña) VALUES (?, ?)";

                //Sentencia para comprobar si existe en la base de datos
                String query = "SELECT * FROM usuario WHERE nombre=?";

                ps = conexion.prepareStatement(query);
                ps.setString(1, usuario);
                rs = ps.executeQuery();
                if (rs.next())  {
                    // Si las credenciales son iguales, muestra un mensaje de error
                    javax.swing.JOptionPane.showMessageDialog(null, "Inicio de no valido , el usuario ya existe ");
                    return;
                }

                ps = conexion.prepareStatement(sql);
                ps.setString(1, usuario);
                ps.setString(2, passwd1);
                int filasInsertadas = ps.executeUpdate();

                if (filasInsertadas > 0) {
                    // Si las credenciales son correctas, muestra un mensaje de éxito
                    javax.swing.JOptionPane.showMessageDialog(null, "Usuario creado correctamente");

                    dispose();
                    // Aquí puedes abrir la nueva ventana o realizar otras acciones necesarias
                } else {
                    // Si las credenciales son incorrectas, muestra un mensaje de error
                    javax.swing.JOptionPane.showMessageDialog(null, "Algo salio mal");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            } finally {
                try {
                    // Cerrar recursos
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
                    System.out.println("Error al cerrar recursos: " + ex.getMessage());
                }
            }
        }


    }//GEN-LAST:event_jCrearUsuarioActionPerformed

    private void jPasswd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswd2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswd2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jCrearUsuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jPasswd1;
    private javax.swing.JTextField jPasswd2;
    private javax.swing.JTextField jUsu1;
    // End of variables declaration//GEN-END:variables
}

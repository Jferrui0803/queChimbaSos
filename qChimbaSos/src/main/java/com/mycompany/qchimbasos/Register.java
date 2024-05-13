package com.mycompany.qchimbasos;

import static com.mycompany.qchimbasos.Register.DB_URL;
import static com.mycompany.qchimbasos.Register.PASS;
import static com.mycompany.qchimbasos.Register.USER;
import static com.mycompany.qchimbasos.Register.reg;
import static com.mycompany.qchimbasos.busquedaAlum.log;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.swing.JOptionPane;

public class Register extends javax.swing.JFrame {
    
    int xMouse, yMouse;
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/qchimbasos";
    static final String USER = "root";
    static final String PASS = "";

    // Atributos variables
    Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public static Register reg;
    public static Login log;
    
    public Register() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        logoIZV = new javax.swing.JLabel();
        exitBtn = new javax.swing.JPanel();
        exitButton = new javax.swing.JButton();
        header = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        userLabel = new javax.swing.JLabel();
        jUsu = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        contraseña = new javax.swing.JLabel();
        jPasswd1 = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        jCrearUsuario = new javax.swing.JButton();
        confirmarContraseña = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jshowPasswd1 = new javax.swing.JCheckBox();
        jshowPasswd2 = new javax.swing.JCheckBox();
        jPasswd2 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logoIZV.setBackground(new java.awt.Color(0, 134, 190));
        logoIZV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logoizv.png"))); // NOI18N
        bg.add(logoIZV, new org.netbeans.lib.awtextra.AbsoluteConstraints(548, 0, -1, 500));

        exitBtn.setBackground(new java.awt.Color(255, 255, 255));

        exitButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/salida.png"))); // NOI18N
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout exitBtnLayout = new javax.swing.GroupLayout(exitBtn);
        exitBtn.setLayout(exitBtnLayout);
        exitBtnLayout.setHorizontalGroup(
            exitBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, exitBtnLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        exitBtnLayout.setVerticalGroup(
            exitBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        bg.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        header.setBackground(new java.awt.Color(255, 255, 255));
        header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                headerMouseDragged(evt);
            }
        });
        header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                headerMousePressed(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        bg.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 50));

        title.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        title.setText("Registrate");
        bg.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        userLabel.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel.setText("USUARIO");
        bg.add(userLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        jUsu.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jUsu.setForeground(new java.awt.Color(204, 204, 204));
        jUsu.setText("Ingrese su nombre de usuario");
        jUsu.setBorder(null);
        jUsu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jUsuMousePressed(evt);
            }
        });
        jUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUsuActionPerformed(evt);
            }
        });
        bg.add(jUsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 410, 30));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        bg.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 410, 20));

        contraseña.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        contraseña.setText("CONTRASEÑA");
        bg.add(contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        jPasswd1.setForeground(new java.awt.Color(204, 204, 204));
        jPasswd1.setText("********");
        jPasswd1.setBorder(null);
        jPasswd1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPasswd1MousePressed(evt);
            }
        });
        jPasswd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswd1ActionPerformed(evt);
            }
        });
        bg.add(jPasswd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 410, 30));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        bg.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 410, 20));

        jCrearUsuario.setBackground(new java.awt.Color(0, 76, 125));
        jCrearUsuario.setFont(new java.awt.Font("Rubik", 1, 14)); // NOI18N
        jCrearUsuario.setForeground(new java.awt.Color(255, 255, 255));
        jCrearUsuario.setText("Crear Usuario");
        jCrearUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCrearUsuarioActionPerformed(evt);
            }
        });
        bg.add(jCrearUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 140, 40));

        confirmarContraseña.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        confirmarContraseña.setText("CONFIRMAR CONTRASEÑA");
        bg.add(confirmarContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, -1));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        bg.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 410, 20));

        jshowPasswd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jshowPasswd1ActionPerformed(evt);
            }
        });
        bg.add(jshowPasswd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 260, -1, -1));
        bg.add(jshowPasswd2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 350, -1, -1));

        jPasswd2.setForeground(new java.awt.Color(204, 204, 204));
        jPasswd2.setText("********");
        jPasswd2.setBorder(null);
        jPasswd2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPasswd2MousePressed(evt);
            }
        });
        jPasswd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswd2ActionPerformed(evt);
            }
        });
        bg.add(jPasswd2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 410, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jUsuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jUsuMousePressed
        if (jUsu.getText().equals("Ingrese su nombre de usuario")) {
            jUsu.setText("");
            jUsu.setForeground(Color.black);
        }
        if (String.valueOf(jPasswd1.getPassword()).isEmpty()) {
            jPasswd1.setText("********");
            jPasswd1.setForeground(Color.gray);
        }
        if (String.valueOf(jPasswd2.getPassword()).isEmpty()) {
            jPasswd2.setText("********");
            jPasswd2.setForeground(Color.gray);
        }
    }//GEN-LAST:event_jUsuMousePressed

    private void jPasswd1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswd1MousePressed
        if (String.valueOf(jPasswd1.getPassword()).equals("********")) {
            jPasswd1.setText("");
            jPasswd1.setForeground(Color.black);
        }
        if (jUsu.getText().isEmpty()) {
            jUsu.setText("Ingrese su nombre de usuario");
            jUsu.setForeground(Color.gray);
        }
    }//GEN-LAST:event_jPasswd1MousePressed

    private void jCrearUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCrearUsuarioActionPerformed
        // TODO add your handling code here:
      // TODO add your handling code here:
        String usuario = jUsu.getText();
        String passwd1 = jPasswd1.getText();
        String passwd2 = jPasswd2.getText();

        // Excepción para que el campo usuario no este vacio
        if (usuario.isBlank() || usuario.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "El usuario no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
            // Excepción para que no este vacio ningun campo de contraseña
        } else if (passwd1.isBlank() || passwd1.isEmpty() || passwd2.isBlank() || passwd2.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Algún campo de la contraseña esta vacio", "Error", JOptionPane.ERROR_MESSAGE);
            // Excepcion en caso de que las contraseñas no coincidan
        } else if (!passwd1.equals(passwd2)) {
            JOptionPane.showMessageDialog(rootPane, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
            // Excepción en caso de que no se cumplan los requisitos de contraseña
        } else if (!passwd2.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$._#-@=%*]).{8,}$")) {
            JOptionPane.showMessageDialog(rootPane, "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula, un dígito y un carácter especial.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            // Verificar en la base de datos
            try {
                conexion = conecta();

                //Sentencia para añadir un nuevo usuario y contraseña 
                String sql = "INSERT INTO usuario (Nombre, Contraseña) VALUES (?, ?)";

                ps = conexion.prepareStatement(sql);
                ps.setString(1, usuario);
                ps.setString(2, passwd1);
                int filasInsertadas = ps.executeUpdate();

                if (filasInsertadas > 0) {
                    // Si las credenciales son correctas, muestra un mensaje de éxito
                    javax.swing.JOptionPane.showMessageDialog(null, "Usuario creado correctamente");
                    
                    log = new Login();
                    log.setVisible(true);
                    
                    dispose();
                    // Aquí puedes abrir la nueva ventana o realizar otras acciones necesarias
                } else {
                    // Si las credenciales son incorrectas, muestra un mensaje de error
                    JOptionPane.showMessageDialog(rootPane, "Algo salio mal", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLIntegrityConstraintViolationException ex) {
                 JOptionPane.showMessageDialog(rootPane, "El usuario de nombre: " + 
                         usuario + " , ya está registrado en la base de datos", "Error",
                         JOptionPane.ERROR_MESSAGE);

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
//                if (ex instanceof SQLIntegrityConstraintViolationException) {
//                    if (ex.getMessage().contains("Duplicate entry")) {
//                        JOptionPane.showMessageDialog(rootPane, "El usuario ya existe", "Error", JOptionPane.ERROR_MESSAGE);
//                    }
//                } else {
//                    javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
//                }

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

    private void jUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUsuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jUsuActionPerformed

    private void jshowPasswd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jshowPasswd1ActionPerformed
        // TODO add your handling code here:
        char echoChar = (jshowPasswd1.isSelected()) ? '\u0000' : '\u2022';
        jPasswd1.setEchoChar(echoChar);
    }//GEN-LAST:event_jshowPasswd1ActionPerformed

    private void jPasswd2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswd2MousePressed
        // TODO add your handling code here:
        
        if (String.valueOf(jPasswd2.getPassword()).equals("********")) {
            jPasswd2.setText("");
            jPasswd2.setForeground(Color.black);
        }
        if (jUsu.getText().isEmpty()) {
            jUsu.setText("Ingrese su nombre de usuario");
            jUsu.setForeground(Color.gray);
        }
    }//GEN-LAST:event_jPasswd2MousePressed

    private void jPasswd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswd2ActionPerformed
        // TODO add your handling code here:
        char echoChar = (jshowPasswd2.isSelected()) ? '\u0000' : '\u2022';
        jPasswd2.setEchoChar(echoChar);
    }//GEN-LAST:event_jPasswd2ActionPerformed

    private void jPasswd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswd1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswd1ActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
       log = new Login();
       log.setVisible(true);
        dispose();
    }//GEN-LAST:event_exitButtonActionPerformed

    private void headerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_headerMousePressed

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_headerMouseDragged

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
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JLabel confirmarContraseña;
    private javax.swing.JLabel contraseña;
    private javax.swing.JPanel exitBtn;
    private javax.swing.JButton exitButton;
    private javax.swing.JPanel header;
    private javax.swing.JButton jCrearUsuario;
    private javax.swing.JPasswordField jPasswd1;
    private javax.swing.JPasswordField jPasswd2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jUsu;
    private javax.swing.JCheckBox jshowPasswd1;
    private javax.swing.JCheckBox jshowPasswd2;
    private javax.swing.JLabel logoIZV;
    private javax.swing.JLabel title;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables
}

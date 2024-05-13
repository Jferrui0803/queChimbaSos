package com.mycompany.qchimbasos;

import static com.mycompany.qchimbasos.Login.DB_URL;
import static com.mycompany.qchimbasos.Login.PASS;
import static com.mycompany.qchimbasos.Login.USER;
import static com.mycompany.qchimbasos.Login.reg;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {
    
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
    public static busquedaAlum bsqA;
    public static busquedaAdmin bsqAdmin;
    
    public Login() {
        initComponents();
        
        // Metodo para iniciar la ventana en el centro
        setLocationRelativeTo(null);
        
   
    }
    
    // Conexion a la BD
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
        jBregistro = new javax.swing.JButton();
        exitBtn = new javax.swing.JPanel();
        exitButton = new javax.swing.JButton();
        header = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        userLabel = new javax.swing.JLabel();
        jTusuario = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        passLabel = new javax.swing.JLabel();
        jShowpasswd = new javax.swing.JCheckBox();
        jTcontraseña = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        loginButton = new javax.swing.JButton();
        logoIZV = new javax.swing.JLabel();
        loginAlumno = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBregistro.setBackground(new java.awt.Color(0, 76, 125));
        jBregistro.setFont(new java.awt.Font("Rubik", 1, 14)); // NOI18N
        jBregistro.setForeground(new java.awt.Color(255, 255, 255));
        jBregistro.setText("REGISTRO");
        jBregistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBregistroActionPerformed(evt);
            }
        });
        bg.add(jBregistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 130, 40));

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
            .addGap(0, 560, Short.MAX_VALUE)
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        bg.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 50));

        title.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        title.setText("INICIAR SESIÓN");
        bg.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        userLabel.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel.setText("USUARIO");
        bg.add(userLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        jTusuario.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jTusuario.setForeground(new java.awt.Color(204, 204, 204));
        jTusuario.setText("Ingrese su nombre de usuario");
        jTusuario.setBorder(null);
        jTusuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTusuarioMousePressed(evt);
            }
        });
        jTusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTusuarioActionPerformed(evt);
            }
        });
        bg.add(jTusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 410, 30));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        bg.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 410, 20));

        passLabel.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        passLabel.setText("CONTRASEÑA");
        bg.add(passLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, -1, -1));

        jShowpasswd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jShowpasswdActionPerformed(evt);
            }
        });
        bg.add(jShowpasswd, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, -1, -1));

        jTcontraseña.setForeground(new java.awt.Color(204, 204, 204));
        jTcontraseña.setText("********");
        jTcontraseña.setBorder(null);
        jTcontraseña.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTcontraseñaMousePressed(evt);
            }
        });
        jTcontraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTcontraseñaActionPerformed(evt);
            }
        });
        bg.add(jTcontraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 410, 30));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        bg.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 410, 20));

        loginButton.setBackground(new java.awt.Color(0, 76, 125));
        loginButton.setFont(new java.awt.Font("Rubik", 1, 14)); // NOI18N
        loginButton.setForeground(new java.awt.Color(255, 255, 255));
        loginButton.setText("ENTRAR");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        bg.add(loginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 130, 40));

        logoIZV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logoizv.png"))); // NOI18N
        bg.add(logoIZV, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, -1, 500));

        loginAlumno.setBackground(new java.awt.Color(0, 76, 125));
        loginAlumno.setFont(new java.awt.Font("Rubik", 1, 14)); // NOI18N
        loginAlumno.setForeground(new java.awt.Color(255, 255, 255));
        loginAlumno.setText("ENTRAR COMO ALUMNO");
        loginAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginAlumnoActionPerformed(evt);
            }
        });
        bg.add(loginAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 420, 230, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTusuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTusuarioMousePressed
        if (jTusuario.getText().equals("Ingrese su nombre de usuario")) {
            jTusuario.setText("");
            jTusuario.setForeground(Color.black);
        }
        if (String.valueOf(jTcontraseña.getPassword()).isEmpty()) {
            jTcontraseña.setText("********");
            jTcontraseña.setForeground(Color.gray);
        }
    }//GEN-LAST:event_jTusuarioMousePressed

    private void jTcontraseñaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTcontraseñaMousePressed
        if (String.valueOf(jTcontraseña.getPassword()).equals("********")) {
            jTcontraseña.setText("");
            jTcontraseña.setForeground(Color.black);
        }
        if (jTusuario.getText().isEmpty()) {
            jTusuario.setText("Ingrese su nombre de usuario");
            jTusuario.setForeground(Color.gray);
        }
    }//GEN-LAST:event_jTcontraseñaMousePressed

   
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
       String usuario = jTusuario.getText();
        String contraseña = new String(jTcontraseña.getPassword());

        // Verificar en la base de datos
        try {
            conexion = conecta();
            String sql = "SELECT * FROM usuario WHERE nombre=? AND contraseña=?";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, contraseña);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Si las credenciales son correctas, muestra un mensaje de éxito
                javax.swing.JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
                
                bsqAdmin = new busquedaAdmin();
                bsqAdmin.setVisible(true);
                dispose();
            } else {
                // Si las credenciales son incorrectas, muestra un mensaje de error
                JOptionPane.showMessageDialog(rootPane, "Nombre de usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            try {
                // Cerrar recursos
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    private void jTusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTusuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTusuarioActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_exitButtonActionPerformed

    private void jBregistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBregistroActionPerformed
        // TODO add your handling code here:
        reg = new Register();
        reg.setVisible(true);
        dispose();
    }//GEN-LAST:event_jBregistroActionPerformed

    private void jShowpasswdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jShowpasswdActionPerformed
        // TODO add your handling code here:
        char echoChar = (jShowpasswd.isSelected()) ? '\u0000' : '\u2022';
        jTcontraseña.setEchoChar(echoChar);
    }//GEN-LAST:event_jShowpasswdActionPerformed

    private void loginAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginAlumnoActionPerformed
        // TODO add your handling code here:
        bsqA = new busquedaAlum();
        bsqA.setVisible(true);
        dispose();
    }//GEN-LAST:event_loginAlumnoActionPerformed

    private void headerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_headerMousePressed

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_headerMouseDragged

    private void jTcontraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTcontraseñaActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_jTcontraseñaActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JPanel exitBtn;
    private javax.swing.JButton exitButton;
    private javax.swing.JPanel header;
    private javax.swing.JButton jBregistro;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JCheckBox jShowpasswd;
    private javax.swing.JPasswordField jTcontraseña;
    private javax.swing.JTextField jTusuario;
    private javax.swing.JButton loginAlumno;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel logoIZV;
    private javax.swing.JLabel passLabel;
    private javax.swing.JLabel title;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables
}

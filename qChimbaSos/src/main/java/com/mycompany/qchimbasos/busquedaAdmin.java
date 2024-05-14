/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.qchimbasos;

import static com.mycompany.qchimbasos.Login.bsqAdmin;
import static com.mycompany.qchimbasos.Register.log;
import com.mycompany.qchimbasos.clases.Conexion;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author DAW MAÑANA
 */
public class busquedaAdmin extends javax.swing.JFrame {

    /**
     * Creates new form busquedaAlum
     */
    private Conexion conexion;

    public static Modificacion modif;
    public static Login log;
    public static AgregacionReactivo reac;
    public static AgregacionMaterial mat;
    public static AgregacionAuxiliar aux;

    public busquedaAdmin() {
        initComponents();
        setLocationRelativeTo(null);
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
        jBsalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLproductos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jtakeFormato = new javax.swing.JTextField();
        jLproductos1 = new javax.swing.JLabel();
        jtakeUbicacion = new javax.swing.JTextField();
        jtakeNombre = new javax.swing.JTextField();
        jLnombre = new javax.swing.JLabel();
        jLubicacion = new javax.swing.JLabel();
        jLubicacion1 = new javax.swing.JLabel();
        jtakeUbicacion1 = new javax.swing.JTextField();
        jBmodificar = new javax.swing.JButton();
        jBbuscar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuReactivo = new javax.swing.JMenuItem();
        jMenuAuxiliar = new javax.swing.JMenuItem();
        jMenuMaterial = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jBsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cerrarSesion.png"))); // NOI18N
        jBsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsalirActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logoizv.png"))); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Reactivos", "Auxiliares", "Materiales" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLproductos.setForeground(new java.awt.Color(153, 153, 153));
        jLproductos.setText("Productos *");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jtakeFormato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtakeFormatoActionPerformed(evt);
            }
        });

        jLproductos1.setForeground(new java.awt.Color(153, 153, 153));
        jLproductos1.setText("Formato");

        jtakeUbicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtakeUbicacionActionPerformed(evt);
            }
        });

        jtakeNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtakeNombreActionPerformed(evt);
            }
        });

        jLnombre.setForeground(new java.awt.Color(153, 153, 153));
        jLnombre.setText("Nombre");

        jLubicacion.setForeground(new java.awt.Color(153, 153, 153));
        jLubicacion.setText("Ubicación");

        jLubicacion1.setForeground(new java.awt.Color(153, 153, 153));
        jLubicacion1.setText("Localización");

        jtakeUbicacion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtakeUbicacion1ActionPerformed(evt);
            }
        });

        jBmodificar.setText("Modificar");
        jBmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBmodificarActionPerformed(evt);
            }
        });

        jBbuscar.setText("Buscar");
        jBbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBbuscarActionPerformed(evt);
            }
        });

        jButton1.setText("Tipos de Riesgos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLproductos))
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLnombre))
                            .addComponent(jtakeNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLproductos1))
                            .addComponent(jtakeFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jLubicacion))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jtakeUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLubicacion1))
                            .addComponent(jtakeUbicacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(jBbuscar)
                        .addContainerGap(361, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(jButton1)
                        .addGap(107, 107, 107)
                        .addComponent(jBmodificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBsalir)
                        .addGap(110, 110, 110))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLnombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtakeNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLproductos)
                            .addComponent(jLubicacion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtakeUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLproductos1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtakeFormato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLubicacion1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtakeUbicacion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addComponent(jBbuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBmodificar)
                            .addComponent(jButton1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jBsalir)))
                .addGap(54, 54, 54))
        );

        jMenu1.setText("Agregar Producto");

        jMenuReactivo.setText("Reactivo");
        jMenuReactivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuReactivoActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuReactivo);

        jMenuAuxiliar.setText("Auxiliar");
        jMenuAuxiliar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAuxiliarActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuAuxiliar);

        jMenuMaterial.setText("Material");
        jMenuMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuMaterialActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuMaterial);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Agregar Inventario");

        jMenuItem4.setText("Almacen1/principal");
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Almacen General");
        jMenu2.add(jMenuItem5);

        jMenuItem6.setText("Laboratorio Instrumental");
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

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

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jtakeFormatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtakeFormatoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtakeFormatoActionPerformed

    private void jtakeNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtakeNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtakeNombreActionPerformed

    private void jtakeUbicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtakeUbicacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtakeUbicacionActionPerformed

    private void jtakeUbicacion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtakeUbicacion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtakeUbicacion1ActionPerformed

    private void jBmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBmodificarActionPerformed
        // TODO add your handling code here:

        modif = new Modificacion();
        modif.setVisible(true);
    }//GEN-LAST:event_jBmodificarActionPerformed

    private void jBsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsalirActionPerformed
        // TODO add your handling code here:
        log = new Login();
        log.setVisible(true);
        dispose();

    }//GEN-LAST:event_jBsalirActionPerformed

    private void jBbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBbuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBbuscarActionPerformed

    private void jMenuMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuMaterialActionPerformed
        // TODO add your handling code here:
        mat = new AgregacionMaterial();
        mat.setVisible(true);
        
    }//GEN-LAST:event_jMenuMaterialActionPerformed

    private void jMenuReactivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuReactivoActionPerformed
        // TODO add your handling code here:
        reac = new AgregacionReactivo();
        reac.setVisible(true);
    }//GEN-LAST:event_jMenuReactivoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        try {
            // Conectar a la base de datos
            this.conexion = new Conexion("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/qchimbasos", "root", "");

            // Crear una declaración
            Statement stmt = this.conexion.conecta().createStatement();

            // Ejecutar una consulta SQL para recuperar los datos de tipo_riesgos
            String sql = "SELECT nombre, descripcion FROM tipo_riesgos";
            ResultSet rs = stmt.executeQuery(sql);

            // Crear un nuevo archivo de texto
            String txtFilePath = "tipo_riesgos.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(txtFilePath));

            // Escribir los datos en el archivo de texto
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                writer.write("Nombre: " + nombre + "\n");
                writer.write("Descripción: " + descripcion + "\n\n");
            }

            // Cerrar el escritor y la conexión a la base de datos
            writer.close();
            this.conexion.cerrar();

            System.out.println("Archivo de texto creado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuAuxiliarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAuxiliarActionPerformed
        // TODO add your handling code here:
        aux = new AgregacionAuxiliar();
        aux.setVisible(true);
    }//GEN-LAST:event_jMenuAuxiliarActionPerformed

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
            java.util.logging.Logger.getLogger(busquedaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(busquedaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(busquedaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(busquedaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new busquedaAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBbuscar;
    private javax.swing.JButton jBmodificar;
    private javax.swing.JButton jBsalir;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLnombre;
    private javax.swing.JLabel jLproductos;
    private javax.swing.JLabel jLproductos1;
    private javax.swing.JLabel jLubicacion;
    private javax.swing.JLabel jLubicacion1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuAuxiliar;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuMaterial;
    private javax.swing.JMenuItem jMenuReactivo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jtakeFormato;
    private javax.swing.JTextField jtakeNombre;
    private javax.swing.JTextField jtakeUbicacion;
    private javax.swing.JTextField jtakeUbicacion1;
    // End of variables declaration//GEN-END:variables
}

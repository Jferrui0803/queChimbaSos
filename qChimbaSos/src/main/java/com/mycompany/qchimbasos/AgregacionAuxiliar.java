/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.qchimbasos;

import com.mycompany.qchimbasos.clases.Conexion;
import com.mycompany.qchimbasos.clases.Reactivos;
import static com.mycompany.qchimbasos.clases.Reactivos.crearReactivos;
import com.mycompany.qchimbasos.clases.Auxiliares;
import static com.mycompany.qchimbasos.clases.Auxiliares.crearAuxiliar;
import com.mycompany.qchimbasos.clases.InventarioAuxiliares;
import com.mycompany.qchimbasos.clases.InventarioReactivosMateriales;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author DAW MAÑANA
 */
public class AgregacionAuxiliar extends javax.swing.JFrame implements metdodosMeter {

    /**
     * Creates new form AgregacionAuxiliar
     */
    private Conexion conexion;
    private Reactivos reactivos;
    PreparedStatement ps = null;
    ResultSet rs = null;
    private Auxiliares auxiliares;
    private InventarioAuxiliares inventarioAuxiliares;

    private void cerrarVentana() {
        String botones[] = {"Cerrar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(rootPane, "¿Quieres cerrar la ventana?", "¡Cuidado!",
                0, 0, null, botones, EXIT_ON_CLOSE);
        if (eleccion == JOptionPane.YES_OPTION) {
            dispose();
        } else if (eleccion == JOptionPane.NO_OPTION) {
            System.out.println("Cierre cancelado");
        }
    }

    public AgregacionAuxiliar() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cerrarVentana();
            }

        });
    }

    @Override
    public int meterProducto() {
        this.conexion = new Conexion("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/qchimbasos", "root", "");

        // Creamos un nuevo auxiliar, en el que se recoge el nombre y el tipo de material
        this.auxiliares = crearAuxiliar(jTextNombre.getText(), jTextTMaterial.getText());
        int nuevoId = -1;
        try {
            Connection conec = this.conexion.conecta();

            //Sentencia para añadir un nuevo usuario y contraseña 
            String sql = "INSERT INTO auxiliares (Nombre, Tipo_Material) VALUES (?, ?)";
            ps = conec.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
          
            ps.setString(1, this.auxiliares.getNombre());
            ps.setString(2, this.auxiliares.getTipo_material());

            int filasInsertadas = ps.executeUpdate();

            if (filasInsertadas > 0) {
                // Si las credenciales son correctas, muestra un mensaje de éxito
                javax.swing.JOptionPane.showMessageDialog(null, "Producto auxiliar agregado");
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    // Obtener el ID generado para el nuevo producto
                    nuevoId = generatedKeys.getInt(1);
                }
                // Aquí puedes abrir la nueva ventana o realizar otras acciones necesarias
            } else {
                // Si las credenciales son incorrectas, muestra un mensaje de error
                JOptionPane.showMessageDialog(rootPane, "Algo salio mal", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(rootPane, "El producto de nombre: "
                    + this.reactivos.getNombre() + " , ya está registrado en la base de datos", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }        // TODO add your handling code here:
        return nuevoId;
    }

    @Override
    public void meterInventario(int idproductoInsertado) {
        this.conexion = new Conexion("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/qchimbasos", "root", "");

        // Creamos un nuevo reactivo, en el que se recoge el nombre, la selección de producto con un CASTEO y el grado de pureza
        this.inventarioAuxiliares = new InventarioAuxiliares((String) jComboBoxLocal.getSelectedItem(),
                (String) jComboBoxUbi.getSelectedItem(),
                Integer.parseInt(jTextFieldCanti.getText()),
                Integer.parseInt(jTextFieldStock.getText()));

        try {
            Connection conec = this.conexion.conecta();

            // Sentencia para añadir un nuevo producto
            String sql = "INSERT INTO inventario_auxiliares(ID_Almacen, ID_Auxiliares, ID_Ubicacion, Cantidad, Stock_Minimo) VALUES (? , ? , ? , ? , ?)";

            // Establecer el flag para recuperar las claves generadas
            ps = conec.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, this.inventarioAuxiliares.getCodigoAlmacen());
            ps.setInt(2, idproductoInsertado);
            ps.setInt(3, this.inventarioAuxiliares.getCodigoUbicacion());
            ps.setInt(4, this.inventarioAuxiliares.getCantidad());
            ps.setInt(5, this.inventarioAuxiliares.getStockMinimo());

            int filasInsertadas = ps.executeUpdate();

            if (filasInsertadas > 0) {

                // Si las credenciales son correctas, muestra un mensaje de éxito
                System.out.println("AYUDA RUT");

                // Aquí puedes abrir la nueva ventana o realizar otras acciones necesarias
            } else {
                // Si las credenciales son incorrectas, muestra un mensaje de error
                JOptionPane.showMessageDialog(rootPane, "Algo salió mal", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextTMaterial = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextNombre = new javax.swing.JTextField();
        jBSalir = new javax.swing.JButton();
        jBañadir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxLocal = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jComboBoxUbi = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldCanti = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldStock = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel4.setText("AUXILIARES");

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel1.setText("Nombre");

        jTextTMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextTMaterialActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel2.setText("Tipo Material");

        jTextNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNombreActionPerformed(evt);
            }
        });

        jBSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cerrarSesion.png"))); // NOI18N
        jBSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalirActionPerformed(evt);
            }
        });

        jBañadir.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        jBañadir.setText("Añadir");
        jBañadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBañadirActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/auxiliares.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel8.setText("Localización");

        jComboBoxLocal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Almacen 1 /principal", "Almacen General", "Laboratorio Instrumental" }));

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel9.setText("Ubicación");

        jComboBoxUbi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1CA", "2N", "3N", "4N", "5N", "8l", "estantería 1, balda 3", "estantería 1,balda 4", "C1", "C2", "P1", "P2", "Estantería 0, balda 4 ", " " }));
        jComboBoxUbi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxUbiActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel6.setText("Cantidad");

        jTextFieldCanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCantiActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel11.setText("Stock Minimo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jComboBoxLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldStock, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jComboBoxUbi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jTextFieldCanti, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextTMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(107, 107, 107))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addGap(33, 33, 33)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel3)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jBSalir)
                                .addGap(96, 96, 96))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jBañadir)
                                .addGap(124, 124, 124))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel4)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jTextTMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jBañadir))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxUbi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldCanti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBSalir)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(jTextFieldStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextTMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextTMaterialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextTMaterialActionPerformed

    private void jTextNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNombreActionPerformed

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jBSalirActionPerformed

    private void jBañadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBañadirActionPerformed
        int id = meterProducto();
        meterInventario(id);
    }//GEN-LAST:event_jBañadirActionPerformed

    private void jComboBoxUbiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxUbiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxUbiActionPerformed

    private void jTextFieldCantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCantiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCantiActionPerformed

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
            java.util.logging.Logger.getLogger(AgregacionAuxiliar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregacionAuxiliar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregacionAuxiliar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregacionAuxiliar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgregacionAuxiliar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBSalir;
    private javax.swing.JButton jBañadir;
    private javax.swing.JComboBox<String> jComboBoxLocal;
    private javax.swing.JComboBox<String> jComboBoxUbi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldCanti;
    private javax.swing.JTextField jTextFieldStock;
    private javax.swing.JTextField jTextNombre;
    private javax.swing.JTextField jTextTMaterial;
    // End of variables declaration//GEN-END:variables

}

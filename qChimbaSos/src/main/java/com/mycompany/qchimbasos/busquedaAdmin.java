/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.qchimbasos;

import static com.mycompany.qchimbasos.Login.bsqAdmin;
import static com.mycompany.qchimbasos.Register.log;
import com.mycompany.qchimbasos.clases.Conexion;
import com.mycompany.qchimbasos.clases.Reactivos;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DAW MAÑANA
 */
public class busquedaAdmin extends javax.swing.JFrame {

    /**
     * Creates new form busquedaAlum
     */
    // Creamos la variable global tabla
    String tabla;
    private Conexion conexion;
    private Reactivos reactivo;

    public static Modificacion modif;
    public static Login log;
    public static AgregacionReactivo reac;
    public static AgregacionMaterial mat;
    public static AgregacionAuxiliar aux;

    private void cerrarVentana() {
        String botones[] = {"Cerrar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(rootPane, "¿Quieres cerrar la aplicación?", "¡Cuidado!",
                0, 0, null, botones, EXIT_ON_CLOSE);
        if (eleccion == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else if (eleccion == JOptionPane.NO_OPTION) {
            System.out.println("Cierre cancelado");
        }
    }

    public busquedaAdmin() {
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

    public void mostrarMateriales() {
        String nombre = jtakeNombre.getText();

        tabla = "materiales";
        this.conexion = new Conexion("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/qchimbasos", "root", "");
        DefaultTableModel mt = new DefaultTableModel();
        mt.addColumn("ID");
        mt.addColumn("Nombre");
        mt.addColumn("Tipo_Material");
        mt.addColumn("Descripcion");
        mt.addColumn("Nº_Serie");
        mt.addColumn("Localizacion");
        mt.addColumn("Ubicacion");
        mt.addColumn("Cantidad");
        mt.addColumn("Stock_Minimo");
        mt.addColumn("Fecha_compra");

        jTable1.setModel(mt);

        try {
            Connection con = this.conexion.conecta();

            // Construir la consulta SQL
            String query = "SELECT M.ID, M.Nombre, M.Tipo_Material, M.Descripcion, "
                    + "M.Nº_Serie, AL.Nombre AS Localizacion, U.Ubicacion, "
                    + "IM.Cantidad, IM.Stock_minimo, IM.Fecha_compra "
                    + "FROM Materiales M "
                    + "JOIN Inventario_Materiales IM ON M.ID = IM.ID_Material "
                    + "JOIN almacenes AL ON IM.ID_almacen = AL.ID "
                    + "JOIN Ubicaciones U ON IM.ID_ubicacion = U.ID ";

            // Si el nombre no está vacío, agregar filtro
            if (!nombre.trim().isEmpty()) {
                query += "WHERE M.Nombre = ?";
            }

            PreparedStatement pstmt = con.prepareStatement(query);

            // Si el nombre no está vacío, asignar el valor como parámetro
            if (!nombre.trim().isEmpty()) {
                pstmt.setString(1, nombre);
            }

            ResultSet rs = pstmt.executeQuery();

            // Limpiar la tabla antes de agregar nuevos datos
            mt.setRowCount(0);

            // Iterar a través de los resultados y agregarlos a la tabla
            while (rs.next()) {
                int id = rs.getInt("M.ID");
                String nombreProducto = rs.getString("M.Nombre");
                String tipoMaterial = rs.getString("M.Tipo_Material");
                String descripcion = rs.getString("M.Descripcion");
                String numeroSerie = rs.getString("M.Nº_Serie");
                String localizacion = rs.getString("Localizacion");
                String ubicacion = rs.getString("U.Ubicacion");
                String cantidad = rs.getString("IM.Cantidad");
                String stock = rs.getString("IM.Stock_minimo");
                String fecha = rs.getString("IM.Fecha_compra");

                mt.addRow(new Object[]{id, nombreProducto, tipoMaterial, descripcion, numeroSerie, localizacion, ubicacion, cantidad, stock, fecha});
            }

            // Cerrar la conexión
            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void mostrarReactivos() {
        String nombre = jtakeNombre.getText();

        tabla = "productos_quimicos";
        this.conexion = new Conexion("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/qchimbasos", "root", "");
        DefaultTableModel mt = new DefaultTableModel();
        mt.addColumn("ID");
        mt.addColumn("Nombre");
        mt.addColumn("Grado de Pureza");
        mt.addColumn("Formato");
        mt.addColumn("Localizacion");
        mt.addColumn("Ubicacion");
        mt.addColumn("Cantidad");
        mt.addColumn("Stock_Minimo");
        mt.addColumn("Fecha_caducidad");

        jTable1.setModel(mt);

        try {
            Connection con = this.conexion.conecta();

            // Construir la consulta SQL
            String query = "SELECT p.ID, p.Nombre, p.Grado_pureza, f.formato, AL.Nombre AS Localizacion, "
                    + "U.ubicacion, IM.Cantidad, IM.Stock_minimo, IM.Fecha_caducidad "
                    + "FROM productos_quimicos p "
                    + "JOIN formato f ON p.ID_formato = f.ID "
                    + "JOIN inventario_reactivos IM ON p.ID = IM.ID_Producto "
                    + "JOIN ALMACENES AL ON IM.ID_almacen = AL.ID "
                    + "JOIN UBICACIONES U ON IM.ID_UBICACION = U.ID ";

            // Si el nombre no está vacío, agregar filtro
            if (!nombre.trim().isEmpty()) {
                query += "WHERE p.Nombre = ?";
            }

            PreparedStatement pstmt = con.prepareStatement(query);

            // Si el nombre no está vacío, asignar el valor como parámetro
            if (!nombre.trim().isEmpty()) {
                pstmt.setString(1, nombre);
            }

            ResultSet rs = pstmt.executeQuery();

            // Limpiar la tabla antes de agregar nuevos datos
            mt.setRowCount(0);

            // Iterar a través de los resultados y agregarlos a la tabla
            while (rs.next()) {
                int id = rs.getInt("p.ID");
                String nombreProducto = rs.getString("p.Nombre");
                String tipoMaterial = rs.getString("p.Grado_pureza");
                String descripcion = rs.getString("f.formato");
                String localizacion = rs.getString("Localizacion");
                String ubicacion = rs.getString("U.ubicacion");
                String cantidad = rs.getString("IM.Cantidad");
                String stock = rs.getString("IM.Stock_minimo");
                String fecha = rs.getString("IM.Fecha_caducidad");

                mt.addRow(new Object[]{id, nombreProducto, tipoMaterial, descripcion, localizacion, ubicacion, cantidad, stock, fecha});
            }

            // Cerrar la conexión
            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void mostrarAuxiliares() {

        String nombre = jtakeNombre.getText();
        System.out.println(nombre);
        tabla = "auxiliares";
        this.conexion = new Conexion("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/qchimbasos", "root", "");
        DefaultTableModel mt = new DefaultTableModel();
        mt.addColumn("ID");
        mt.addColumn("Nombre");
        mt.addColumn("Tipo_Material");
        mt.addColumn("Almacen");
        mt.addColumn("Ubicacion");
        mt.addColumn("Cantidad");
        mt.addColumn("Stock_minimo");

//Falta la Ubicacion y la Localizacion INNER JOIN 
        jTable1.setModel(mt);

        try {
            Connection con = this.conexion.conecta();

            // Usando una consulta preparada con parámetros
            String query = "SELECT A.ID, A.nombre, A.tipo_material, AL.Nombre AS Almacen, "
                    + "U.Ubicacion AS Ubicacion, IM.Cantidad, IM.Stock_Minimo FROM auxiliares A "
                    + "LEFT JOIN inventario_auxiliares IM ON A.ID = IM.ID_Auxiliares "
                    + "LEFT JOIN almacenes AL ON IM.ID_Almacen = AL.ID "
                    + "LEFT JOIN ubicaciones U ON IM.ID_Ubicacion = U.ID ";

            // Si el nombre no está vacío, agregar filtro
            if (!nombre.trim().isEmpty()) {
                query += "WHERE A.Nombre = ?";
            }

            PreparedStatement pstmt = con.prepareStatement(query);

            // Si el nombre no está vacío, asignar el valor como parámetro
            if (!nombre.trim().isEmpty()) {
                pstmt.setString(1, nombre);
            }

            ResultSet rs = pstmt.executeQuery();

            // Limpiar la tabla antes de agregar nuevos datos
            mt.setRowCount(0);

            // Iterar a través de los resultados y agregarlos a la tabla
            while (rs.next()) {
                int id = rs.getInt("ID");
                String nombreProducto = rs.getString("nombre");
                String tipoMaterial = rs.getString("tipo_material");
                String almacen = rs.getString("Almacen");
                String ubi = rs.getString("Ubicacion");
                String cantidad = rs.getString("Cantidad");
                String stock = rs.getString("Stock_Minimo");

                mt.addRow(new Object[]{id, nombreProducto, tipoMaterial, almacen, ubi, cantidad, stock});
            }

            // Cerrar la conexión
            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
        jBeliminar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuReactivo = new javax.swing.JMenuItem();
        jMenuAuxiliar = new javax.swing.JMenuItem();
        jMenuMaterial = new javax.swing.JMenuItem();

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

        jLproductos.setForeground(new java.awt.Color(102, 102, 102));
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

        jLproductos1.setForeground(new java.awt.Color(102, 102, 102));
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

        jLnombre.setForeground(new java.awt.Color(102, 102, 102));
        jLnombre.setText("Nombre");

        jLubicacion.setForeground(new java.awt.Color(102, 102, 102));
        jLubicacion.setText("Ubicación");

        jLubicacion1.setForeground(new java.awt.Color(102, 102, 102));
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

        jBeliminar.setText("Eliminar");
        jBeliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBeliminarActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jBmodificar)
                                .addGap(18, 18, 18)
                                .addComponent(jBeliminar)
                                .addGap(67, 67, 67)
                                .addComponent(jBsalir))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(287, 287, 287)
                                .addComponent(jBbuscar)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1))
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 269, Short.MAX_VALUE)
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
                                    .addComponent(jtakeUbicacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(26, 26, 26))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 29, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBbuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jBsalir)
                        .addGap(61, 61, 61))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBmodificar)
                            .addComponent(jButton1)
                            .addComponent(jBeliminar))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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

        if (tabla.equals("productos_quimicos")) {
            // Selecciona la fila que esocge el usaurio
            int filaSeleccionada = jTable1.getSelectedRow();
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            int idRegistro = (int) modelo.getValueAt(filaSeleccionada, 0);
            String nombre = (String) modelo.getValueAt(filaSeleccionada, 1);
            String formato = (String) modelo.getValueAt(filaSeleccionada, 2);
            String pureza = (String) modelo.getValueAt(filaSeleccionada, 3);
            this.reactivo = new Reactivos(nombre, formato, pureza);
            modif = new Modificacion(this.reactivo, idRegistro);
            modif.setVisible(true);
        }

    }//GEN-LAST:event_jBmodificarActionPerformed

    private void jBsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsalirActionPerformed
        // TODO add your handling code here:
        log = new Login();
        log.setVisible(true);
        dispose();

    }//GEN-LAST:event_jBsalirActionPerformed

    private void jBbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBbuscarActionPerformed

        String consulta = (String) jComboBox1.getSelectedItem();

        if (consulta.equals("Materiales")) {
            mostrarMateriales();
        }
        if (consulta.equals("Auxiliares")) {
            mostrarAuxiliares();
        }

        if (consulta.equals("Reactivos")) {
            mostrarReactivos();
        }

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

    // BOTONO PARA CREAR UN ARCHIVO DE TEXTO COON TODOS LOS RIESGOOOOS
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        try {
            // Obtener la fila seleccionada en la tabla
            int filaSeleccionada = jTable1.getSelectedRow();

            // Verificar si se seleccionó una fila
            if (filaSeleccionada != -1) {
                // Obtener el ID del registro seleccionado en la tabla
                DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
                int idRegistro = (int) modelo.getValueAt(filaSeleccionada, 0);

                // Conectar a la base de datos
                this.conexion = new Conexion("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/qchimbasos", "root", "");
                Statement stmt = this.conexion.conecta().createStatement();

                // Ejecutar una consulta SQL para eliminar el registro de la base de datos
                String sqlEliminar = "DELETE FROM tipo_riesgos WHERE id = " + idRegistro; // Suponiendo que el campo de ID se llama "id"
                int filasEliminadas = stmt.executeUpdate(sqlEliminar);

                // Verificar si se eliminó correctamente el registro de la base de datos
                if (filasEliminadas > 0) {
                    // Eliminar la fila correspondiente de la tabla visual
                    modelo.removeRow(filaSeleccionada);

                    System.out.println("Registro eliminado correctamente de la base de datos y de la tabla.");
                } else {
                    System.out.println("No se pudo eliminar el registro de la base de datos.");
                }

                // Cerrar la conexión a la base de datos
                this.conexion.cerrar();
            } else {
                System.out.println("No se ha seleccionado ninguna fila.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuAuxiliarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAuxiliarActionPerformed
        // TODO add your handling code here:
        aux = new AgregacionAuxiliar();
        aux.setVisible(true);
    }//GEN-LAST:event_jMenuAuxiliarActionPerformed

    private void jBeliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBeliminarActionPerformed
        try {

            int filaSeleccionada = jTable1.getSelectedRow();

            if (filaSeleccionada != -1) {

                DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
                int idRegistro = (int) modelo.getValueAt(filaSeleccionada, 0);

                this.conexion = new Conexion("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/qchimbasos", "root", "");
                Statement stmt = this.conexion.conecta().createStatement();
                if (tabla.equals("materiales")) {
                   String sqlEliminarInventario = "DELETE FROM inventario_materiales WHERE ID = " + idRegistro;
                    int filasEliminadas = stmt.executeUpdate(sqlEliminarInventario);
                    if (filasEliminadas > 0) {
                        modelo.removeRow(filaSeleccionada);

                        System.out.println("Registro eliminado correctamente del inventario ");
                    } else {
                        System.out.println("No se pudo eliminar el registro de la base de datos.");
                    }
                    
                    System.out.println("SA eliminao el producto del inveataiorasikjdoa");
                    String sqlEliminar = "DELETE FROM " + tabla + " WHERE id = " + idRegistro;
                    filasEliminadas = stmt.executeUpdate(sqlEliminar);
                    System.out.println("SA eliminao el producto entero");

                    if (filasEliminadas > 0) {
                        modelo.removeRow(filaSeleccionada);

                        System.out.println("Registro eliminado correctamente de la base de datos y de la tabla.");
                    } else {
                        System.out.println("No se pudo eliminar el registro de la base de datos.");
                    }

                }

                if (tabla.equals("auxiliares")) {
                    String sqlEliminarInventario = "DELETE FROM inventario_auxiliares WHERE ID = " + idRegistro;
                    int filasEliminadas = stmt.executeUpdate(sqlEliminarInventario);
                    if (filasEliminadas > 0) {
                        modelo.removeRow(filaSeleccionada);

                        System.out.println("Registro eliminado correctamente del inventario ");
                    } else {
                        System.out.println("No se pudo eliminar el registro de la base de datos.");
                    }
                    
                    System.out.println("SA eliminao el producto del inveataiorasikjdoa");
                    String sqlEliminar = "DELETE FROM " + tabla + " WHERE id = " + idRegistro;
                    filasEliminadas = stmt.executeUpdate(sqlEliminar);
                    System.out.println("SA eliminao el producto entero");
                    
                    
                    
                    if (filasEliminadas > 0) {
                        modelo.removeRow(filaSeleccionada);

                        System.out.println("Registro eliminado correctamente de la base de datos y de la tabla.");
                    } else {
                        System.out.println("No se pudo eliminar el registro de la base de datos.");
                    }

                }
                if (tabla.equals("productos_quimicos")) {
                    String sqlEliminarInventario = "DELETE FROM inventario_reactivos WHERE id_producto = " + idRegistro;
                    int filasEliminadas = stmt.executeUpdate(sqlEliminarInventario);
                    if (filasEliminadas > 0) {
                        modelo.removeRow(filaSeleccionada);

                        System.out.println("Registro eliminado correctamente del inventario ");
                    } else {
                        System.out.println("No se pudo eliminar el registro de la base de datos.");
                    }
                    
                    System.out.println("SA eliminao el producto del inveataiorasikjdoa");
                    String sqlEliminar = "DELETE FROM " + tabla + " WHERE id = " + idRegistro;
                    filasEliminadas = stmt.executeUpdate(sqlEliminar);
                    System.out.println("SA eliminao el producto entero");

                    if (filasEliminadas > 0) {
                        modelo.removeRow(filaSeleccionada);

                        System.out.println("Registro eliminado correctamente de la base de datos y de la tabla.");
                    } else {
                        System.out.println("No se pudo eliminar el registro de la base de datos.");
                    }

                }

                this.conexion.cerrar();
            } else {
                System.out.println("No se ha seleccionado ninguna fila.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jBeliminarActionPerformed

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
    private javax.swing.JButton jBeliminar;
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
    private javax.swing.JMenuItem jMenuAuxiliar;
    private javax.swing.JMenuBar jMenuBar1;
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

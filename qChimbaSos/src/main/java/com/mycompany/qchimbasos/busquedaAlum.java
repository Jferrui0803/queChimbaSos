/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.qchimbasos;

import static com.mycompany.qchimbasos.Login.reg;
import com.mycompany.qchimbasos.clases.Conexion;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DAW MAÑANA
 */
public class busquedaAlum extends javax.swing.JFrame {

    String tabla;
    private Conexion conexion;
    public static Login log;

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

    public busquedaAlum() {
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
        String formato = (String) jComboBoxFormato.getSelectedItem();
        String ubi = (String) jComboBoxUbi.getSelectedItem();
        String localizac = (String) jComboBoxLocal.getSelectedItem();

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
                // Si el nombre no está vacío, agregar filtro
            if (!nombre.trim().isEmpty() && formato.trim().isEmpty() && ubi.trim().isEmpty() && localizac.trim().isEmpty()) {
                query += "WHERE p.Nombre = ?";

            }

            if (!ubi.trim().isEmpty() && formato.trim().isEmpty() && nombre.trim().isEmpty() && localizac.trim().isEmpty()) {
                query += "WHERE U.Ubicacion = ?";

            }

            if (!localizac.trim().isEmpty() && formato.trim().isEmpty() && nombre.trim().isEmpty() && ubi.trim().isEmpty()) {
                query += "WHERE AL.Nombre = ?";

            }

            PreparedStatement pstmt = con.prepareStatement(query);

            // Si el nombre no está vacío, asignar el valor como parámetro
            if (!nombre.trim().isEmpty() && formato.trim().isEmpty() && ubi.trim().isEmpty() && localizac.trim().isEmpty()) {
                pstmt.setString(1, nombre);
            }
            if (!ubi.trim().isEmpty() && formato.trim().isEmpty() && nombre.trim().isEmpty() && localizac.trim().isEmpty()) {
                pstmt.setString(1, ubi);

            }
            if (!localizac.trim().isEmpty() && formato.trim().isEmpty() && nombre.trim().isEmpty() && ubi.trim().isEmpty()) {
                pstmt.setString(1, localizac);

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
        String formato = (String) jComboBoxFormato.getSelectedItem();
        String ubi = (String) jComboBoxUbi.getSelectedItem();
        String localizac = (String) jComboBoxLocal.getSelectedItem();

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
            if (!nombre.trim().isEmpty() && formato.trim().isEmpty() && ubi.trim().isEmpty() && localizac.trim().isEmpty()) {
                query += "WHERE p.Nombre = ?";

            }

            if (!ubi.trim().isEmpty() && formato.trim().isEmpty() && nombre.trim().isEmpty() && localizac.trim().isEmpty()) {
                query += "WHERE U.Ubicacion = ?";

            }

            if (!localizac.trim().isEmpty() && formato.trim().isEmpty() && nombre.trim().isEmpty() && ubi.trim().isEmpty()) {
                query += "WHERE AL.Nombre = ?";

            }
            
            if (!formato.trim().isEmpty() && localizac.trim().isEmpty() && nombre.trim().isEmpty() && ubi.trim().isEmpty()) {
                query += "WHERE f.formato = ?";

            }


            PreparedStatement pstmt = con.prepareStatement(query);

            // Si el nombre no está vacío, asignar el valor como parámetro
            if (!nombre.trim().isEmpty() && formato.trim().isEmpty() && ubi.trim().isEmpty() && localizac.trim().isEmpty()) {
                pstmt.setString(1, nombre);
            }
            if (!ubi.trim().isEmpty() && formato.trim().isEmpty() && nombre.trim().isEmpty() && localizac.trim().isEmpty()) {
                pstmt.setString(1, ubi);

            }
            if (!localizac.trim().isEmpty() && formato.trim().isEmpty() && nombre.trim().isEmpty() && ubi.trim().isEmpty()) {
                pstmt.setString(1, localizac);

            }
            if (!formato.trim().isEmpty() && localizac.trim().isEmpty() && nombre.trim().isEmpty() && ubi.trim().isEmpty()) {
                pstmt.setString(1, formato);

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
        String formato = (String) jComboBoxFormato.getSelectedItem();
        String ubicacion = (String) jComboBoxUbi.getSelectedItem();
        String localizacion = (String) jComboBoxLocal.getSelectedItem();
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
            if (!nombre.trim().isEmpty() && formato.trim().isEmpty() && ubicacion.trim().isEmpty() && localizacion.trim().isEmpty()) {
                query += "WHERE A.Nombre = ?";

            }

            if (!ubicacion.trim().isEmpty() && formato.trim().isEmpty() && nombre.trim().isEmpty() && localizacion.trim().isEmpty()) {
                query += "WHERE U.Ubicacion = ?";

            }

            if (!localizacion.trim().isEmpty() && formato.trim().isEmpty() && nombre.trim().isEmpty() && ubicacion.trim().isEmpty()) {
                query += "WHERE AL.Nombre = ?";

            }

            PreparedStatement pstmt = con.prepareStatement(query);

            // Si el nombre no está vacío, asignar el valor como parámetro
            if (!nombre.trim().isEmpty() && formato.trim().isEmpty() && ubicacion.trim().isEmpty() && localizacion.trim().isEmpty()) {
                pstmt.setString(1, nombre);
            }
            if (!ubicacion.trim().isEmpty() && formato.trim().isEmpty() && nombre.trim().isEmpty() && localizacion.trim().isEmpty()) {
                pstmt.setString(1, ubicacion);

            }
            if (!localizacion.trim().isEmpty() && formato.trim().isEmpty() && nombre.trim().isEmpty() && ubicacion.trim().isEmpty()) {
                pstmt.setString(1, localizacion);

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

        jPanel2 = new javax.swing.JPanel();
        jBotonUsuario = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLproductos = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLnombre = new javax.swing.JLabel();
        jtakeNombre = new javax.swing.JTextField();
        jLproductos1 = new javax.swing.JLabel();
        jLubicacion = new javax.swing.JLabel();
        jLubicacion1 = new javax.swing.JLabel();
        jBbuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jBsalir = new javax.swing.JButton();
        jComboBoxFormato = new javax.swing.JComboBox<>();
        jComboBoxUbi = new javax.swing.JComboBox<>();
        jComboBoxLocal = new javax.swing.JComboBox<>();
        jMenuBar2 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jBotonUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user.png"))); // NOI18N
        jBotonUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonUsuarioActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logoizv.png"))); // NOI18N

        jLproductos.setForeground(new java.awt.Color(102, 102, 102));
        jLproductos.setText("Productos *");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Reactivos", "Auxiliares", "Materiales" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLnombre.setForeground(new java.awt.Color(102, 102, 102));
        jLnombre.setText("Nombre");

        jtakeNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtakeNombreActionPerformed(evt);
            }
        });

        jLproductos1.setForeground(new java.awt.Color(102, 102, 102));
        jLproductos1.setText("Formato");

        jLubicacion.setForeground(new java.awt.Color(102, 102, 102));
        jLubicacion.setText("Ubicación");

        jLubicacion1.setForeground(new java.awt.Color(102, 102, 102));
        jLubicacion1.setText("Localización");

        jBbuscar.setText("Buscar");
        jBbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBbuscarActionPerformed(evt);
            }
        });

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

        jBsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cerrarSesion.png"))); // NOI18N
        jBsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsalirActionPerformed(evt);
            }
        });

        jComboBoxFormato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "1 Kg", "100 g", "250 g", "500 g", "5 g", "No viene reflejado", "1 L", "500 mL", "5 Kg", "2,5 L", "250 mL", "100 mL", "250 g,1 kg", "10 g", " " }));

        jComboBoxUbi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "1CA", "2N", "3N", "4N", "5N", "8l", "estantería 1, balda 3", "estantería 1,balda 4", "C1", "C2", "P1", "P2", "Estantería 0, balda 4 ", " " }));
        jComboBoxUbi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxUbiActionPerformed(evt);
            }
        });

        jComboBoxLocal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Almacen 1 /principal", "Almacen General", "Laboratorio Instrumental" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBotonUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(294, 294, 294)
                                .addComponent(jBbuscar))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jBsalir)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addGap(12, 12, 12)
                                                    .addComponent(jLproductos))
                                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(44, 44, 44)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addGap(32, 32, 32)
                                                    .addComponent(jLnombre))
                                                .addComponent(jtakeNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addGap(69, 69, 69)
                                                    .addComponent(jLproductos1)
                                                    .addGap(104, 104, 104)
                                                    .addComponent(jLubicacion)
                                                    .addGap(98, 98, 98)
                                                    .addComponent(jLubicacion1))
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addGap(50, 50, 50)
                                                    .addComponent(jComboBoxFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(60, 60, 60)
                                                    .addComponent(jComboBoxUbi, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(46, 46, 46)
                                                    .addComponent(jComboBoxLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(39, 154, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLnombre)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtakeNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLproductos, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLubicacion)
                                    .addComponent(jLproductos1)
                                    .addComponent(jLubicacion1)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBoxFormato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxUbi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGap(26, 26, 26)
                    .addComponent(jBbuscar)
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(38, 38, 38)
                    .addComponent(jBsalir)
                    .addGap(16, 16, 16))
                .addComponent(jLabel1))
            .addComponent(jBotonUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBotonUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotonUsuarioActionPerformed
        log = new Login();
        log.setVisible(true);
        dispose();
    }//GEN-LAST:event_jBotonUsuarioActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jtakeNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtakeNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtakeNombreActionPerformed

    private void jBbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBbuscarActionPerformed
        // TODO add your handling code here:
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

    private void jBsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsalirActionPerformed
        // TODO add your handling code here:
        log = new Login();
        log.setVisible(true);
        dispose();
    }//GEN-LAST:event_jBsalirActionPerformed

    private void jComboBoxUbiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxUbiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxUbiActionPerformed

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
            java.util.logging.Logger.getLogger(busquedaAlum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(busquedaAlum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(busquedaAlum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(busquedaAlum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new busquedaAlum().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBbuscar;
    private javax.swing.JButton jBotonUsuario;
    private javax.swing.JButton jBsalir;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBoxFormato;
    private javax.swing.JComboBox<String> jComboBoxLocal;
    private javax.swing.JComboBox<String> jComboBoxUbi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLnombre;
    private javax.swing.JLabel jLproductos;
    private javax.swing.JLabel jLproductos1;
    private javax.swing.JLabel jLubicacion;
    private javax.swing.JLabel jLubicacion1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jtakeNombre;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.qchimbasos;

import static com.mycompany.qchimbasos.Login.bsqAdmin;
import static com.mycompany.qchimbasos.Register.log;
import com.mycompany.qchimbasos.clases.Auxiliares;
import static com.mycompany.qchimbasos.clases.Auxiliares.crearAuxiliar;
import com.mycompany.qchimbasos.clases.Conexion;
import com.mycompany.qchimbasos.clases.InventarioAuxiliares;
import com.mycompany.qchimbasos.clases.InventarioReactivosMateriales;
import com.mycompany.qchimbasos.clases.Materiales;
import static com.mycompany.qchimbasos.clases.Materiales.crearMateriales;
import com.mycompany.qchimbasos.clases.Reactivos;
import static com.mycompany.qchimbasos.clases.Reactivos.crearReactivos;
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
import java.util.HashMap;
import java.util.Map;
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
    private Materiales material;
    private Auxiliares auxiliar;
    private InventarioReactivosMateriales inventarioReactivoMateriales;
    private InventarioAuxiliares inventarioAuxiliar;

    public static ModificacionReactivo modifReact;
    public static ModificacionMaterial modifMat;
    public static ModificacionAuxiliar modifAux;
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


    public void crearArchivo() {
        // Conectar a la base de datos
        this.conexion = new Conexion("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/qchimbasos", "root", "");

        try {

            // Crear una declaración
            Connection con = this.conexion.conecta();
            Statement stmt = con.createStatement();

            // Ejecutar una consulta SQL para recuperar los datos de tipo_riesgos
            String sql = "SELECT nombre, descripcion FROM tipo_riesgos";
            ResultSet rs = stmt.executeQuery(sql);

            // Crear un HashMap para almacenar los datos
            HashMap<String, String> tipoRiesgosMap = new HashMap<>();

            // Leer los datos de la base de datos y almacenarlos en el HashMap
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                tipoRiesgosMap.put(nombre, descripcion);
            }

            // Cerrar la conexión a la base de datos
            con.close();

            // Crear un nuevo archivo de texto
            String Riesgos_productos = "tipo_riesgos.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(Riesgos_productos));

            // Escribir los datos del HashMap en el archivo de texto
            for (Map.Entry<String, String> entry : tipoRiesgosMap.entrySet()) {
                writer.write("Nombre: " + entry.getKey() + "\n");
                writer.write("Descripción: " + entry.getValue() + "\n\n");
            }

            // Cerrar el escritor
            writer.close();

            System.out.println("Archivo de texto creado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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
        jLproductos1 = new javax.swing.JLabel();
        jtakeNombre = new javax.swing.JTextField();
        jLnombre = new javax.swing.JLabel();
        jLubicacion = new javax.swing.JLabel();
        jLubicacion1 = new javax.swing.JLabel();
        jBmodificar = new javax.swing.JButton();
        jBbuscar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jBeliminar = new javax.swing.JButton();
        jComboBoxLocal = new javax.swing.JComboBox<>();
        jComboBoxFormato = new javax.swing.JComboBox<>();
        jComboBoxUbi = new javax.swing.JComboBox<>();
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

        jLproductos1.setForeground(new java.awt.Color(102, 102, 102));
        jLproductos1.setText("Formato");

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

        jComboBoxLocal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Almacen 1 /principal", "Almacen General", "Laboratorio Instrumental" }));

        jComboBoxFormato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "1 Kg", "100 g", "250 g", "500 g", "5 g", "No viene reflejado", "1 L", "500 mL", "5 Kg", "2", "5 L", "250 mL", "100 mL", "250 g", "1 kg", "10 g" }));

        jComboBoxUbi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "1CA", "2N", "3N", "4N", "5N", "8l", "estantería 1, balda 3", "estantería 1,balda 4", "C1", "C2", "P1", "P2", "Estantería 0, balda 4 ", " " }));
        jComboBoxUbi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxUbiActionPerformed(evt);
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
                        .addGap(126, 126, 126)
                        .addComponent(jButton1)
                        .addGap(26, 26, 26)
                        .addComponent(jBmodificar)
                        .addGap(18, 18, 18)
                        .addComponent(jBeliminar)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 68, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLproductos))
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(85, 85, 85)
                                        .addComponent(jLnombre)
                                        .addGap(122, 122, 122)
                                        .addComponent(jLproductos1)
                                        .addGap(139, 139, 139)
                                        .addComponent(jLubicacion)
                                        .addGap(153, 153, 153)
                                        .addComponent(jLubicacion1)
                                        .addGap(129, 129, 129))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(54, 54, 54)
                                        .addComponent(jtakeNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(69, 69, 69)
                                        .addComponent(jComboBoxFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(80, 80, 80)
                                        .addComponent(jComboBoxUbi, 0, 0, Short.MAX_VALUE)
                                        .addGap(73, 73, 73)
                                        .addComponent(jComboBoxLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(81, 81, 81))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jBsalir)
                                .addGap(200, 200, 200))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jBbuscar)
                                .addGap(455, 455, 455))))))
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLnombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLproductos1)
                                    .addComponent(jLubicacion)
                                    .addComponent(jLubicacion1))
                                .addGap(6, 6, 6)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxFormato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxUbi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtakeNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLproductos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(jBbuscar)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jBmodificar)
                            .addComponent(jBeliminar))
                        .addContainerGap(66, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBsalir)
                        .addGap(41, 41, 41))))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

    private void jtakeNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtakeNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtakeNombreActionPerformed

    private void jBmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBmodificarActionPerformed

        if (tabla.equals("productos_quimicos")) {
            // Selecciona la fila que esocge el usaurio
            int filaSeleccionada = jTable1.getSelectedRow();
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            int idRegistro = (int) modelo.getValueAt(filaSeleccionada, 0);
            String nombre = (String) modelo.getValueAt(filaSeleccionada, 1);
            String formato = (String) modelo.getValueAt(filaSeleccionada, 2);
            String pureza = (String) modelo.getValueAt(filaSeleccionada, 3);
            this.reactivo = crearReactivos(nombre, formato, pureza);

            String almacen = (String) modelo.getValueAt(filaSeleccionada, 4);
            String ubicacion = (String) modelo.getValueAt(filaSeleccionada, 5);
            String cantidadStr = (String) modelo.getValueAt(filaSeleccionada, 6);
            int cantidad = 0; // Valor por defecto o un valor adecuado en caso de ser aplicable
            if (!cantidadStr.isEmpty()) {
                cantidad = Integer.parseInt(cantidadStr);
            }

            String stockMinimoStr = (String) modelo.getValueAt(filaSeleccionada, 7);
            int stockMinimo = 0; // Valor por defecto o un valor adecuado en caso de ser aplicable
            if (!stockMinimoStr.isEmpty()) {
                stockMinimo = Integer.parseInt(stockMinimoStr);
            }
            String fechaCaducidad = (String) modelo.getValueAt(filaSeleccionada, 8);
            this.inventarioReactivoMateriales = new InventarioReactivosMateriales(almacen, ubicacion, cantidad, stockMinimo, fechaCaducidad);
            modifReact = new ModificacionReactivo(this.reactivo, this.inventarioReactivoMateriales, idRegistro);
            modifReact.setVisible(true);
        }
        
        
        if (tabla.equals("materiales")) {
            // Selecciona la fila que esocge el usaurio
            int filaSeleccionada = jTable1.getSelectedRow();
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            int idRegistro = (int) modelo.getValueAt(filaSeleccionada, 0);
            String nombre = (String) modelo.getValueAt(filaSeleccionada, 1);
            String tipoMaterial = (String) modelo.getValueAt(filaSeleccionada, 2);
            String descripcion = (String) modelo.getValueAt(filaSeleccionada, 3);
            String nºserieStr = (String) modelo.getValueAt(filaSeleccionada, 4);
            int nºserie = 0; // Valor por defecto o un valor adecuado en caso de ser aplicable
            if (!nºserieStr.isEmpty()) {
                nºserie = Integer.parseInt(nºserieStr);
            }
            this.material = crearMateriales(nombre, tipoMaterial, descripcion, nºserie);

            String almacen = (String) modelo.getValueAt(filaSeleccionada, 5);
            String ubicacion = (String) modelo.getValueAt(filaSeleccionada, 6);
            String cantidadStr = (String) modelo.getValueAt(filaSeleccionada, 7);
            int cantidad = 0; // Valor por defecto o un valor adecuado en caso de ser aplicable
            if (!cantidadStr.isEmpty()) {
                cantidad = Integer.parseInt(cantidadStr);
            }

            String stockMinimoStr = (String) modelo.getValueAt(filaSeleccionada, 8);
            int stockMinimo = 0; // Valor por defecto o un valor adecuado en caso de ser aplicable
            if (!stockMinimoStr.isEmpty()) {
                stockMinimo = Integer.parseInt(stockMinimoStr);
            }
            String fechaCompra = (String) modelo.getValueAt(filaSeleccionada, 9);
            this.inventarioReactivoMateriales = new InventarioReactivosMateriales(almacen, ubicacion, cantidad, stockMinimo, fechaCompra);
            modifMat = new ModificacionMaterial(this.material, this.inventarioReactivoMateriales, idRegistro);
            modifMat.setVisible(true);
        }
        
        
        if(tabla.equals("auxiliares")){
             // Selecciona la fila que esocge el usaurio
            int filaSeleccionada = jTable1.getSelectedRow();
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            int idRegistro = (int) modelo.getValueAt(filaSeleccionada, 0);
            String nombre = (String) modelo.getValueAt(filaSeleccionada, 1);
            String tipoMaterial = (String) modelo.getValueAt(filaSeleccionada, 2);
            this.auxiliar = crearAuxiliar(nombre, tipoMaterial);

            String almacen = (String) modelo.getValueAt(filaSeleccionada, 3);
            String ubicacion = (String) modelo.getValueAt(filaSeleccionada, 4);
            String cantidadStr = (String) modelo.getValueAt(filaSeleccionada, 5);
            int cantidad = 0; // Valor por defecto o un valor adecuado en caso de ser aplicable
            if (!cantidadStr.isEmpty()) {
                cantidad = Integer.parseInt(cantidadStr);
            }

            String stockMinimoStr = (String) modelo.getValueAt(filaSeleccionada, 6);
            int stockMinimo = 0; // Valor por defecto o un valor adecuado en caso de ser aplicable
            if (!stockMinimoStr.isEmpty()) {
                stockMinimo = Integer.parseInt(stockMinimoStr);
            }
            
            this.inventarioAuxiliar = new InventarioAuxiliares(almacen, ubicacion, cantidad, stockMinimo);
            modifAux = new ModificacionAuxiliar(this.auxiliar, this.inventarioAuxiliar, idRegistro);
            modifAux.setVisible(true);
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
        crearArchivo();
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
                        modelo.fireTableDataChanged();


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
    private javax.swing.JComboBox<String> jComboBoxFormato;
    private javax.swing.JComboBox<String> jComboBoxLocal;
    private javax.swing.JComboBox<String> jComboBoxUbi;
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
    private javax.swing.JTextField jtakeNombre;
    // End of variables declaration//GEN-END:variables
}
